package com.github.dashframe.rest;

import com.github.dashframe.dao.ServiceDAO;
import com.github.dashframe.dao.UserDAO;
import com.github.dashframe.dao.WidgetDAO;
import com.github.dashframe.dao.WidgetParameterDAO;
import com.github.dashframe.models.Service;
import com.github.dashframe.models.User;
import com.github.dashframe.models.Widget;
import com.github.dashframe.models.WidgetParameter;
import com.github.dashframe.models.json.*;
import com.github.dashframe.rest.api.WidgetsApi;
import java.util.*;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.NativeWebRequest;

@RestController
@CrossOrigin
public class WidgetsController implements WidgetsApi {

    private final NativeWebRequest request;

    @Autowired
    private ServiceDAO serviceDAO;

    @Autowired
    private WidgetDAO widgetDAO;

    @Autowired
    private UserDAO userDao;

    @Autowired
    private WidgetParameterDAO widgetParameterDAO;

    @Autowired
    public WidgetsController(NativeWebRequest request) {
        this.request = request;
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }

    @RequestMapping(
        method = RequestMethod.POST,
        value = "/widgets",
        produces = { "application/json" },
        consumes = { "application/json" }
    )
    public ResponseEntity<WidgetInstance> createWidget(
        @Valid @RequestBody(required = false) CreateWidgetRequest createWidgetRequest
    ) {
        Optional<Service> service = serviceDAO.findById(createWidgetRequest.getServiceId());
        if (service.isPresent()) {
            System.out.println(createWidgetRequest.getConfig());
            Widget widget = widgetDAO.save(
                new Widget(
                    service.get(),
                    createWidgetRequest.getConfig().getName(),
                    createWidgetRequest.getConfig().getPosition(),
                    createWidgetRequest.getConfig().getColumnPos(),
                    createWidgetRequest.getConfig().getType(),
                    createWidgetRequest.getConfig().getRefreshRate()
                )
            );
            createParameters(createWidgetRequest.getConfig(), widget);
            WidgetType widgetType = WidgetType.fromValue(widget.getType());
            WidgetConfiguration<?> widgetConfiguration = getConfig(widgetType, widget);

            return ResponseEntity.ok(
                new WidgetInstance()
                    .id(widget.getId())
                    .serviceId(widget.getService().getId())
                    .config(widgetConfiguration)
            );
        }
        return ResponseEntity.badRequest().body(null);
    }

    @RequestMapping(
        method = RequestMethod.GET,
        value = "/widgets",
        produces = { "application/json" },
        consumes = { "application/*" }
    )
    public ResponseEntity<ArrayList<WidgetInstance>> getWidgets(
        @Valid @RequestParam(required = false) Optional<Integer> userId
    ) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userDao.findByUsername(username);
        if (userId.isPresent() && user.isAdmin()) {
            user = userDao.findById(userId.get().intValue());
        }
        ArrayList<Service> services = serviceDAO.findByUser(user);
        if (services.size() != 0) {
            ArrayList<WidgetInstance> widgetInstances = new ArrayList<>();
            for (Service service : services) {
                ArrayList<Widget> widgets = widgetDAO.findByService(service);
                if (widgets.size() != 0) {
                    for (Widget widget : widgets) {
                        WidgetType widgetType = WidgetType.fromValue(widget.getType());
                        WidgetConfiguration<?> widgetConfiguration = getConfig(widgetType, widget);
                        widgetInstances.add(
                            new WidgetInstance()
                                .id(widget.getId())
                                .serviceId(widget.getService().getId())
                                .config(widgetConfiguration)
                        );
                    }
                }
            }
            return ResponseEntity.ok(widgetInstances);
        }
        return ResponseEntity.badRequest().body(null);
    }

    @RequestMapping(
        method = RequestMethod.DELETE,
        value = "/widgets",
        produces = { "application/json" },
        consumes = { "application/*" }
    )
    public ResponseEntity<ArrayList<WidgetInstance>> deleteWidgets(
        @Valid @RequestParam(required = false) Optional<Integer> userId
    ) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userDao.findByUsername(username);
        if (userId.isPresent() && user.isAdmin()) {
            user = userDao.findById(userId.get().intValue());
        }
        ArrayList<Service> services = serviceDAO.findByUser(user);
        if (services.size() != 0) {
            for (Service service : services) {
                serviceDAO.delete(service);
                ArrayList<Widget> widgets = widgetDAO.findByService(service);
                if (widgets.size() != 0) {
                    for (Widget widget : widgets) {
                        ArrayList<WidgetParameter> widgetParameters = widgetParameterDAO.findByWidget(widget);
                        if (widgetParameters.size() != 0) {
                            for (WidgetParameter widgetParameter : widgetParameters) {
                                widgetParameterDAO.delete(widgetParameter);
                            }
                        }
                        widgetDAO.delete(widget);
                    }
                }
            }
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.badRequest().body(null);
    }

    @RequestMapping(
        method = RequestMethod.GET,
        value = "/widgets/:{widgetId}",
        produces = { "application/json" },
        consumes = { "application/*" }
    )
    public ResponseEntity<WidgetInstance> getWidget(@Valid @PathVariable(required = false) int widgetId) {
        Widget widget = widgetDAO.findById(widgetId);
        if (widget != null) {
            WidgetType widgetType = WidgetType.fromValue(widget.getType());
            WidgetConfiguration<?> widgetConfiguration = getConfig(widgetType, widget);
            return ResponseEntity.ok(
                new WidgetInstance()
                    .id(widget.getId())
                    .serviceId(widget.getService().getId())
                    .config(widgetConfiguration)
            );
        }
        return ResponseEntity.badRequest().body(null);
    }

    @RequestMapping(
        method = RequestMethod.PATCH,
        value = "/widgets/:{widgetId}",
        produces = { "application/json" },
        consumes = { "application/json" }
    )
    public ResponseEntity<WidgetInstance> putWidget(
        @Valid @PathVariable(required = false) int widgetId,
        @Valid @RequestBody(required = false) UpdateWidgetRequest UpdateWidgetRequest
    ) {
        Widget widget = widgetDAO.findById(widgetId);
        System.out.println(widget);
        if (widget != null) {
            widget.setType(UpdateWidgetRequest.getConfig().getType());
            widget.setName(UpdateWidgetRequest.getConfig().getName());
            widget.setRefreshRate(UpdateWidgetRequest.getConfig().getRefreshRate());
            if (!UpdateWidgetRequest.getConfig().getParameters().isEmpty()) {
                System.out.println(UpdateWidgetRequest.getConfig().getParameters());
                PatchParameters(UpdateWidgetRequest.getConfig(), widget);
            }
            widgetDAO.save(widget);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.badRequest().body(null);
    }

    @RequestMapping(
        method = RequestMethod.DELETE,
        value = "/widgets/:{widgetId}",
        produces = { "application/json" },
        consumes = { "application/*" }
    )
    public ResponseEntity<WidgetInstance> deleteWidget(@Valid @PathVariable(required = false) int widgetId) {
        Widget widget = widgetDAO.findById(widgetId);
        if (widget != null) {
            ArrayList<WidgetParameter> widgetParameters = widgetParameterDAO.findByWidget(widget);
            for (WidgetParameter widgetParameter : widgetParameters) {
                widgetParameterDAO.delete(widgetParameter);
            }
            widgetDAO.delete(widget);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.badRequest().body(null);
    }

    public WidgetConfiguration<?> getConfig(WidgetType widgetType, Widget widget) {
        switch (widgetType) {
            case EPIC_GAMES_FREE_GAMES -> {
                EpicGamesFreeGamesWidgetConfiguration widgetConfiguration = new EpicGamesFreeGamesWidgetConfiguration();
                widgetConfiguration =
                    (EpicGamesFreeGamesWidgetConfiguration) setWidgetConf(widgetConfiguration, widget);
                return widgetConfiguration;
            }
            case EPIC_GAMES_FRIENDS_LIST -> {
                EpicGamesFriendsListWidgetConfiguration widgetConfiguration = new EpicGamesFriendsListWidgetConfiguration();
                widgetConfiguration =
                    (EpicGamesFriendsListWidgetConfiguration) setWidgetConf(widgetConfiguration, widget);
                ArrayList<WidgetParameter> widgetParameters = widgetParameterDAO.findByWidget(widget);
                Map<String, Object> map = new HashMap<>();
                for (WidgetParameter widgetParameter : widgetParameters) {
                    map.put(widgetParameter.getName(), widgetParameter.getValue());
                }
                widgetConfiguration.setParameters(map);
                return widgetConfiguration;
            }
            case RSS_FEED -> {
                RssFeedWidgetConfiguration widgetConfiguration = new RssFeedWidgetConfiguration();
                widgetConfiguration = (RssFeedWidgetConfiguration) setWidgetConf(widgetConfiguration, widget);
                ArrayList<WidgetParameter> widgetParameters = widgetParameterDAO.findByWidget(widget);
                Map<String, Object> map = new HashMap<>();
                for (WidgetParameter widgetParameter : widgetParameters) {
                    map.put(widgetParameter.getName(), widgetParameter.getValue());
                }
                widgetConfiguration.setParameters(map);
                return widgetConfiguration;
            }
            case STEAM_FRIENDS_LIST -> {
                SteamFriendsListWidgetConfiguration widgetConfiguration = new SteamFriendsListWidgetConfiguration();
                widgetConfiguration = (SteamFriendsListWidgetConfiguration) setWidgetConf(widgetConfiguration, widget);
                ArrayList<WidgetParameter> widgetParameters = widgetParameterDAO.findByWidget(widget);
                Map<String, Object> map = new HashMap<>();
                for (WidgetParameter widgetParameter : widgetParameters) {
                    map.put(widgetParameter.getName(), widgetParameter.getValue());
                }
                widgetConfiguration.setParameters(map);
                return widgetConfiguration;
            }
            case STEAM_GAME_NEWS -> {
                SteamGameNewsWidgetConfiguration widgetConfiguration = new SteamGameNewsWidgetConfiguration();
                widgetConfiguration = (SteamGameNewsWidgetConfiguration) setWidgetConf(widgetConfiguration, widget);
                ArrayList<WidgetParameter> widgetParameters = widgetParameterDAO.findByWidget(widget);
                Map<String, Object> map = new HashMap<>();
                for (WidgetParameter widgetParameter : widgetParameters) {
                    map.put(widgetParameter.getName(), widgetParameter.getValue());
                }
                widgetConfiguration.setParameters(map);
                return widgetConfiguration;
            }
            case STEAM_GAME_POPULATION -> {
                SteamGamePopulationWidgetConfiguration widgetConfiguration = new SteamGamePopulationWidgetConfiguration();
                widgetConfiguration =
                    (SteamGamePopulationWidgetConfiguration) setWidgetConf(widgetConfiguration, widget);
                ArrayList<WidgetParameter> widgetParameters = widgetParameterDAO.findByWidget(widget);
                Map<String, Object> map = new HashMap<>();
                for (WidgetParameter widgetParameter : widgetParameters) {
                    map.put(widgetParameter.getName(), widgetParameter.getValue());
                }
                widgetConfiguration.setParameters(map);
                return widgetConfiguration;
            }
            case TWITCH_FOLLOWED_CHANNELS -> {
                TwitchFollowedChannelsWidgetConfiguration widgetConfiguration = new TwitchFollowedChannelsWidgetConfiguration();
                widgetConfiguration =
                    (TwitchFollowedChannelsWidgetConfiguration) setWidgetConf(widgetConfiguration, widget);
                ArrayList<WidgetParameter> widgetParameters = widgetParameterDAO.findByWidget(widget);
                Map<String, Object> map = new HashMap<>();
                for (WidgetParameter widgetParameter : widgetParameters) {
                    map.put(widgetParameter.getName(), widgetParameter.getValue());
                }
                widgetConfiguration.setParameters(map);
                return widgetConfiguration;
            }
            case WEATHER_TIME_DEFAULT -> {
                WeatherTimeWidgetConfiguration widgetConfiguration = new WeatherTimeWidgetConfiguration();
                widgetConfiguration = (WeatherTimeWidgetConfiguration) setWidgetConf(widgetConfiguration, widget);
                ArrayList<WidgetParameter> widgetParameters = widgetParameterDAO.findByWidget(widget);
                Map<String, Object> map = new HashMap<>();
                for (WidgetParameter widgetParameter : widgetParameters) {
                    map.put(widgetParameter.getName(), widgetParameter.getValue());
                }
                widgetConfiguration.setParameters(map);
                return widgetConfiguration;
            }
            case YOUTUBE_CHANNEL_STATISTICS -> {
                YouTubeChannelStatisticsWidgetConfiguration widgetConfiguration = new YouTubeChannelStatisticsWidgetConfiguration();
                widgetConfiguration =
                    (YouTubeChannelStatisticsWidgetConfiguration) setWidgetConf(widgetConfiguration, widget);
                ArrayList<WidgetParameter> widgetParameters = widgetParameterDAO.findByWidget(widget);
                Map<String, Object> map = new HashMap<>();
                for (WidgetParameter widgetParameter : widgetParameters) {
                    map.put(widgetParameter.getName(), widgetParameter.getValue());
                }
                widgetConfiguration.setParameters(map);
                return widgetConfiguration;
            }
            case YOUTUBE_SUBSCRIBED_CHANNELS -> {
                YouTubeSubscribedChannelsWidgetConfiguration widgetConfiguration = new YouTubeSubscribedChannelsWidgetConfiguration();
                widgetConfiguration =
                    (YouTubeSubscribedChannelsWidgetConfiguration) setWidgetConf(widgetConfiguration, widget);
                ArrayList<WidgetParameter> widgetParameters = widgetParameterDAO.findByWidget(widget);
                Map<String, Object> map = new HashMap<>();
                for (WidgetParameter widgetParameter : widgetParameters) {
                    map.put(widgetParameter.getName(), widgetParameter.getValue());
                }
                widgetConfiguration.setParameters(map);
                return widgetConfiguration;
            }
            case YOUTUBE_VIDEO_STATISTICS -> {
                YouTubeVideoStatisticsWidgetConfiguration widgetConfiguration = new YouTubeVideoStatisticsWidgetConfiguration();
                widgetConfiguration =
                    (YouTubeVideoStatisticsWidgetConfiguration) setWidgetConf(widgetConfiguration, widget);
                ArrayList<WidgetParameter> widgetParameters = widgetParameterDAO.findByWidget(widget);
                Map<String, Object> map = new HashMap<>();
                for (WidgetParameter widgetParameter : widgetParameters) {
                    map.put(widgetParameter.getName(), widgetParameter.getValue());
                }
                widgetConfiguration.setParameters(map);
                return widgetConfiguration;
            }
        }
        return null;
    }

    public WidgetConfiguration<?> setWidgetConf(WidgetConfiguration<?> widgetConfiguration, Widget widget) {
        widgetConfiguration.setType(widget.getType());
        widgetConfiguration.setPosition(widget.getPosition());
        widgetConfiguration.setColumnPos(widget.getColumn_pos());
        widgetConfiguration.setName(widget.getName());
        widgetConfiguration.setRefreshRate(widget.getRefreshRate());
        return widgetConfiguration;
    }

    public void createParameters(WidgetConfiguration<?> widgetConfiguration, Widget widget) {
        WidgetType widgetType = WidgetType.fromValue(widgetConfiguration.getType());
        switch (widgetType) {
            case EPIC_GAMES_FRIENDS_LIST -> widgetParameterDAO.save(
                new WidgetParameter(
                    widget,
                    "showOffline",
                    widgetConfiguration.getParameters().get("showOffline").toString()
                )
            );
            case RSS_FEED -> widgetParameterDAO.save(
                new WidgetParameter(widget, "url", widgetConfiguration.getParameters().get("url").toString())
            );
            case STEAM_FRIENDS_LIST -> widgetParameterDAO.save(
                new WidgetParameter(
                    widget,
                    "showOffline",
                    widgetConfiguration.getParameters().get("showOffline").toString()
                )
            );
            case STEAM_GAME_NEWS -> widgetParameterDAO.save(
                new WidgetParameter(
                    widget,
                    "gameNameOrId",
                    widgetConfiguration.getParameters().get("gameNameOrId").toString()
                )
            );
            case STEAM_GAME_POPULATION -> widgetParameterDAO.save(
                new WidgetParameter(
                    widget,
                    "gameNameOrId",
                    widgetConfiguration.getParameters().get("gameNameOrId").toString()
                )
            );
            case TWITCH_FOLLOWED_CHANNELS -> widgetParameterDAO.save(
                new WidgetParameter(widget, "filter", widgetConfiguration.getParameters().get("filter").toString())
            );
            case WEATHER_TIME_DEFAULT -> widgetParameterDAO.save(
                new WidgetParameter(widget, "location", widgetConfiguration.getParameters().get("location").toString())
            );
            case YOUTUBE_CHANNEL_STATISTICS -> widgetParameterDAO.save(
                new WidgetParameter(widget, "channel", widgetConfiguration.getParameters().get("channel").toString())
            );
            case YOUTUBE_SUBSCRIBED_CHANNELS -> {
                widgetParameterDAO.save(
                    new WidgetParameter(widget, "filter", widgetConfiguration.getParameters().get("filter").toString())
                );
            }
            case YOUTUBE_VIDEO_STATISTICS -> widgetParameterDAO.save(
                new WidgetParameter(widget, "video", widgetConfiguration.getParameters().get("video").toString())
            );
        }
    }

    public void PatchParameters(WidgetConfiguration<?> widgetConfiguration, Widget widget) {
        WidgetType widgetType = WidgetType.fromValue(widgetConfiguration.getType());
        switch (widgetType) {
            case EPIC_GAMES_FRIENDS_LIST -> {
                WidgetParameter widgetParameter = widgetParameterDAO.findByWidget(widget).get(0);
                widgetParameter.setValue(widgetConfiguration.getParameters().get("showOffline").toString());
                widgetParameterDAO.save(widgetParameter);
            }
            case RSS_FEED -> {
                WidgetParameter widgetParameter = widgetParameterDAO.findByWidget(widget).get(0);
                widgetParameter.setValue(widgetConfiguration.getParameters().get("url").toString());
                widgetParameterDAO.save(widgetParameter);
            }
            case STEAM_FRIENDS_LIST -> {
                WidgetParameter widgetParameter = widgetParameterDAO.findByWidget(widget).get(0);
                widgetParameter.setValue(widgetConfiguration.getParameters().get("showOffline").toString());
                widgetParameterDAO.save(widgetParameter);
            }
            case STEAM_GAME_NEWS -> {
                WidgetParameter widgetParameter = widgetParameterDAO.findByWidget(widget).get(0);
                widgetParameter.setValue(widgetConfiguration.getParameters().get("gameNameOrId").toString());
                widgetParameterDAO.save(widgetParameter);
            }
            case STEAM_GAME_POPULATION -> {
                WidgetParameter widgetParameter = widgetParameterDAO.findByWidget(widget).get(0);
                widgetParameter.setValue(widgetConfiguration.getParameters().get("gameNameOrId").toString());
                widgetParameterDAO.save(widgetParameter);
            }
            case TWITCH_FOLLOWED_CHANNELS -> {
                WidgetParameter widgetParameter = widgetParameterDAO.findByWidget(widget).get(0);
                widgetParameter.setValue(widgetConfiguration.getParameters().get("filter").toString());
                widgetParameterDAO.save(widgetParameter);
            }
            case WEATHER_TIME_DEFAULT -> {
                WidgetParameter widgetParameter = widgetParameterDAO.findByWidget(widget).get(0);
                widgetParameter.setValue(widgetConfiguration.getParameters().get("location").toString());
                widgetParameterDAO.save(widgetParameter);
            }
            case YOUTUBE_CHANNEL_STATISTICS -> {
                WidgetParameter widgetParameter = widgetParameterDAO.findByWidget(widget).get(0);
                widgetParameter.setValue(widgetConfiguration.getParameters().get("channel").toString());
                widgetParameterDAO.save(widgetParameter);
            }
            case YOUTUBE_SUBSCRIBED_CHANNELS -> {
                WidgetParameter widgetParameter = widgetParameterDAO.findByWidget(widget).get(0);
                widgetParameter.setValue(widgetConfiguration.getParameters().get("filter").toString());
                widgetParameterDAO.save(widgetParameter);
            }
            case YOUTUBE_VIDEO_STATISTICS -> {
                WidgetParameter widgetParameter = widgetParameterDAO.findByWidget(widget).get(0);
                widgetParameter.setValue(widgetConfiguration.getParameters().get("video").toString());
                widgetParameterDAO.save(widgetParameter);
            }
        }
    }
}
