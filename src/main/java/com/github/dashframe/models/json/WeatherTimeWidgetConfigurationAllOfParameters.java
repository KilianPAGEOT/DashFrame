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
 * WeatherTimeWidgetConfigurationAllOfParameters
 */

@JsonTypeName("WeatherTimeWidgetConfiguration_allOf_parameters")
@Generated(
    value = "org.openapitools.codegen.languages.SpringCodegen",
    date = "2022-11-06T17:27:32.778551651+01:00[Europe/Paris]"
)
public class WeatherTimeWidgetConfigurationAllOfParameters {

    @JsonProperty("location")
    private String location;

    public WeatherTimeWidgetConfigurationAllOfParameters location(String location) {
        this.location = location;
        return this;
    }

    /**
     * City name or address
     * @return location
     */
    @NotNull
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        WeatherTimeWidgetConfigurationAllOfParameters weatherTimeWidgetConfigurationAllOfParameters = (WeatherTimeWidgetConfigurationAllOfParameters) o;
        return Objects.equals(this.location, weatherTimeWidgetConfigurationAllOfParameters.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(location);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class WeatherTimeWidgetConfigurationAllOfParameters {\n");
        sb.append("    location: ").append(toIndentedString(location)).append("\n");
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
