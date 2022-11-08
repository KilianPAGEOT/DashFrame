package com.github.dashframe.models.json;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.github.dashframe.models.json.RssFeedWidgetConfigurationAllOfParameters;
import java.net.URI;
import java.time.OffsetDateTime;
import java.util.*;
import java.util.Objects;
import javax.annotation.Generated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Configuration for the RSS feed widget
 */

@JsonTypeName("RssFeedWidgetConfiguration_allOf")
@Generated(
    value = "org.openapitools.codegen.languages.SpringCodegen",
    date = "2022-11-08T15:27:52.118173200+01:00[Europe/Paris]"
)
public class RssFeedWidgetConfigurationAllOf {

    @JsonProperty("parameters")
    private RssFeedWidgetConfigurationAllOfParameters parameters;

    public RssFeedWidgetConfigurationAllOf parameters(RssFeedWidgetConfigurationAllOfParameters parameters) {
        this.parameters = parameters;
        return this;
    }

    /**
     * Get parameters
     * @return parameters
     */
    @NotNull
    @Valid
    public RssFeedWidgetConfigurationAllOfParameters getParameters() {
        return parameters;
    }

    public void setParameters(RssFeedWidgetConfigurationAllOfParameters parameters) {
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
        RssFeedWidgetConfigurationAllOf rssFeedWidgetConfigurationAllOf = (RssFeedWidgetConfigurationAllOf) o;
        return Objects.equals(this.parameters, rssFeedWidgetConfigurationAllOf.parameters);
    }

    @Override
    public int hashCode() {
        return Objects.hash(parameters);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class RssFeedWidgetConfigurationAllOf {\n");
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
