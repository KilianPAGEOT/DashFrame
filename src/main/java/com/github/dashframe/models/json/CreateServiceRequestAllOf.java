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
 * CreateServiceRequestAllOf
 */

@JsonTypeName("create_service_request_allOf")
@Generated(
    value = "org.openapitools.codegen.languages.SpringCodegen",
    date = "2022-11-06T17:27:32.778551651+01:00[Europe/Paris]"
)
public class CreateServiceRequestAllOf {

    @JsonProperty("type")
    private ServiceType type;

    @JsonProperty("token")
    private String token;

    @JsonProperty("username")
    private String username;

    public CreateServiceRequestAllOf type(ServiceType type) {
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

    public CreateServiceRequestAllOf token(String token) {
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

    public CreateServiceRequestAllOf username(String username) {
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
        CreateServiceRequestAllOf createServiceRequestAllOf = (CreateServiceRequestAllOf) o;
        return (
            Objects.equals(this.type, createServiceRequestAllOf.type) &&
            Objects.equals(this.token, createServiceRequestAllOf.token) &&
            Objects.equals(this.username, createServiceRequestAllOf.username)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, token, username);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CreateServiceRequestAllOf {\n");
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
