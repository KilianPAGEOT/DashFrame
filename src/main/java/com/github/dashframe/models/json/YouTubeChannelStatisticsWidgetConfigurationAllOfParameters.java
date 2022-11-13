package com.github.dashframe.models.json;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import java.net.URI;
import java.time.OffsetDateTime;
import java.util.*;
import java.util.Objects;
import javax.annotation.Generated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * YouTubeChannelStatisticsWidgetConfigurationAllOfParameters
 */

@JsonTypeName("YouTubeChannelStatisticsWidgetConfiguration_allOf_parameters")
@Generated(
    value = "org.openapitools.codegen.languages.SpringCodegen",
    date = "2022-11-08T16:03:51.937911500+01:00[Europe/Paris]"
)
public class YouTubeChannelStatisticsWidgetConfigurationAllOfParameters extends WidgetConfigurationParameters {

    @JsonProperty("channel")
    private String channel;

    public YouTubeChannelStatisticsWidgetConfigurationAllOfParameters channel(String channel) {
        this.channel = channel;
        return this;
    }

    /**
     * Get channel
     * @return channel
     */
    @NotNull
    @Size(min = 1)
    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        YouTubeChannelStatisticsWidgetConfigurationAllOfParameters youTubeChannelStatisticsWidgetConfigurationAllOfParameters = (YouTubeChannelStatisticsWidgetConfigurationAllOfParameters) o;
        return Objects.equals(this.channel, youTubeChannelStatisticsWidgetConfigurationAllOfParameters.channel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(channel);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class YouTubeChannelStatisticsWidgetConfigurationAllOfParameters {\n");
        sb.append("    channel: ").append(toIndentedString(channel)).append("\n");
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
        return List.of(describeParameter("channel", WidgetParameterDescription.TypeEnum.STRING));
    }
}
