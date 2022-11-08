package com.github.dashframe.models.json;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.dashframe.models.json.WidgetParameterDescription;
import java.net.URI;
import java.time.OffsetDateTime;
import java.util.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.annotation.Generated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * WidgetDescription
 */

@Generated(
    value = "org.openapitools.codegen.languages.SpringCodegen",
    date = "2022-11-08T16:03:51.937911500+01:00[Europe/Paris]"
)
public class WidgetDescription {

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("params")
    @Valid
    private List<WidgetParameterDescription> params = new ArrayList<>();

    public WidgetDescription name(String name) {
        this.name = name;
        return this;
    }

    /**
     * Indicates the identifier of this Widget
     * @return name
     */
    @NotNull
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public WidgetDescription description(String description) {
        this.description = description;
        return this;
    }

    /**
     * Get description
     * @return description
     */
    @NotNull
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public WidgetDescription params(List<WidgetParameterDescription> params) {
        this.params = params;
        return this;
    }

    public WidgetDescription addParamsItem(WidgetParameterDescription paramsItem) {
        if (this.params == null) {
            this.params = new ArrayList<>();
        }
        this.params.add(paramsItem);
        return this;
    }

    /**
     * Indicates the list of parameters to configure this Widget
     * @return params
     */
    @NotNull
    @Valid
    public List<WidgetParameterDescription> getParams() {
        return params;
    }

    public void setParams(List<WidgetParameterDescription> params) {
        this.params = params;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        WidgetDescription widgetDescription = (WidgetDescription) o;
        return (
            Objects.equals(this.name, widgetDescription.name) &&
            Objects.equals(this.description, widgetDescription.description) &&
            Objects.equals(this.params, widgetDescription.params)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, params);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class WidgetDescription {\n");
        sb.append("    name: ").append(toIndentedString(name)).append("\n");
        sb.append("    description: ").append(toIndentedString(description)).append("\n");
        sb.append("    params: ").append(toIndentedString(params)).append("\n");
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
