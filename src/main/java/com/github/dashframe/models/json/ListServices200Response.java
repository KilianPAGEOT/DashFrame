package com.github.dashframe.models.json;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.github.dashframe.models.json.ServiceDescription;
import com.github.dashframe.models.json.ServiceInstance;
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
 * ListServices200Response
 */

@JsonTypeName("list_services_200_response")
@Generated(
    value = "org.openapitools.codegen.languages.SpringCodegen",
    date = "2022-11-08T16:03:51.937911500+01:00[Europe/Paris]"
)
public class ListServices200Response {

    @JsonProperty("available")
    @Valid
    private List<ServiceDescription> available = new ArrayList<>();

    @JsonProperty("instances")
    @Valid
    private List<ServiceInstance> instances = new ArrayList<>();

    public ListServices200Response available(List<ServiceDescription> available) {
        this.available = available;
        return this;
    }

    public ListServices200Response addAvailableItem(ServiceDescription availableItem) {
        if (this.available == null) {
            this.available = new ArrayList<>();
        }
        this.available.add(availableItem);
        return this;
    }

    /**
     * Indicates the list of Services supported by the server
     * @return available
     */
    @NotNull
    @Valid
    public List<ServiceDescription> getAvailable() {
        return available;
    }

    public void setAvailable(List<ServiceDescription> available) {
        this.available = available;
    }

    public ListServices200Response instances(List<ServiceInstance> instances) {
        this.instances = instances;
        return this;
    }

    public ListServices200Response addInstancesItem(ServiceInstance instancesItem) {
        if (this.instances == null) {
            this.instances = new ArrayList<>();
        }
        this.instances.add(instancesItem);
        return this;
    }

    /**
     * Get instances
     * @return instances
     */
    @NotNull
    @Valid
    public List<ServiceInstance> getInstances() {
        return instances;
    }

    public void setInstances(List<ServiceInstance> instances) {
        this.instances = instances;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ListServices200Response listServices200Response = (ListServices200Response) o;
        return (
            Objects.equals(this.available, listServices200Response.available) &&
            Objects.equals(this.instances, listServices200Response.instances)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(available, instances);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ListServices200Response {\n");
        sb.append("    available: ").append(toIndentedString(available)).append("\n");
        sb.append("    instances: ").append(toIndentedString(instances)).append("\n");
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
