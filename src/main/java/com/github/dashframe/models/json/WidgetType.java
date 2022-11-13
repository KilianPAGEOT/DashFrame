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
public enum WidgetType {
    EPIC_GAMES_FREE_GAMES(
        "epic_games/free_games",
        ServiceType.EPIC_GAMES,
        "A list of free games on the Epic Games Launcher"
    ),

    EPIC_GAMES_FRIENDS_LIST(
        "epic_games/friends_list",
        ServiceType.EPIC_GAMES,
        EpicGamesFriendsListWidgetConfigurationAllOfParameters.class,
        "Display your friends on Epic Games"
    ),

    RSS_FEED("rss/feed", ServiceType.RSS, RssFeedWidgetConfigurationAllOfParameters.class, "Display any RSS feed"),

    STEAM_FRIENDS_LIST(
        "steam/friends_list",
        ServiceType.STEAM,
        EpicGamesFriendsListWidgetConfigurationAllOfParameters.class,
        "Display your friends on Steam"
    ),

    STEAM_GAME_NEWS(
        "steam/game_news",
        ServiceType.STEAM,
        SteamGameNewsWidgetConfigurationAllOfParameters.class,
        """
            Display the latest and greatest news of a game of your choice.
            You can change how many entries your want displayed at a time.
            """
    ),

    STEAM_GAME_POPULATION(
        "steam/game_population",
        ServiceType.STEAM,
        SteamGameNewsWidgetConfigurationAllOfParameters.class,
        """
                    Display the population statistics of your favorite game!
                    Try not to fall into depression if your game barely has 1K daily users...
                    """
    ),

    TWITCH_FOLLOWED_CHANNELS(
        "twitch/followed_channels",
        ServiceType.TWITCH,
        TwitchFollowedChannelsWidgetConfigurationAllOfParameters.class,
        "Display your favorite streamers on Twitch and see who's live!"
    ),

    WEATHER_TIME_DEFAULT(
        "weather_time/default",
        ServiceType.WEATHER_TIME,
        WeatherTimeWidgetConfigurationAllOfParameters.class,
        "Display time and date of a specific location"
    ),

    YOUTUBE_CHANNEL_STATISTICS(
        "youtube/channel_statistics",
        ServiceType.YOUTUBE,
        YouTubeChannelStatisticsWidgetConfigurationAllOfParameters.class,
        "Display the statistics of a YouTube channel"
    ),

    YOUTUBE_SUBSCRIBED_CHANNELS(
        "youtube/subscribed_channels",
        ServiceType.YOUTUBE,
        TwitchFollowedChannelsWidgetConfigurationAllOfParameters.class,
        "Show the channels you are subscribed to"
    ),

    YOUTUBE_VIDEO_STATISTICS(
        "youtube/video_statistics",
        ServiceType.YOUTUBE,
        YouTubeVideoStatisticsWidgetConfigurationAllOfParameters.class,
        "Show the statistics of a specific video"
    );

    private final String value;

    private final ServiceType serviceType;

    private final List<WidgetParameterDescription> parameterDescriptions;

    private final String description;

    WidgetType(String value, ServiceType serviceType, String description) {
        this.value = value;
        this.serviceType = serviceType;
        this.parameterDescriptions = Collections.emptyList();
        this.description = description;
    }

    WidgetType(
        String value,
        ServiceType serviceType,
        Class<? extends WidgetConfigurationParameters> params,
        String description
    ) {
        this.value = value;
        this.serviceType = serviceType;
        List<WidgetParameterDescription> parameterDescriptions;
        try {
            parameterDescriptions = params.getConstructor().newInstance().describeParameters();
        } catch (ReflectiveOperationException ignored) {
            parameterDescriptions = Collections.emptyList();
        }
        this.parameterDescriptions = parameterDescriptions;
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

    public ServiceType getServiceType() {
        return this.serviceType;
    }

    public List<WidgetParameterDescription> getParameterDescriptions() {
        return this.parameterDescriptions;
    }

    public String getDescription() {
        return this.description;
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
