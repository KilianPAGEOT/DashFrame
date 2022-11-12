package com.github.dashframe.service.widget.steam;

import com.github.dashframe.models.Widget;
import com.github.dashframe.models.json.SteamUser;
import com.github.dashframe.models.json.WidgetType;
import com.github.dashframe.service.service.SteamServiceManager;
import com.github.dashframe.service.widget.AbstractWidgetManager;
import com.github.dashframe.service.widget.WidgetContext;
import com.github.dashframe.util.MonoUtil;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class SteamFriendsListWidgetManager
    extends AbstractWidgetManager<SteamServiceManager.Context, SteamFriendsListWidgetManager.Context, List<SteamUser>> {

    @Override
    public WidgetType getType() {
        return WidgetType.STEAM_FRIENDS_LIST;
    }

    @Override
    public Mono<Context> open(Widget widget, SteamServiceManager.Context serviceContext) {
        return Mono.just(new Context(serviceContext, widget));
    }

    @Override
    public Mono<List<SteamUser>> fetchData(Context widgetContext) {
        var api = widgetContext.serviceContext.api();
        var steamId = widgetContext.serviceContext.steamId();

        return MonoUtil
            .ignoreExceptions(
                api.getFriendsList(steamId),
                error -> this.logger.error("Failed to fetch friends list of Steam user {}", steamId, error)
            )
            .flatMapMany(friendsIds -> {
                // Steam's API expects a list of comma-separated steam IDs as a parameter
                String ids = String.join(",", friendsIds);

                return MonoUtil
                    .ignoreExceptions(
                        api.getPlayerSummaries(ids),
                        error -> this.logger.error("Failed to fetch summaries of Steam users {}", ids, error)
                    )
                    // convert the response to a flux of Steam IDs for easier post-processing
                    .flatMapIterable(Function.identity());
            })
            .map(raw ->
                // Convert the users returned by Steam's API to the one our front-end expects
                new SteamUser(
                    raw.steamId(),
                    raw.name(),
                    raw.profileURL(),
                    raw.avatar32x32(),
                    raw.avatar64x64(),
                    raw.avatar184x184(),
                    raw.status(),
                    // omit the `lastLogoff` field when the user is offline, or format it as a proper date otherwise
                    raw.status() == SteamUser.Status.ONLINE
                        ? Optional.empty()
                        : Optional.of(new Date(raw.lastLogoff() * 1000))
                )
            )
            .collect(Collectors.toList());
    }

    public record Context(SteamServiceManager.Context serviceContext, Widget widget) implements WidgetContext {}
}
