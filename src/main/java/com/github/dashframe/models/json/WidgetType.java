package com.github.dashframe.models.json;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.annotation.JsonValue;
import java.net.URI;
import java.time.OffsetDateTime;
import java.util.*;
import java.util.Objects;
import javax.annotation.Generated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Gets or Sets WidgetType
 */

@Generated(
    value = "org.openapitools.codegen.languages.SpringCodegen",
    date = "2022-11-06T17:27:32.778551651+01:00[Europe/Paris]"
)
public enum WidgetType {
    EPIC_GAMES_FREE_GAMES("epic_games/free_games"),

    EPIC_GAMES_FRIENDS_LIST("epic_games/friends_list"),

    RSS_FEED("rss/feed"),

    STEAM_FRIENDS_LIST("steam/friends_list"),

    STEAM_GAME_NEWS("steam/game_news"),

    STEAM_GAME_POPULATION("steam/game_population"),

    TWITCH_FOLLOWED_CHANNELS("twitch/followed_channels"),

    WEATHER_TIME_DEFAULT("weather_time/default"),

    YOUTUBE_CHANNEL_STATISTICS("youtube/channel_statistics"),

    YOUTUBE_SUBSRIBED_CHANNELS("youtube/subsribed_channels"),

    YOUTUBE_VIDEO_STATISTICS("youtube/video_statistics");

    private String value;

    WidgetType(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    @JsonCreator
    public static WidgetType fromValue(String value) {
        for (WidgetType b : WidgetType.values()) {
            if (b.value.equals(value)) {
                return b;
            }
        }
        throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
}
