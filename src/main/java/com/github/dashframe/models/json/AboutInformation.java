package com.github.dashframe.models.json;

import com.fasterxml.jackson.annotation.JsonProperty;

/** <b>NOT GENERATED </b> */
public record AboutInformation(
    @JsonProperty(value = "client", required = true) ClientInformation client,
    @JsonProperty(value = "server", required = true) ServerInformation server
) {}
