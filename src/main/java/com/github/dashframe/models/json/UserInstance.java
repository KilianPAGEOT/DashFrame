package com.github.dashframe.models.json;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
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
 * A user instance
 */

@Generated(
    value = "org.openapitools.codegen.languages.SpringCodegen",
    date = "2022-11-08T16:03:51.937911500+01:00[Europe/Paris]"
)
public class UserInstance {

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("username")
    private String username;

    @JsonProperty("isAdmin")
    private Boolean isAdmin;

    @JsonProperty("createdAt")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private OffsetDateTime createdAt;

    public UserInstance id(Integer id) {
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

    public UserInstance name(String name) {
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

    public UserInstance username(String username) {
        this.username = username;
        return this;
    }

    /**
     * A unique identifier that can be any format like email or number sequence
     * @return username
     */

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public UserInstance isAdmin(Boolean isAdmin) {
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

    public UserInstance createdAt(OffsetDateTime createdAt) {
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
        UserInstance userInstance = (UserInstance) o;
        return (
            Objects.equals(this.id, userInstance.id) &&
            Objects.equals(this.name, userInstance.name) &&
            Objects.equals(this.username, userInstance.username) &&
            Objects.equals(this.isAdmin, userInstance.isAdmin) &&
            Objects.equals(this.createdAt, userInstance.createdAt)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, username, isAdmin, createdAt);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class UserInstance {\n");
        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("    name: ").append(toIndentedString(name)).append("\n");
        sb.append("    username: ").append(toIndentedString(username)).append("\n");
        sb.append("    isAdmin: ").append(toIndentedString(isAdmin)).append("\n");
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
