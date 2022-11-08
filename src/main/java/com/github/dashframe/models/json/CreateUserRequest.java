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
 * CreateUserRequest
 */

@JsonTypeName("create_user_request")
@Generated(
    value = "org.openapitools.codegen.languages.SpringCodegen",
    date = "2022-11-08T15:27:52.118173200+01:00[Europe/Paris]"
)
public class CreateUserRequest {

    @JsonProperty("userId")
    private Integer userId;

    @JsonProperty("name")
    private String name;

    @JsonProperty("username")
    private String username;

    @JsonProperty("hashPassword")
    private String hashPassword;

    @JsonProperty("oauthToken")
    private String oauthToken;

    @JsonProperty("isAdmin")
    private Boolean isAdmin;

    public CreateUserRequest userId(Integer userId) {
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

    public CreateUserRequest name(String name) {
        this.name = name;
        return this;
    }

    /**
     * The user's name
     * @return name
     */
    @NotNull
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CreateUserRequest username(String username) {
        this.username = username;
        return this;
    }

    /**
     * A unique identifier that can be any format like email or number sequence
     * @return username
     */
    @NotNull
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public CreateUserRequest hashPassword(String hashPassword) {
        this.hashPassword = hashPassword;
        return this;
    }

    /**
     * The password of the user
     * @return hashPassword
     */
    @NotNull
    public String getHashPassword() {
        return hashPassword;
    }

    public void setHashPassword(String hashPassword) {
        this.hashPassword = hashPassword;
    }

    public CreateUserRequest oauthToken(String oauthToken) {
        this.oauthToken = oauthToken;
        return this;
    }

    /**
     * The token returned by the external service, optional if not needed
     * @return oauthToken
     */

    public String getOauthToken() {
        return oauthToken;
    }

    public void setOauthToken(String oauthToken) {
        this.oauthToken = oauthToken;
    }

    public CreateUserRequest isAdmin(Boolean isAdmin) {
        this.isAdmin = isAdmin;
        return this;
    }

    /**
     * If the user is admin or not
     * @return isAdmin
     */
    @NotNull
    public Boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(Boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CreateUserRequest createUserRequest = (CreateUserRequest) o;
        return (
            Objects.equals(this.userId, createUserRequest.userId) &&
            Objects.equals(this.name, createUserRequest.name) &&
            Objects.equals(this.username, createUserRequest.username) &&
            Objects.equals(this.hashPassword, createUserRequest.hashPassword) &&
            Objects.equals(this.oauthToken, createUserRequest.oauthToken) &&
            Objects.equals(this.isAdmin, createUserRequest.isAdmin)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, name, username, hashPassword, oauthToken, isAdmin);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CreateUserRequest {\n");
        sb.append("    userId: ").append(toIndentedString(userId)).append("\n");
        sb.append("    name: ").append(toIndentedString(name)).append("\n");
        sb.append("    username: ").append(toIndentedString(username)).append("\n");
        sb.append("    hashPassword: ").append(toIndentedString(hashPassword)).append("\n");
        sb.append("    oauthToken: ").append(toIndentedString(oauthToken)).append("\n");
        sb.append("    isAdmin: ").append(toIndentedString(isAdmin)).append("\n");
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
