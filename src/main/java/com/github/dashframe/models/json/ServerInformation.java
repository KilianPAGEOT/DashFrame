package com.github.dashframe.models.json;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.dashframe.models.json.ServiceDescription;
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
 * Information about the server that is receiving this request
 */

@Generated(
    value = "org.openapitools.codegen.languages.SpringCodegen",
    date = "2022-11-08T15:27:52.118173200+01:00[Europe/Paris]"
)
public class ServerInformation {

    @JsonProperty("current_time")
    private Long currentTime;

    @JsonProperty("services")
    @Valid
    private List<ServiceDescription> services = new ArrayList<>();

    public ServerInformation currentTime(Long currentTime) {
        this.currentTime = currentTime;
        return this;
    }

    /**
     * Indicates the server time in the Epoch Unix Time Stamp format
     * @return currentTime
     */
    @NotNull
    public Long getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(Long currentTime) {
        this.currentTime = currentTime;
    }

    public ServerInformation services(List<ServiceDescription> services) {
        this.services = services;
        return this;
    }

    public ServerInformation addServicesItem(ServiceDescription servicesItem) {
        if (this.services == null) {
            this.services = new ArrayList<>();
        }
        this.services.add(servicesItem);
        return this;
    }

    /**
     * Indicates the list of Services supported by the server
     * @return services
     */
    @NotNull
    @Valid
    public List<ServiceDescription> getServices() {
        return services;
    }

    public void setServices(List<ServiceDescription> services) {
        this.services = services;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ServerInformation serverInformation = (ServerInformation) o;
        return (
            Objects.equals(this.currentTime, serverInformation.currentTime) &&
            Objects.equals(this.services, serverInformation.services)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(currentTime, services);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ServerInformation {\n");
        sb.append("    currentTime: ").append(toIndentedString(currentTime)).append("\n");
        sb.append("    services: ").append(toIndentedString(services)).append("\n");
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
