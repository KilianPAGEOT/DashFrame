package com.github.dashframe.models.json;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.dashframe.models.json.WidgetDescription;
import java.net.URI;
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
 * ServiceDescription
 */

@Generated(
    value = "org.openapitools.codegen.languages.SpringCodegen",
    date = "2022-11-06T17:27:32.778551651+01:00[Europe/Paris]"
)
public class ServiceDescription {

    @JsonProperty("name")
    private String name;

    @JsonProperty("iconUrl")
    private URI iconUrl;

    @JsonProperty("description")
    private String description;

    @JsonProperty("widgets")
    @Valid
    private List<WidgetDescription> widgets = new ArrayList<>();

    public ServiceDescription name(String name) {
        this.name = name;
        return this;
    }

    /**
     * Indicates the name of the Service
     * @return name
     */
    @NotNull
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ServiceDescription iconUrl(URI iconUrl) {
        this.iconUrl = iconUrl;
        return this;
    }

    /**
     * The image URL of the service
     * @return iconUrl
     */
    @Valid
    public URI getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(URI iconUrl) {
        this.iconUrl = iconUrl;
    }

    public ServiceDescription description(String description) {
        this.description = description;
        return this;
    }

    /**
     * Describes the service
     * @return description
     */

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ServiceDescription widgets(List<WidgetDescription> widgets) {
        this.widgets = widgets;
        return this;
    }

    public ServiceDescription addWidgetsItem(WidgetDescription widgetsItem) {
        if (this.widgets == null) {
            this.widgets = new ArrayList<>();
        }
        this.widgets.add(widgetsItem);
        return this;
    }

    /**
     * Indicates the list of Widgets supported by this Service
     * @return widgets
     */
    @NotNull
    @Valid
    public List<WidgetDescription> getWidgets() {
        return widgets;
    }

    public void setWidgets(List<WidgetDescription> widgets) {
        this.widgets = widgets;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ServiceDescription serviceDescription = (ServiceDescription) o;
        return (
            Objects.equals(this.name, serviceDescription.name) &&
            Objects.equals(this.iconUrl, serviceDescription.iconUrl) &&
            Objects.equals(this.description, serviceDescription.description) &&
            Objects.equals(this.widgets, serviceDescription.widgets)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, iconUrl, description, widgets);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ServiceDescription {\n");
        sb.append("    name: ").append(toIndentedString(name)).append("\n");
        sb.append("    iconUrl: ").append(toIndentedString(iconUrl)).append("\n");
        sb.append("    description: ").append(toIndentedString(description)).append("\n");
        sb.append("    widgets: ").append(toIndentedString(widgets)).append("\n");
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
