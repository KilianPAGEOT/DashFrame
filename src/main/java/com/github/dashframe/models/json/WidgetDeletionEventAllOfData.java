package com.github.dashframe.models.json;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
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

@JsonTypeName("WidgetDeletionEvent_allOf_data")
@Generated(
    value = "org.openapitools.codegen.languages.SpringCodegen",
    date = "2022-11-08T15:27:52.118173200+01:00[Europe/Paris]"
)
public class WidgetDeletionEventAllOfData {

    @JsonProperty("widgetIds")
    @Valid
    private List<Integer> widgetIds = new ArrayList<>();

    public WidgetDeletionEventAllOfData widgetIds(List<Integer> widgetIds) {
        this.widgetIds = widgetIds;
        return this;
    }

    public WidgetDeletionEventAllOfData addWidgetIdsItem(Integer widgetIdsItem) {
        if (this.widgetIds == null) {
            this.widgetIds = new ArrayList<>();
        }
        this.widgetIds.add(widgetIdsItem);
        return this;
    }

    /**
     * The IDs of the widgets to be deleted
     * @return widgetIds
     */
    @NotNull
    public List<Integer> getWidgetIds() {
        return widgetIds;
    }

    public void setWidgetIds(List<Integer> widgetIds) {
        this.widgetIds = widgetIds;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        WidgetDeletionEventAllOfData widgetDeletionEventAllOfData = (WidgetDeletionEventAllOfData) o;
        return Objects.equals(this.widgetIds, widgetDeletionEventAllOfData.widgetIds);
    }

    @Override
    public int hashCode() {
        return Objects.hash(widgetIds);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class WidgetDeletionEventAllOfData {\n");
        sb.append("    widgetIds: ").append(toIndentedString(widgetIds)).append("\n");
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
