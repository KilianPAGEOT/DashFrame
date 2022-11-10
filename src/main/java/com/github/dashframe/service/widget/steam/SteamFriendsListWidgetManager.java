package com.github.dashframe.service.widget.steam;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.dashframe.models.Widget;
import com.github.dashframe.models.json.WidgetType;
import com.github.dashframe.service.service.SteamServiceManager;
import com.github.dashframe.service.widget.WidgetContext;
import com.github.dashframe.service.widget.WidgetManager;
import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class SteamFriendsListWidgetManager
    implements
        WidgetManager<SteamServiceManager.Context, SteamFriendsListWidgetManager.Context, List<SteamFriendsListWidgetManager.SteamFriend>> {

    @Override
    public WidgetType getType() {
        return WidgetType.STEAM_FRIENDS_LIST;
    }

    @Override
    public Mono<Context> open(Widget widget, SteamServiceManager.Context serviceContext) {
        return Mono.just(new Context(serviceContext, widget));
    }

    @Override
    public Mono<List<SteamFriend>> fetchData(Context widgetContext) {
        var client = widgetContext.serviceContext.client();
        var apiKey = widgetContext.serviceContext.apiKey();
        var steamid = widgetContext.serviceContext.steamid();

        return client
            .get()
            .uri(uriBuilder ->
                uriBuilder
                    .path("ISteamUser/GetFriendList/v0001")
                    .queryParam("key", apiKey)
                    .queryParam("steamid", steamid)
                    .build()
            )
            .retrieve()
            .bodyToMono(RawData.class)
            .map(rawData -> rawData.friendsList.friends);
    }

    public record Context(SteamServiceManager.Context serviceContext, Widget widget) implements WidgetContext {}

    private record RawData(@JsonProperty("friendslist") RawFriendsList friendsList) {}

    private record RawFriendsList(List<SteamFriend> friends) {}

    public record SteamFriend(
        String steamid,
        String relationship,
        @JsonFormat(shape = JsonFormat.Shape.NUMBER, pattern = "s") Date friendSince
    ) {}
}
