package com.github.dashframe.models.json;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.github.dashframe.models.json.ApiError;
import java.net.URI;
import java.time.OffsetDateTime;
import java.util.*;
import java.util.Objects;
import javax.annotation.Generated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * WidgetErrorEventAllOfDataWidgets
 */

@JsonTypeName("WidgetErrorEvent_allOf_data_widgets")
@Generated(
    value = "org.openapitools.codegen.languages.SpringCodegen",
    date = "2022-11-09T11:51:12.913786908+01:00[Europe/Paris]"
)
public class WidgetErrorEventAllOfDataWidgets {

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("error")
    private ApiError error;

    public WidgetErrorEventAllOfDataWidgets id(Integer id) {
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

    public WidgetErrorEventAllOfDataWidgets error(ApiError error) {
        this.error = error;
        return this;
    }

    /**
     * Get error
     * @return error
     */
    @NotNull
    @Valid
    public ApiError getError() {
        return error;
    }

    public void setError(ApiError error) {
        this.error = error;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        WidgetErrorEventAllOfDataWidgets widgetErrorEventAllOfDataWidgets = (WidgetErrorEventAllOfDataWidgets) o;
        return (
            Objects.equals(this.id, widgetErrorEventAllOfDataWidgets.id) &&
            Objects.equals(this.error, widgetErrorEventAllOfDataWidgets.error)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, error);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class WidgetErrorEventAllOfDataWidgets {\n");
        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("    error: ").append(toIndentedString(error)).append("\n");
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
