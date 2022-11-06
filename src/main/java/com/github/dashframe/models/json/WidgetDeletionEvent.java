package com.github.dashframe.models.json;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.github.dashframe.models.json.WidgetDeletionEventAllOfData;
import java.net.URI;
import java.time.OffsetDateTime;
import java.util.*;
import java.util.Objects;
import javax.annotation.Generated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * WidgetDeletionEvent
 */

@Generated(
    value = "org.openapitools.codegen.languages.SpringCodegen",
    date = "2022-11-06T17:27:32.778551651+01:00[Europe/Paris]"
)
public class WidgetDeletionEvent implements Event {

    @JsonProperty("type")
    private String type;

    @JsonProperty("timestamp")
    private Long timestamp;

    @JsonProperty("data")
    private WidgetDeletionEventAllOfData data;

    public WidgetDeletionEvent type(String type) {
        this.type = type;
        return this;
    }

    /**
     * The type of event, one of the values of EventType
     * @return type
     */

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public WidgetDeletionEvent timestamp(Long timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    /**
     * The UNIX timestamp of the event creation
     * @return timestamp
     */
    @NotNull
    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public WidgetDeletionEvent data(WidgetDeletionEventAllOfData data) {
        this.data = data;
        return this;
    }

    /**
     * Get data
     * @return data
     */
    @NotNull
    @Valid
    public WidgetDeletionEventAllOfData getData() {
        return data;
    }

    public void setData(WidgetDeletionEventAllOfData data) {
        this.data = data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        WidgetDeletionEvent widgetDeletionEvent = (WidgetDeletionEvent) o;
        return (
            Objects.equals(this.type, widgetDeletionEvent.type) &&
            Objects.equals(this.timestamp, widgetDeletionEvent.timestamp) &&
            Objects.equals(this.data, widgetDeletionEvent.data)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, timestamp, data);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class WidgetDeletionEvent {\n");
        sb.append("    type: ").append(toIndentedString(type)).append("\n");
        sb.append("    timestamp: ").append(toIndentedString(timestamp)).append("\n");
        sb.append("    data: ").append(toIndentedString(data)).append("\n");
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
