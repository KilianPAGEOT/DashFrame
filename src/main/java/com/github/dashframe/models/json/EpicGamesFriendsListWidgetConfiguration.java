package com.github.dashframe.models.json;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.github.dashframe.models.json.EpicGamesFriendsListWidgetConfigurationAllOfParameters;
import java.net.URI;
import java.time.OffsetDateTime;
import java.util.*;
import java.util.Objects;
import javax.annotation.Generated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * EpicGamesFriendsListWidgetConfiguration
 */

@Generated(
    value = "org.openapitools.codegen.languages.SpringCodegen",
    date = "2022-11-08T15:27:52.118173200+01:00[Europe/Paris]"
)
public class EpicGamesFriendsListWidgetConfiguration implements WidgetConfiguration {

    @JsonProperty("type")
    private String type;

    @JsonProperty("name")
    private String name;

    @JsonProperty("refreshRate")
    private Integer refreshRate;

    @JsonProperty("parameters")
    private EpicGamesFriendsListWidgetConfigurationAllOfParameters parameters;

    public EpicGamesFriendsListWidgetConfiguration type(String type) {
        this.type = type;
        return this;
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

    public EpicGamesFriendsListWidgetConfiguration name(String name) {
        this.name = name;
        return this;
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

    public EpicGamesFriendsListWidgetConfiguration refreshRate(Integer refreshRate) {
        this.refreshRate = refreshRate;
        return this;
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

    public EpicGamesFriendsListWidgetConfiguration parameters(
        EpicGamesFriendsListWidgetConfigurationAllOfParameters parameters
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
    public EpicGamesFriendsListWidgetConfigurationAllOfParameters getParameters() {
        return parameters;
    }

    public void setParameters(EpicGamesFriendsListWidgetConfigurationAllOfParameters parameters) {
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
        EpicGamesFriendsListWidgetConfiguration epicGamesFriendsListWidgetConfiguration = (EpicGamesFriendsListWidgetConfiguration) o;
        return (
            Objects.equals(this.type, epicGamesFriendsListWidgetConfiguration.type) &&
            Objects.equals(this.name, epicGamesFriendsListWidgetConfiguration.name) &&
            Objects.equals(this.refreshRate, epicGamesFriendsListWidgetConfiguration.refreshRate) &&
            Objects.equals(this.parameters, epicGamesFriendsListWidgetConfiguration.parameters)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, name, refreshRate, parameters);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class EpicGamesFriendsListWidgetConfiguration {\n");
        sb.append("    type: ").append(toIndentedString(type)).append("\n");
        sb.append("    name: ").append(toIndentedString(name)).append("\n");
        sb.append("    refreshRate: ").append(toIndentedString(refreshRate)).append("\n");
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
