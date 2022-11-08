package com.github.dashframe.models.json;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.net.URI;
import java.time.OffsetDateTime;
import java.util.*;
import java.util.Objects;
import javax.annotation.Generated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Common properties shared by any server-sent event, not a full event by itself
 */

@Generated(
    value = "org.openapitools.codegen.languages.SpringCodegen",
    date = "2022-11-08T15:27:52.118173200+01:00[Europe/Paris]"
)
public class BaseEvent {

    @JsonProperty("type")
    private String type;

    @JsonProperty("timestamp")
    private Long timestamp;

    public BaseEvent type(String type) {
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

    public BaseEvent timestamp(Long timestamp) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BaseEvent baseEvent = (BaseEvent) o;
        return Objects.equals(this.type, baseEvent.type) && Objects.equals(this.timestamp, baseEvent.timestamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, timestamp);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class BaseEvent {\n");
        sb.append("    type: ").append(toIndentedString(type)).append("\n");
        sb.append("    timestamp: ").append(toIndentedString(timestamp)).append("\n");
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
