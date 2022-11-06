package com.github.dashframe.models.json;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.net.URI;
import java.time.OffsetDateTime;
import java.util.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import javax.annotation.Generated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * An error response object
 */

@Generated(
    value = "org.openapitools.codegen.languages.SpringCodegen",
    date = "2022-11-06T17:27:32.778551651+01:00[Europe/Paris]"
)
public class Error {

    @JsonProperty("code")
    private Integer code;

    @JsonProperty("message")
    private String message;

    @JsonProperty("messageKey")
    private String messageKey;

    @JsonProperty("messageParams")
    @Valid
    private Map<String, String> messageParams = null;

    public Error code(Integer code) {
        this.code = code;
        return this;
    }

    /**
     * The unique error code
     * @return code
     */

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Error message(String message) {
        this.message = message;
        return this;
    }

    /**
     * The error message in english, not required
     * @return message
     */

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Error messageKey(String messageKey) {
        this.messageKey = messageKey;
        return this;
    }

    /**
     * The error message localization key, not required
     * @return messageKey
     */

    public String getMessageKey() {
        return messageKey;
    }

    public void setMessageKey(String messageKey) {
        this.messageKey = messageKey;
    }

    public Error messageParams(Map<String, String> messageParams) {
        this.messageParams = messageParams;
        return this;
    }

    public Error putMessageParamsItem(String key, String messageParamsItem) {
        if (this.messageParams == null) {
            this.messageParams = new HashMap<>();
        }
        this.messageParams.put(key, messageParamsItem);
        return this;
    }

    /**
     * The parameters used in the localization of this error message, assumed to be empty if not present
     * @return messageParams
     */

    public Map<String, String> getMessageParams() {
        return messageParams;
    }

    public void setMessageParams(Map<String, String> messageParams) {
        this.messageParams = messageParams;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Error error = (Error) o;
        return (
            Objects.equals(this.code, error.code) &&
            Objects.equals(this.message, error.message) &&
            Objects.equals(this.messageKey, error.messageKey) &&
            Objects.equals(this.messageParams, error.messageParams)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, message, messageKey, messageParams);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Error {\n");
        sb.append("    code: ").append(toIndentedString(code)).append("\n");
        sb.append("    message: ").append(toIndentedString(message)).append("\n");
        sb.append("    messageKey: ").append(toIndentedString(messageKey)).append("\n");
        sb.append("    messageParams: ").append(toIndentedString(messageParams)).append("\n");
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
