package com.github.dashframe.models.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.net.URI;
import java.util.Date;
import java.util.List;
import org.springframework.lang.Nullable;

/**
 *
 * @param id
 * @param title
 * @param contents Contents of this article as HTML
 * @param tags
 * @param url
 * @param author
 * @param date
 * @param feed
 */
public record SteamGameNewsItem(
    @JsonProperty(value = "id", required = true) String id,
    @JsonProperty(value = "title", required = true) String title,
    @JsonProperty(value = "contents", required = true) String contents,
    @JsonProperty(value = "tags", required = true) List<String> tags,
    @JsonProperty("url") @Nullable URI url,
    @JsonProperty("author") @Nullable String author,
    @JsonProperty("date") @Nullable Date date,
    @JsonProperty("feed") @Nullable SteamGameNewsFeed feed
) {}
