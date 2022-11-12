package com.github.dashframe.service.widget.steam;

import com.fasterxml.jackson.annotation.JsonProperty;
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
        var client = widgetContext.serviceContext.client();
        var apiKey = widgetContext.serviceContext.apiKey();
        var steamid = widgetContext.serviceContext.steamid();

        return MonoUtil
            .ignoreExceptions(
                client
                    .get()
                    .uri(uriBuilder ->
                        uriBuilder
                            .path("ISteamUser/GetFriendList/v0001")
                            .queryParam("key", apiKey)
                            .queryParam("steamid", steamid)
                            .queryParam("relationship", "friend")
                            .build()
                    )
                    .retrieve()
                    .bodyToMono(FriendsListResponse.class),
                error -> this.logger.debug("Failed to fetch friends list of Steam user {}", steamid)
            )
            .flatMapMany(response -> {
                List<SteamId> rawFriends = response.friendsList.friends;
                // Steam's API expect a list of comma-separated steam IDs as a parameter
                String ids = rawFriends.stream().map(SteamId::steamId).collect(Collectors.joining(","));

                return MonoUtil
                    .ignoreExceptions(
                        client
                            .get()
                            .uri(uriBuilder ->
                                uriBuilder
                                    .path("ISteamUser/GetPlayerSummaries/v0002")
                                    .queryParam("key", apiKey)
                                    .queryParam("steamids", ids)
                                    .build()
                            )
                            .retrieve()
                            .bodyToMono(PlayerSummariesResponse.class),
                        error -> this.logger.debug("Failed to fetch summaries of Steam users {}", ids, error)
                    )
                    // convert the response to a flux of Raw Steam users for easier post-processing
                    .flatMapIterable(summaries -> summaries.response.players);
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

    private record FriendsListResponse(@JsonProperty("friendslist") FriendsList friendsList) {}

    private record FriendsList(List<SteamId> friends) {}

    private record SteamId(@JsonProperty("steamid") String steamId) {}

    private record PlayerSummariesResponse(PlayerList response) {}

    private record PlayerList(List<SteamUser.Raw> players) {}
}
