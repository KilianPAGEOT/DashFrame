package com.github.dashframe.models.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import java.util.Objects;
import javax.annotation.Generated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * CreateWidgetRequest
 */

@JsonTypeName("create_widget_request")
@Generated(
    value = "org.openapitools.codegen.languages.SpringCodegen",
    date = "2022-11-08T16:03:51.937911500+01:00[Europe/Paris]"
)
public class CreateWidgetRequest {

    @JsonProperty("userId")
    private Integer userId;

    @JsonProperty("serviceId")
    private Integer serviceId;

    @JsonProperty("position")
    private Integer position;

    @JsonProperty("columnPos")
    private Integer columnPos;

    @JsonProperty("config")
    private WidgetConfiguration config;

    public CreateWidgetRequest userId(Integer userId) {
        this.userId = userId;
        return this;
    }

    /**
     * A resource identifier
     * minimum: 0
     * @return userId
     */
    @Min(0)
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public CreateWidgetRequest serviceId(Integer serviceId) {
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

    public CreateWidgetRequest config(WidgetConfiguration config) {
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

    @NotNull
    @Min(0)
    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    @NotNull
    @Min(0)
    public int getColumnPos() {
        return columnPos;
    }

    public void setColumnPos(int columnPos) {
        this.columnPos = columnPos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CreateWidgetRequest createWidgetRequest = (CreateWidgetRequest) o;
        return (
            Objects.equals(this.userId, createWidgetRequest.userId) &&
            Objects.equals(this.serviceId, createWidgetRequest.serviceId) &&
            Objects.equals(this.config, createWidgetRequest.config)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, serviceId, config);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CreateWidgetRequest {\n");
        sb.append("    userId: ").append(toIndentedString(userId)).append("\n");
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
