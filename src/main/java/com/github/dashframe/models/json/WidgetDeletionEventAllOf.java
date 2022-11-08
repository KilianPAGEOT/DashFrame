package com.github.dashframe.models.json;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.github.dashframe.models.json.WidgetDeletionEventAllOfData;
import java.net.URI;
import java.time.OffsetDateTime;
import java.util.*;
import java.util.Objects;
import javax.annotation.Generated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Forces a widget to be delete if it still exists on the client side
 */

@JsonTypeName("WidgetDeletionEvent_allOf")
@Generated(
    value = "org.openapitools.codegen.languages.SpringCodegen",
    date = "2022-11-08T16:03:51.937911500+01:00[Europe/Paris]"
)
public class WidgetDeletionEventAllOf {

    @JsonProperty("data")
    private WidgetDeletionEventAllOfData data;

    public WidgetDeletionEventAllOf data(WidgetDeletionEventAllOfData data) {
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
        WidgetDeletionEventAllOf widgetDeletionEventAllOf = (WidgetDeletionEventAllOf) o;
        return Objects.equals(this.data, widgetDeletionEventAllOf.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(data);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class WidgetDeletionEventAllOf {\n");
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
