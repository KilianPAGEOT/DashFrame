package com.github.dashframe.models.json;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import java.net.URI;
import java.time.OffsetDateTime;
import java.util.*;
import java.util.Objects;
import javax.annotation.Generated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * WidgetRefreshEventAllOfDataWidgets
 */

@JsonTypeName("WidgetRefreshEvent_allOf_data_widgets")
@Generated(
    value = "org.openapitools.codegen.languages.SpringCodegen",
    date = "2022-11-08T16:03:51.937911500+01:00[Europe/Paris]"
)
public class WidgetRefreshEventAllOfDataWidgets {

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("data")
    private Object data;

    public WidgetRefreshEventAllOfDataWidgets id(Integer id) {
        this.id = id;
        return this;
    }

    /**
     * A resource identifier
     * minimum: 0
     * @return id
     */
    @NotNull
    @Min(0)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public WidgetRefreshEventAllOfDataWidgets data(Object data) {
        this.data = data;
        return this;
    }

    /**
     * Widget-specific data
     * @return data
     */
    @NotNull
    public Object getData() {
        return data;
    }

    public void setData(Object data) {
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
        WidgetRefreshEventAllOfDataWidgets widgetRefreshEventAllOfDataWidgets = (WidgetRefreshEventAllOfDataWidgets) o;
        return (
            Objects.equals(this.id, widgetRefreshEventAllOfDataWidgets.id) &&
            Objects.equals(this.data, widgetRefreshEventAllOfDataWidgets.data)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, data);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class WidgetRefreshEventAllOfDataWidgets {\n");
        sb.append("    id: ").append(toIndentedString(id)).append("\n");
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
