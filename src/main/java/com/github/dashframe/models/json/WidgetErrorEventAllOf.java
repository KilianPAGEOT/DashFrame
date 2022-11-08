package com.github.dashframe.models.json;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.github.dashframe.models.json.WidgetErrorEventAllOfData;
import java.net.URI;
import java.time.OffsetDateTime;
import java.util.*;
import java.util.Objects;
import javax.annotation.Generated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Widget error event
 */

@JsonTypeName("WidgetErrorEvent_allOf")
@Generated(
    value = "org.openapitools.codegen.languages.SpringCodegen",
    date = "2022-11-08T16:03:51.937911500+01:00[Europe/Paris]"
)
public class WidgetErrorEventAllOf {

    @JsonProperty("data")
    private WidgetErrorEventAllOfData data;

    public WidgetErrorEventAllOf data(WidgetErrorEventAllOfData data) {
        this.data = data;
        return this;
    }

    /**
     * Get data
     * @return data
     */
    @NotNull
    @Valid
    public WidgetErrorEventAllOfData getData() {
        return data;
    }

    public void setData(WidgetErrorEventAllOfData data) {
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
        WidgetErrorEventAllOf widgetErrorEventAllOf = (WidgetErrorEventAllOf) o;
        return Objects.equals(this.data, widgetErrorEventAllOf.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(data);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class WidgetErrorEventAllOf {\n");
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
