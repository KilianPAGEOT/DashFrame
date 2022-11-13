package com.github.dashframe.service.widget.steam;

import com.github.dashframe.models.Widget;
import com.github.dashframe.models.json.SteamGameNews;
import com.github.dashframe.models.json.WidgetType;
import com.github.dashframe.service.api.SteamApi;
import com.github.dashframe.service.service.SteamServiceManager;
import com.github.dashframe.service.widget.AbstractWidgetManager;
import com.github.dashframe.service.widget.WidgetContext;
import java.util.regex.Pattern;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class SteamGameNewsWidgetManager
    extends AbstractWidgetManager<SteamServiceManager.Context, SteamGameNewsWidgetManager.Context, SteamGameNews> {

    private static final Pattern APP_ID_PATTERN = Pattern.compile("^\\d+");

    @Override
    public WidgetType getType() {
        return WidgetType.STEAM_GAME_NEWS;
    }

    @Override
    public Mono<Context> open(Widget widget, SteamServiceManager.Context serviceContext) {
        var api = serviceContext.api();

        var gameNameOrId = widget.getParameterOrThrow("gameNameOrId").getValue().trim();
        var newsCount = widget.getParameterAsLong("newsCount", 5);

        Mono<String> appId;

        if (!APP_ID_PATTERN.matcher(gameNameOrId).matches()) {
            // if gameNameOrId is not numeric, try to search the name in the games database and get the first match
            appId = api.getAppId(gameNameOrId).map(ids -> ids.get(0));
        } else {
            appId = Mono.just(gameNameOrId);
        }

        return appId.map(id -> new Context(widget, api, id, newsCount));
    }

    @Override
    public Mono<SteamGameNews> fetchData(Context widgetContext) {
        return widgetContext.api.getNewsForApp(widgetContext.appId, widgetContext.newsCount);
    }

    public record Context(Widget widget, SteamApi api, String appId, long newsCount) implements WidgetContext {}
}
