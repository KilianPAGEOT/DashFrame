package com.github.dashframe.service.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.dashframe.models.json.SteamUser;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

public class SteamApi {

    private final WebClient client;

    private final String apiKey;

    private static Mono<Map<String, String>> appListCache = null;

    public SteamApi(WebClient client, String apiKey) {
        this.client = client;
        this.apiKey = apiKey;
    }

    public Mono<List<String>> getFriendsList(String steamId) {
        return this.client.get()
            .uri(uriBuilder ->
                uriBuilder
                    .path("ISteamUser/GetFriendList/v0001")
                    .queryParam("key", apiKey)
                    .queryParam("steamid", steamId)
                    .queryParam("relationship", "friend")
                    .build()
            )
            .retrieve()
            .bodyToMono(GetFriendsListResponse.class)
            .map(response -> response.friendsList.friends.stream().map(SteamId::steamId).toList());
    }

    public Mono<List<SteamUser.Raw>> getPlayerSummaries(String ids) {
        //noinspection SpellCheckingInspection
        return this.client.get()
            .uri(uriBuilder ->
                uriBuilder
                    .path("ISteamUser/GetPlayerSummaries/v0002")
                    .queryParam("key", apiKey)
                    .queryParam("steamids", ids)
                    .build()
            )
            .retrieve()
            .bodyToMono(GetPlayerSummariesResponse.class)
            .map(response -> response.playerList.players);
    }

    public Mono<Map<String, String>> getAppList() {
        if (appListCache == null) {
            appListCache =
                this.client.get()
                    .uri("ISteamApps/GetAppList/v2/")
                    .retrieve()
                    .bodyToMono(GetAppListResponse.class)
                    .map(response -> response.appList.apps.stream().collect(Collectors.toMap(App::name, App::id)))
                    .cache();
        }
        return appListCache;
    }

    /**
     * Gets an app's ID from its name.
     */
    public Mono<String> getAppId(String appName) {
        return this.getAppList().mapNotNull(appsNameToId -> appsNameToId.get(appName));
    }

    private record GetFriendsListResponse(@JsonProperty("friendslist") FriendsList friendsList) {}

    private record FriendsList(@JsonProperty("friends") List<SteamId> friends) {}

    /** Wrapped Steam ID object */
    private record SteamId(@JsonProperty("steamid") String steamId) {}

    private record GetPlayerSummariesResponse(@JsonProperty("response") PlayerList playerList) {}

    private record PlayerList(@JsonProperty("players") List<SteamUser.Raw> players) {}

    private record GetAppListResponse(@JsonProperty("applist") AppList appList) {}

    private record AppList(@JsonProperty("apps") List<App> apps) {}

    public record App(@JsonProperty("appid") String id, String name) {}
}
