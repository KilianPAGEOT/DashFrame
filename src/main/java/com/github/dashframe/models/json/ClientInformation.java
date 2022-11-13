package com.github.dashframe.models.json;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Information about the client that executed this request
 * <b>NOT GENERATED</b>
 */
public record ClientInformation(@JsonProperty(value = "host", required = true) String host) {}
