package com.github.dashframe.models.json;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.github.dashframe.models.json.WidgetConfiguration;
import java.net.URI;
import java.time.OffsetDateTime;
import java.util.*;
import java.util.Objects;
import javax.annotation.Generated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * CreateWidgetRequestAllOf
 */

@JsonTypeName("create_widget_request_allOf")
@Generated(
    value = "org.openapitools.codegen.languages.SpringCodegen",
    date = "2022-11-08T16:03:51.937911500+01:00[Europe/Paris]"
)
public class CreateWidgetRequestAllOf {

    @JsonProperty("serviceId")
    private Integer serviceId;

    @JsonProperty("config")
    private WidgetConfiguration config;

    public CreateWidgetRequestAllOf serviceId(Integer serviceId) {
        this.serviceId = serviceId;
        return this;
    }

    /**
     * A resource identifier
     * minimum: 0
     * @return serviceId
     */
    @NotNull
    @Min(0)
    public Integer getServiceId() {
        return serviceId;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }

    public CreateWidgetRequestAllOf config(WidgetConfiguration config) {
        this.config = config;
        return this;
    }

    /**
     * Get config
     * @return config
     */
    @NotNull
    @Valid
    public WidgetConfiguration getConfig() {
        return config;
    }

    public void setConfig(WidgetConfiguration config) {
        this.config = config;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CreateWidgetRequestAllOf createWidgetRequestAllOf = (CreateWidgetRequestAllOf) o;
        return (
            Objects.equals(this.serviceId, createWidgetRequestAllOf.serviceId) &&
            Objects.equals(this.config, createWidgetRequestAllOf.config)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(serviceId, config);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CreateWidgetRequestAllOf {\n");
        sb.append("    serviceId: ").append(toIndentedString(serviceId)).append("\n");
        sb.append("    config: ").append(toIndentedString(config)).append("\n");
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
