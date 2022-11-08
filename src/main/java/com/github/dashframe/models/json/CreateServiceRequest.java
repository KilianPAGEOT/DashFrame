package com.github.dashframe.models.json;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.github.dashframe.models.json.ServiceType;
import java.net.URI;
import java.time.OffsetDateTime;
import java.util.*;
import java.util.Objects;
import javax.annotation.Generated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * CreateServiceRequest
 */

@JsonTypeName("create_service_request")
@Generated(
    value = "org.openapitools.codegen.languages.SpringCodegen",
    date = "2022-11-08T16:03:51.937911500+01:00[Europe/Paris]"
)
public class CreateServiceRequest {

    @JsonProperty("userId")
    private Integer userId;

    @JsonProperty("type")
    private ServiceType type;

    @JsonProperty("token")
    private String token;

    @JsonProperty("username")
    private String username;

    public CreateServiceRequest userId(Integer userId) {
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

    public CreateServiceRequest type(ServiceType type) {
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

    public CreateServiceRequest token(String token) {
        this.token = token;
        return this;
    }

    /**
     * The token returned by the external service, optional if not needed
     * @return token
     */

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public CreateServiceRequest username(String username) {
        this.username = username;
        return this;
    }

    /**
     * A string that identifies this specific account, not needed if the services does not have accounts
     * @return username
     */

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CreateServiceRequest createServiceRequest = (CreateServiceRequest) o;
        return (
            Objects.equals(this.userId, createServiceRequest.userId) &&
            Objects.equals(this.type, createServiceRequest.type) &&
            Objects.equals(this.token, createServiceRequest.token) &&
            Objects.equals(this.username, createServiceRequest.username)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, type, token, username);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CreateServiceRequest {\n");
        sb.append("    userId: ").append(toIndentedString(userId)).append("\n");
        sb.append("    type: ").append(toIndentedString(type)).append("\n");
        sb.append("    token: ").append(toIndentedString(token)).append("\n");
        sb.append("    username: ").append(toIndentedString(username)).append("\n");
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
