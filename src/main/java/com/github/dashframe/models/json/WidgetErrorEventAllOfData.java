package com.github.dashframe.models.json;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.github.dashframe.models.json.WidgetErrorEventAllOfDataWidgets;
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
 * Event data
 */

@JsonTypeName("WidgetErrorEvent_allOf_data")
@Generated(
    value = "org.openapitools.codegen.languages.SpringCodegen",
    date = "2022-11-08T16:03:51.937911500+01:00[Europe/Paris]"
)
public class WidgetErrorEventAllOfData {

    @JsonProperty("widgets")
    @Valid
    private List<WidgetErrorEventAllOfDataWidgets> widgets = new ArrayList<>();

    public WidgetErrorEventAllOfData widgets(List<WidgetErrorEventAllOfDataWidgets> widgets) {
        this.widgets = widgets;
        return this;
    }

    public WidgetErrorEventAllOfData addWidgetsItem(WidgetErrorEventAllOfDataWidgets widgetsItem) {
        if (this.widgets == null) {
            this.widgets = new ArrayList<>();
        }
        this.widgets.add(widgetsItem);
        return this;
    }

    /**
     * Get widgets
     * @return widgets
     */
    @NotNull
    @Valid
    public List<WidgetErrorEventAllOfDataWidgets> getWidgets() {
        return widgets;
    }

    public void setWidgets(List<WidgetErrorEventAllOfDataWidgets> widgets) {
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
        WidgetErrorEventAllOfData widgetErrorEventAllOfData = (WidgetErrorEventAllOfData) o;
        return Objects.equals(this.widgets, widgetErrorEventAllOfData.widgets);
    }

    @Override
    public int hashCode() {
        return Objects.hash(widgets);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class WidgetErrorEventAllOfData {\n");
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
