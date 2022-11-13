package com.github.dashframe.models.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 * The NEWS! Used as part of the widgets/refresh event.
 * <b>NOT GENERATED </b>
 *
 * @param name The game's name
 * @param id The game's app ID.
 * @param items A list of news items.
 */
public record SteamGameNews(
    @JsonProperty(value = "name", required = true) String name,
    @JsonProperty(value = "id", required = true) String id,
    @JsonProperty(value = "items", required = true) List<SteamGameNewsItem> items
) {}
