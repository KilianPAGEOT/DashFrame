package com.github.dashframe.models.json;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonValue;
import java.net.URI;
import java.time.OffsetDateTime;
import java.util.*;
import java.util.Objects;
import javax.annotation.Generated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * EpicGamesFriendsListWidgetConfigurationAllOfParameters
 */

@JsonTypeName("EpicGamesFriendsListWidgetConfiguration_allOf_parameters")
@Generated(
    value = "org.openapitools.codegen.languages.SpringCodegen",
    date = "2022-11-06T17:27:32.778551651+01:00[Europe/Paris]"
)
public class EpicGamesFriendsListWidgetConfigurationAllOfParameters {

    /**
     * Gets or Sets showOffline
     */
    public enum ShowOfflineEnum {
        NUMBER_0(0),

        NUMBER_1(1);

        private Integer value;

        ShowOfflineEnum(Integer value) {
            this.value = value;
        }

        @JsonValue
        public Integer getValue() {
            return value;
        }

        @Override
        public String toString() {
            return String.valueOf(value);
        }

        @JsonCreator
        public static ShowOfflineEnum fromValue(Integer value) {
            for (ShowOfflineEnum b : ShowOfflineEnum.values()) {
                if (b.value.equals(value)) {
                    return b;
                }
            }
            throw new IllegalArgumentException("Unexpected value '" + value + "'");
        }
    }

    @JsonProperty("showOffline")
    private ShowOfflineEnum showOffline;

    public EpicGamesFriendsListWidgetConfigurationAllOfParameters showOffline(ShowOfflineEnum showOffline) {
        this.showOffline = showOffline;
        return this;
    }

    /**
     * Get showOffline
     * @return showOffline
     */
    @NotNull
    public ShowOfflineEnum getShowOffline() {
        return showOffline;
    }

    public void setShowOffline(ShowOfflineEnum showOffline) {
        this.showOffline = showOffline;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        EpicGamesFriendsListWidgetConfigurationAllOfParameters epicGamesFriendsListWidgetConfigurationAllOfParameters = (EpicGamesFriendsListWidgetConfigurationAllOfParameters) o;
        return Objects.equals(this.showOffline, epicGamesFriendsListWidgetConfigurationAllOfParameters.showOffline);
    }

    @Override
    public int hashCode() {
        return Objects.hash(showOffline);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class EpicGamesFriendsListWidgetConfigurationAllOfParameters {\n");
        sb.append("    showOffline: ").append(toIndentedString(showOffline)).append("\n");
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
