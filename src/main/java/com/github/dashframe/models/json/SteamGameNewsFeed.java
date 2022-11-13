package com.github.dashframe.models.json;

import com.fasterxml.jackson.annotation.JsonProperty;

/** <b>NOT GENERATED </b> */
public record SteamGameNewsFeed(
    @JsonProperty(value = "label", required = true) String label,
    @JsonProperty(value = "name", required = true) String name,
    @JsonProperty(value = "type", required = true) long type
) {}
