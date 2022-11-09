package com.github.dashframe.models.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;

import java.util.Objects;
import javax.annotation.Generated;
import javax.validation.constraints.*;

/**
 * BaseWidgetConfiguration
 */
@JsonSubTypes(
        {
                @JsonSubTypes.Type(value = EpicGamesFreeGamesWidgetConfiguration.class, name = "epic_games/free_games"),
                @JsonSubTypes.Type(value = EpicGamesFriendsListWidgetConfiguration.class, name = "epic_games/friends_list"),
                @JsonSubTypes.Type(value = RssFeedWidgetConfiguration.class, name = "rss/feed"),
                @JsonSubTypes.Type(value = SteamFriendsListWidgetConfiguration.class, name = "steam/friends_list"),
                @JsonSubTypes.Type(value = SteamGameNewsWidgetConfiguration.class, name = "steam/game_news"),
                @JsonSubTypes.Type(value = SteamGamePopulationWidgetConfiguration.class, name = "steam/game_population"),
                @JsonSubTypes.Type(value = TwitchFollowedChannelsWidgetConfiguration.class, name = "twitch/followed_channels"),
                @JsonSubTypes.Type(value = WeatherTimeWidgetConfiguration.class, name = "weather_time/default"),
                @JsonSubTypes.Type(
                        value = YouTubeChannelStatisticsWidgetConfiguration.class,
                        name = "youtube/channel_statistics"
                ),
                @JsonSubTypes.Type(
                        value = YouTubeSubscribedChannelsWidgetConfiguration.class,
                        name = "youtube/subsribed_channels"
                ),
                @JsonSubTypes.Type(value = YouTubeVideoStatisticsWidgetConfiguration.class, name = "youtube/video_statistics"),
        })
@Generated(
    value = "org.openapitools.codegen.languages.SpringCodegen",
    date = "2022-11-08T16:03:51.937911500+01:00[Europe/Paris]"
)
public class WidgetConfiguration<C>{

    @JsonProperty("type")
    private String type;

    @JsonProperty("name")
    private String name;

    @JsonProperty("refreshRate")
    private Integer refreshRate;

    @JsonProperty("position")
    private Integer position;

    @JsonProperty("columnPos")
    private Integer columnPos;

    public C type(String type) {
        this.type = type;
        return (C) this;
    }

    /**
     * The type of widget, one of the values of WidgetType
     * @return type
     */
    @NotNull
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public C name(String name) {
        this.name = name;
        return (C) this;
    }

    /**
     * Optional user-defined name for the widget
     * @return name
     */

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public C refreshRate(Integer refreshRate) {
        this.refreshRate = refreshRate;
        return (C) this;
    }

    /**
     * Refresh rate of the widget, in seconds
     * minimum: 1
     * @return refreshRate
     */
    @Min(1)
    public Integer getRefreshRate() {
        return refreshRate;
    }

    public void setRefreshRate(Integer refreshRate) {
        this.refreshRate = refreshRate;
    }

    @NotNull
    @Min(0)
    public int getPosition(){return position;}

    public void setPosition(int position){this.position=position;}
    @NotNull
    @Min(0)
    public int getColumnPos(){return columnPos;}
    public void setColumnPos(int columnPos){this.columnPos=columnPos;}
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        WidgetConfiguration widgetConfiguration = (WidgetConfiguration) o;
        return (
            Objects.equals(this.type, widgetConfiguration.type) &&
            Objects.equals(this.name, widgetConfiguration.name) &&
            Objects.equals(this.refreshRate, widgetConfiguration.refreshRate)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, name, refreshRate);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class BaseWidgetConfiguration {\n");
        sb.append("    type: ").append(toIndentedString(type)).append("\n");
        sb.append("    name: ").append(toIndentedString(name)).append("\n");
        sb.append("    refreshRate: ").append(toIndentedString(refreshRate)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}
