package com.github.dashframe.models.json;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import java.net.URI;
import java.time.OffsetDateTime;
import java.util.*;
import java.util.Objects;
import javax.annotation.Generated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * EpicGamesFreeGamesWidgetConfiguration
 */

@Generated(
    value = "org.openapitools.codegen.languages.SpringCodegen",
    date = "2022-11-06T17:27:32.778551651+01:00[Europe/Paris]"
)
public class EpicGamesFreeGamesWidgetConfiguration implements WidgetConfiguration {

    @JsonProperty("type")
    private String type;

    @JsonProperty("name")
    private String name;

    @JsonProperty("refreshRate")
    private Integer refreshRate;

    public EpicGamesFreeGamesWidgetConfiguration type(String type) {
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

    public EpicGamesFreeGamesWidgetConfiguration name(String name) {
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

    public EpicGamesFreeGamesWidgetConfiguration refreshRate(Integer refreshRate) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        EpicGamesFreeGamesWidgetConfiguration epicGamesFreeGamesWidgetConfiguration = (EpicGamesFreeGamesWidgetConfiguration) o;
        return (
            Objects.equals(this.type, epicGamesFreeGamesWidgetConfiguration.type) &&
            Objects.equals(this.name, epicGamesFreeGamesWidgetConfiguration.name) &&
            Objects.equals(this.refreshRate, epicGamesFreeGamesWidgetConfiguration.refreshRate)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, name, refreshRate);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class EpicGamesFreeGamesWidgetConfiguration {\n");
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
