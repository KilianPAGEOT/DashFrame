package com.github.dashframe.models.json;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.github.dashframe.models.json.WidgetRefreshEventAllOfDataWidgets;
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

@JsonTypeName("WidgetRefreshEvent_allOf_data")
@Generated(
    value = "org.openapitools.codegen.languages.SpringCodegen",
    date = "2022-11-06T17:27:32.778551651+01:00[Europe/Paris]"
)
public class WidgetRefreshEventAllOfData {

    @JsonProperty("widgets")
    @Valid
    private List<WidgetRefreshEventAllOfDataWidgets> widgets = new ArrayList<>();

    public WidgetRefreshEventAllOfData widgets(List<WidgetRefreshEventAllOfDataWidgets> widgets) {
        this.widgets = widgets;
        return this;
    }

    public WidgetRefreshEventAllOfData addWidgetsItem(WidgetRefreshEventAllOfDataWidgets widgetsItem) {
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
    public List<WidgetRefreshEventAllOfDataWidgets> getWidgets() {
        return widgets;
    }

    public void setWidgets(List<WidgetRefreshEventAllOfDataWidgets> widgets) {
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
        WidgetRefreshEventAllOfData widgetRefreshEventAllOfData = (WidgetRefreshEventAllOfData) o;
        return Objects.equals(this.widgets, widgetRefreshEventAllOfData.widgets);
    }

    @Override
    public int hashCode() {
        return Objects.hash(widgets);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class WidgetRefreshEventAllOfData {\n");
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
