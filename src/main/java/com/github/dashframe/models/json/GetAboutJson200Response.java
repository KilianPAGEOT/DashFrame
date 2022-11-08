package com.github.dashframe.models.json;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.github.dashframe.models.json.ClientInformation;
import com.github.dashframe.models.json.ServerInformation;
import java.net.URI;
import java.time.OffsetDateTime;
import java.util.*;
import java.util.Objects;
import javax.annotation.Generated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * GetAboutJson200Response
 */

@JsonTypeName("get_about_json_200_response")
@Generated(
    value = "org.openapitools.codegen.languages.SpringCodegen",
    date = "2022-11-08T16:03:51.937911500+01:00[Europe/Paris]"
)
public class GetAboutJson200Response {

    @JsonProperty("client")
    private ClientInformation client;

    @JsonProperty("server")
    private ServerInformation server;

    public GetAboutJson200Response client(ClientInformation client) {
        this.client = client;
        return this;
    }

    /**
     * Get client
     * @return client
     */
    @NotNull
    @Valid
    public ClientInformation getClient() {
        return client;
    }

    public void setClient(ClientInformation client) {
        this.client = client;
    }

    public GetAboutJson200Response server(ServerInformation server) {
        this.server = server;
        return this;
    }

    /**
     * Get server
     * @return server
     */
    @NotNull
    @Valid
    public ServerInformation getServer() {
        return server;
    }

    public void setServer(ServerInformation server) {
        this.server = server;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        GetAboutJson200Response getAboutJson200Response = (GetAboutJson200Response) o;
        return (
            Objects.equals(this.client, getAboutJson200Response.client) &&
            Objects.equals(this.server, getAboutJson200Response.server)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(client, server);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class GetAboutJson200Response {\n");
        sb.append("    client: ").append(toIndentedString(client)).append("\n");
        sb.append("    server: ").append(toIndentedString(server)).append("\n");
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
