package com.github.dashframe.models.json;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import javax.annotation.Generated;

/**
 * Service unique identifier
 */

@Generated(
    value = "org.openapitools.codegen.languages.SpringCodegen",
    date = "2022-11-08T16:03:51.937911500+01:00[Europe/Paris]"
)
public enum ServiceType {
    EPIC_GAMES("epic_games", "The Epic Games Launcher"),

    RSS("rss", "RSS Feed"),

    STEAM("steam", "The Steam Launcher"),

    TWITCH("twitch", "The livestreaming platform"),

    WEATHER_TIME("weather_time", "Time & Date"),

    YOUTUBE("youtube", "All the videos");

    private final String value;

    private final String description;

    ServiceType(String value, String description) {
        this.value = value;
        this.description = description;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    public String getDescription() {
        return this.description;
    }

    @JsonCreator
    public static ServiceType fromValue(String value) {
        for (ServiceType b : ServiceType.values()) {
            if (b.value.equals(value)) {
                return b;
            }
        }
        throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
}
