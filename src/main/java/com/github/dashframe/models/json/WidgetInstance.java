package com.github.dashframe.models.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.OffsetDateTime;
import java.util.Objects;
import javax.annotation.Generated;
import javax.validation.Valid;
import javax.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * WidgetInstance
 */

@Generated(
    value = "org.openapitools.codegen.languages.SpringCodegen",
    date = "2022-11-08T16:03:51.937911500+01:00[Europe/Paris]"
)
public class WidgetInstance {

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("serviceId")
    private Integer serviceId;

    @JsonProperty("position")
    private int position;

    @JsonProperty("columnPos")
    private Integer columnPos;

    @JsonProperty("config")
    private WidgetConfiguration config;

    @JsonProperty("createdAt")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private OffsetDateTime createdAt;

    public WidgetInstance id(Integer id) {
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

    public WidgetInstance name(String name) {
        this.name = name;
        return this;
    }

    /**
     * User-defined name of this widget component, optional
     * @return name
     */

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public WidgetInstance serviceId(Integer serviceId) {
        this.serviceId = serviceId;
        return this;
    }

    /**
     * A resource identifier
     * minimum: 0
     * @return serviceId
     */
    @Min(0)
    public Integer getServiceId() {
        return serviceId;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }

    public WidgetInstance position(int position) {
        this.position = position;
        return this;
    }

    /**
     * The position of this widget in the column
     * minimum: 0
     * @return position
     */
    @NotNull
    @Min(0)
    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public WidgetInstance columnPos(Integer columnPos) {
        this.columnPos = columnPos;
        return this;
    }

    /**
     * The column id of the widget
     * minimum: 0
     * @return columnPos
     */
    @Min(0)
    public Integer getColumnPos() {
        return columnPos;
    }

    public void setColumnPos(Integer columnPos) {
        this.columnPos = columnPos;
    }

    public WidgetInstance config(WidgetConfiguration config) {
        this.config = config;
        return this;
    }

    /**
     * Get config
     * @return config
     */
    @Valid
    public WidgetConfiguration getConfig() {
        return config;
    }

    public void setConfig(WidgetConfiguration config) {
        this.config = config;
    }

    public WidgetInstance createdAt(OffsetDateTime createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    /**
     * Created date-time
     * @return createdAt
     */
    @Valid
    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(OffsetDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        WidgetInstance widgetInstance = (WidgetInstance) o;
        return (
            Objects.equals(this.id, widgetInstance.id) &&
            Objects.equals(this.name, widgetInstance.name) &&
            Objects.equals(this.serviceId, widgetInstance.serviceId) &&
            Objects.equals(this.position, widgetInstance.position) &&
            Objects.equals(this.columnPos, widgetInstance.columnPos) &&
            Objects.equals(this.config, widgetInstance.config) &&
            Objects.equals(this.createdAt, widgetInstance.createdAt)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, serviceId, position, columnPos, config, createdAt);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class WidgetInstance {\n");
        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("    name: ").append(toIndentedString(name)).append("\n");
        sb.append("    serviceId: ").append(toIndentedString(serviceId)).append("\n");
        sb.append("    position: ").append(toIndentedString(position)).append("\n");
        sb.append("    columnPos: ").append(toIndentedString(columnPos)).append("\n");
        sb.append("    config: ").append(toIndentedString(config)).append("\n");
        sb.append("    createdAt: ").append(toIndentedString(createdAt)).append("\n");
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
