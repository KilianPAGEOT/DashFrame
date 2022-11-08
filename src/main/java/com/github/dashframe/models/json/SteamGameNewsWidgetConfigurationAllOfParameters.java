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
 * SteamGameNewsWidgetConfigurationAllOfParameters
 */

@JsonTypeName("SteamGameNewsWidgetConfiguration_allOf_parameters")
@Generated(
    value = "org.openapitools.codegen.languages.SpringCodegen",
    date = "2022-11-08T16:03:51.937911500+01:00[Europe/Paris]"
)
public class SteamGameNewsWidgetConfigurationAllOfParameters {

    @JsonProperty("gameNameOrId")
    private String gameNameOrId;

    public SteamGameNewsWidgetConfigurationAllOfParameters gameNameOrId(String gameNameOrId) {
        this.gameNameOrId = gameNameOrId;
        return this;
    }

    /**
     * Get gameNameOrId
     * @return gameNameOrId
     */
    @NotNull
    @Size(min = 1)
    public String getGameNameOrId() {
        return gameNameOrId;
    }

    public void setGameNameOrId(String gameNameOrId) {
        this.gameNameOrId = gameNameOrId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SteamGameNewsWidgetConfigurationAllOfParameters steamGameNewsWidgetConfigurationAllOfParameters = (SteamGameNewsWidgetConfigurationAllOfParameters) o;
        return Objects.equals(this.gameNameOrId, steamGameNewsWidgetConfigurationAllOfParameters.gameNameOrId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gameNameOrId);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class SteamGameNewsWidgetConfigurationAllOfParameters {\n");
        sb.append("    gameNameOrId: ").append(toIndentedString(gameNameOrId)).append("\n");
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
