package com.github.dashframe.models.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import java.util.List;
import java.util.Objects;
import javax.annotation.Generated;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * SteamGameNewsWidgetConfigurationAllOfParameters
 */

@JsonTypeName("SteamGameNewsWidgetConfiguration_allOf_parameters")
@Generated(
    value = "org.openapitools.codegen.languages.SpringCodegen",
    date = "2022-11-08T16:03:51.937911500+01:00[Europe/Paris]"
)
public class SteamGameNewsWidgetConfigurationAllOfParameters extends WidgetConfigurationParameters {

    @JsonProperty("gameNameOrId")
    private String gameNameOrId;

    /** How many news entries you want to see */
    @JsonProperty("newsCount")
    private Integer newsCount;

    public SteamGameNewsWidgetConfigurationAllOfParameters gameNameOrId(String gameNameOrId) {
        this.gameNameOrId = gameNameOrId;
        return this;
    }

    public SteamGameNewsWidgetConfigurationAllOfParameters newsCount(Integer newsCount) {
        this.newsCount = newsCount;
        return this;
    }

    /**
     * Get gameNameOrId
     * @return gameNameOrId
     */
    @NotNull
    @Size(min = 1)
    public String getGameNameOrId() {
        return gameNameOrId;
    }

    /**
     * Get newsCount
     * @return newsCount
     */
    @NotNull
    @Min(1)
    public Integer getNewsCount() {
        return newsCount;
    }

    public void setGameNameOrId(String gameNameOrId) {
        this.gameNameOrId = gameNameOrId;
    }

    public void setNewsCount(Integer newsCount) {
        this.newsCount = newsCount;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || getClass() != other.getClass()) {
            return false;
        }
        var otherNews = (SteamGameNewsWidgetConfigurationAllOfParameters) other;
        return (
            Objects.equals(this.gameNameOrId, otherNews.gameNameOrId) &&
            Objects.equals(this.newsCount, otherNews.newsCount)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(gameNameOrId, newsCount);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class SteamGameNewsWidgetConfigurationAllOfParameters {\n");
        sb.append("    gameNameOrId: ").append(toIndentedString(gameNameOrId)).append("\n");
        sb.append("    newsCount: ").append(toIndentedString(newsCount)).append("\n");
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

    @Override
    public List<WidgetParameterDescription> describeParameters() {
        return List.of(describeParameter("gameNameOrId", WidgetParameterDescription.TypeEnum.STRING));
    }
}
