package com.github.dashframe.models.json;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.dashframe.models.json.ServiceType;
import java.net.URI;
import java.time.OffsetDateTime;
import java.time.OffsetDateTime;
import java.util.*;
import java.util.Objects;
import javax.annotation.Generated;
import javax.validation.Valid;
import javax.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * A service instance, a user may possess multiple instances (accounts) for any given service
 */

@Generated(
    value = "org.openapitools.codegen.languages.SpringCodegen",
    date = "2022-11-08T16:03:51.937911500+01:00[Europe/Paris]"
)
public class ServiceInstance {

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("type")
    private ServiceType type;

    @JsonProperty("username")
    private String username;

    @JsonProperty("token")
    private String token;

    @JsonProperty("createdAt")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private OffsetDateTime createdAt;

    public ServiceInstance id(Integer id) {
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

    public ServiceInstance type(ServiceType type) {
        this.type = type;
        return this;
    }

    /**
     * Get type
     * @return type
     */
    @NotNull
    @Valid
    public ServiceType getType() {
        return type;
    }

    public void setType(ServiceType type) {
        this.type = type;
    }

    public ServiceInstance username(String username) {
        this.username = username;
        return this;
    }

    /**
     * An username/email, unique accross service instances of a user
     * @return username
     */

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public ServiceInstance token(String token) {
        this.token = token;
        return this;
    }

    /**
     * Login token for the service
     * @return token
     */

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public ServiceInstance createdAt(OffsetDateTime createdAt) {
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
        ServiceInstance serviceInstance = (ServiceInstance) o;
        return (
            Objects.equals(this.id, serviceInstance.id) &&
            Objects.equals(this.type, serviceInstance.type) &&
            Objects.equals(this.username, serviceInstance.username) &&
            Objects.equals(this.token, serviceInstance.token) &&
            Objects.equals(this.createdAt, serviceInstance.createdAt)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type, username, token, createdAt);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ServiceInstance {\n");
        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("    type: ").append(toIndentedString(type)).append("\n");
        sb.append("    username: ").append(toIndentedString(username)).append("\n");
        sb.append("    token: ").append(toIndentedString(token)).append("\n");
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
