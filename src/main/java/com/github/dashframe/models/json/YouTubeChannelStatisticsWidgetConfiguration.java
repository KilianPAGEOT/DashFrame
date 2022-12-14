package com.github.dashframe.models.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;
import javax.annotation.Generated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * YouTubeChannelStatisticsWidgetConfiguration
 */

@Generated(
    value = "org.openapitools.codegen.languages.SpringCodegen",
    date = "2022-11-08T16:03:51.937911500+01:00[Europe/Paris]"
)
public class YouTubeChannelStatisticsWidgetConfiguration
    extends WidgetConfiguration<YouTubeChannelStatisticsWidgetConfiguration> {

    @JsonProperty("parameters")
    private YouTubeChannelStatisticsWidgetConfigurationAllOfParameters parameters;

    public YouTubeChannelStatisticsWidgetConfiguration parameters(
        YouTubeChannelStatisticsWidgetConfigurationAllOfParameters parameters
    ) {
        this.parameters = parameters;
        return this;
    }

    /**
     * Get parameters
     *
     * @return parameters
     */
    /*public @NotNull @Valid YouTubeChannelStatisticsWidgetConfigurationAllOfParameters getParameters() {
        return parameters;
    }*/

    public void setParameters(YouTubeChannelStatisticsWidgetConfigurationAllOfParameters parameters) {
        this.parameters = parameters;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        YouTubeChannelStatisticsWidgetConfiguration youTubeChannelStatisticsWidgetConfiguration = (YouTubeChannelStatisticsWidgetConfiguration) o;
        return (
            super.equals(o) && Objects.equals(this.parameters, youTubeChannelStatisticsWidgetConfiguration.parameters)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(getType(), getName(), getRefreshRate(), parameters);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class YouTubeChannelStatisticsWidgetConfiguration {\n");
        sb.append("    type: ").append(toIndentedString(getType())).append("\n");
        sb.append("    name: ").append(toIndentedString(getName())).append("\n");
        sb.append("    refreshRate: ").append(toIndentedString(getRefreshRate())).append("\n");
        sb.append("    parameters: ").append(toIndentedString(parameters)).append("\n");
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
