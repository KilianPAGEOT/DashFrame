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
 * TwitchFollowedChannelsWidgetConfigurationAllOfParameters
 */

@JsonTypeName("TwitchFollowedChannelsWidgetConfiguration_allOf_parameters")
@Generated(
    value = "org.openapitools.codegen.languages.SpringCodegen",
    date = "2022-11-06T17:27:32.778551651+01:00[Europe/Paris]"
)
public class TwitchFollowedChannelsWidgetConfigurationAllOfParameters {

    /**
     * Gets or Sets filter
     */
    public enum FilterEnum {
        ALL("ALL"),

        STREAMING_ONLY("STREAMING_ONLY");

        private String value;

        FilterEnum(String value) {
            this.value = value;
        }

        @JsonValue
        public String getValue() {
            return value;
        }

        @Override
        public String toString() {
            return String.valueOf(value);
        }

        @JsonCreator
        public static FilterEnum fromValue(String value) {
            for (FilterEnum b : FilterEnum.values()) {
                if (b.value.equals(value)) {
                    return b;
                }
            }
            throw new IllegalArgumentException("Unexpected value '" + value + "'");
        }
    }

    @JsonProperty("filter")
    private FilterEnum filter;

    public TwitchFollowedChannelsWidgetConfigurationAllOfParameters filter(FilterEnum filter) {
        this.filter = filter;
        return this;
    }

    /**
     * Get filter
     * @return filter
     */
    @NotNull
    public FilterEnum getFilter() {
        return filter;
    }

    public void setFilter(FilterEnum filter) {
        this.filter = filter;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TwitchFollowedChannelsWidgetConfigurationAllOfParameters twitchFollowedChannelsWidgetConfigurationAllOfParameters = (TwitchFollowedChannelsWidgetConfigurationAllOfParameters) o;
        return Objects.equals(this.filter, twitchFollowedChannelsWidgetConfigurationAllOfParameters.filter);
    }

    @Override
    public int hashCode() {
        return Objects.hash(filter);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class TwitchFollowedChannelsWidgetConfigurationAllOfParameters {\n");
        sb.append("    filter: ").append(toIndentedString(filter)).append("\n");
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
