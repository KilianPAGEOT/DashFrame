package com.github.dashframe.service.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.dashframe.models.json.SteamGameNews;
import com.github.dashframe.models.json.SteamGameNewsFeed;
import com.github.dashframe.models.json.SteamGameNewsItem;
import com.github.dashframe.models.json.SteamUser;
import com.google.common.collect.ListMultimap;
import com.google.common.collect.MultimapBuilder;
import java.net.URI;
import java.util.*;
import org.springframework.lang.Nullable;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.HtmlUtils;
import reactor.core.publisher.Mono;

public class SteamApi {

    private final WebClient client;

    private final String apiKey;

    private static Mono<AppNamesIds> appNamesIds = null;

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

    public Mono<AppNamesIds> getAppList() {
        if (appNamesIds == null) {
            appNamesIds =
                this.client.get()
                    .uri("ISteamApps/GetAppList/v2/")
                    .retrieve()
                    .bodyToMono(GetAppListResponse.class)
                    .map(response -> {
                        ListMultimap<String, String> nameToIds = MultimapBuilder.hashKeys().arrayListValues().build();
                        Map<String, String> idToName = new HashMap<>();

                        for (var entry : response.appList.apps) {
                            var name = entry.name;
                            var id = entry.id;
                            nameToIds.put(name, id);
                            idToName.put(id, name);
                        }
                        return new AppNamesIds(nameToIds, idToName);
                    })
                    .cache();
        }
        return appNamesIds;
    }

    /**
     * Gets an app's ID from its name.
     */
    public Mono<List<String>> getAppId(String appName) {
        return this.getAppList().mapNotNull(appNamesIds -> appNamesIds.nameToIds.get(appName));
    }

    /**
     * Gets an app's name from its ID.
     */
    public Mono<String> getAppName(String appId) {
        return this.getAppList().mapNotNull(appNamesIds -> appNamesIds.idToName.get(appId));
    }

    public Mono<SteamGameNews> getNewsForApp(String appId, long count) {
        return this.client.get()
            .uri(uriBuilder ->
                uriBuilder
                    .path("ISteamNews/GetNewsForApp/v0002")
                    .queryParam("appid", appId)
                    .queryParam("count", count)
                    .build()
            )
            .retrieve()
            .bodyToMono(GetNewsForAppResponse.class)
            .zipWith(this.getAppName(appId))
            .map(responseAndName -> {
                var response = responseAndName.getT1();
                var appName = responseAndName.getT2();
                return new SteamGameNews(
                    appName,
                    appId,
                    response.appNews.newsItems
                        .stream()
                        .map(news ->
                            new SteamGameNewsItem(
                                news.id,
                                news.title,
                                this.renderSteamFormattingToHtml(news.contents),
                                news.tags,
                                news.url,
                                news.author,
                                news.date,
                                news.feedLabel != null && news.feedName != null && news.feedType != null
                                    ? new SteamGameNewsFeed(news.feedLabel, news.feedName, news.feedType)
                                    : null
                            )
                        )
                        .toList()
                );
            });
    }

    /**
     * See <a href="https://steamcommunity.com/comment/Announcement/formattinghelp">he Steam formatting reference</a>
     */
    public String renderSteamFormattingToHtml(String steamFormattedText) {
        var escaped = HtmlUtils.htmlEscape(steamFormattedText);
        var rendered = new StringBuilder();
        int len = escaped.length();
        int pos = 0;

        do {
            int noParse = escaped.indexOf("[noparse]", pos);

            // quick n' dirty "parsing"
            rendered.append(
                escaped
                    .substring(pos, noParse < 0 ? len : noParse)
                    .replaceAll("\\[b]", "<b>")
                    .replaceAll("\\[/b]", "</b>")
                    .replaceAll("\\[u]", "<u>")
                    .replaceAll("\\[/u]", "</u>")
                    .replaceAll("\\[i]", "<i>")
                    .replaceAll("\\[/i]", "</i>")
                    .replaceAll("\\[strike]", "<span class=\"steam-strike\">")
                    .replaceAll("\\[/strike]", "</span>")
                    .replaceAll("\\[spoiler]", "<span class=\"steam-spoiler\">")
                    .replaceAll("\\[/spoiler]", "</span>")
                    .replaceAll("\\[list]", "<ul>")
                    .replaceAll("\\[/list]", "</ul>")
                    .replaceAll("\\[\\*]", "<li>")
                    .replaceAll("\\[/\\*]", "</li>")
                    .replaceAll("\\[hr]\\[/hr]", "<div class=\"steam-hr\"></div>")
                    .replaceAll("\\[url=([^]]*)]", "<a href=\"$1\">")
                    .replaceAll("\\[/url]", "</a>")
            );
            if (noParse >= 0) {
                int noParseEnd = escaped.indexOf("[/noparse]", noParse);
                noParseEnd = noParseEnd < 0 ? len : noParseEnd;
                rendered.append(escaped, noParse + 9, noParseEnd);
                pos = noParseEnd + 10;
            } else {
                pos = len;
            }
        } while (pos < len);
        return rendered.toString();
    }

    private record GetFriendsListResponse(@JsonProperty("friendslist") FriendsList friendsList) {}

    private record FriendsList(@JsonProperty("friends") List<SteamId> friends) {}

    /** Wrapped Steam ID object */
    private record SteamId(@JsonProperty("steamid") String steamId) {}

    private record GetPlayerSummariesResponse(@JsonProperty("response") PlayerList playerList) {}

    private record PlayerList(@JsonProperty("players") List<SteamUser.Raw> players) {}

    private record GetAppListResponse(@JsonProperty("applist") AppList appList) {}

    private record AppList(@JsonProperty("apps") List<App> apps) {}

    private record App(@JsonProperty("appid") String id, String name) {}

    private record GetNewsForAppResponse(@JsonProperty(value = "appnews", required = true) GameNews appNews) {}

    private record GameNews(
        @JsonProperty(value = "appid", required = true) String id,
        @JsonProperty(value = "newsitems", required = true) List<GameNewsItem> newsItems
    ) {}

    private record GameNewsItem(
        @JsonProperty(value = "gid", required = true) String id,
        @JsonProperty(value = "title", required = true) String title,
        @JsonProperty("url") @Nullable URI url,
        @JsonProperty("author") @Nullable String author,
        @JsonProperty(value = "contents", required = true) String contents,
        @JsonProperty("feedlabel") @Nullable String feedLabel,
        @JsonProperty("date") @Nullable Date date,
        @JsonProperty("feedname") @Nullable String feedName,
        @JsonProperty("feed_type") @Nullable Integer feedType,
        @JsonProperty(value = "tags", required = true) List<String> tags
    ) {}

    public record AppNamesIds(ListMultimap<String, String> nameToIds, Map<String, String> idToName) {}
}
