package com.github.dashframe.models.json;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.github.dashframe.models.json.YouTubeVideoStatisticsWidgetConfigurationAllOfParameters;
import java.net.URI;
import java.time.OffsetDateTime;
import java.util.*;
import java.util.Objects;
import javax.annotation.Generated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Configuration for the YouTube video statistics
 */

@JsonTypeName("YouTubeVideoStatisticsWidgetConfiguration_allOf")
@Generated(
    value = "org.openapitools.codegen.languages.SpringCodegen",
    date = "2022-11-08T16:03:51.937911500+01:00[Europe/Paris]"
)
public class YouTubeVideoStatisticsWidgetConfigurationAllOf {

    @JsonProperty("parameters")
    private YouTubeVideoStatisticsWidgetConfigurationAllOfParameters parameters;

    public YouTubeVideoStatisticsWidgetConfigurationAllOf parameters(
        YouTubeVideoStatisticsWidgetConfigurationAllOfParameters parameters
    ) {
        this.parameters = parameters;
        return this;
    }

    /**
     * Get parameters
     * @return parameters
     */
    @NotNull
    @Valid
    public YouTubeVideoStatisticsWidgetConfigurationAllOfParameters getParameters() {
        return parameters;
    }

    public void setParameters(YouTubeVideoStatisticsWidgetConfigurationAllOfParameters parameters) {
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
        YouTubeVideoStatisticsWidgetConfigurationAllOf youTubeVideoStatisticsWidgetConfigurationAllOf = (YouTubeVideoStatisticsWidgetConfigurationAllOf) o;
        return Objects.equals(this.parameters, youTubeVideoStatisticsWidgetConfigurationAllOf.parameters);
    }

    @Override
    public int hashCode() {
        return Objects.hash(parameters);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class YouTubeVideoStatisticsWidgetConfigurationAllOf {\n");
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
