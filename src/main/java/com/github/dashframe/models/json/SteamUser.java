package com.github.dashframe.models.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.net.URI;
import java.util.Date;
import java.util.Optional;

/** <b>NOT GENERATED </b> */
public record SteamUser(
    String steamId,
    String name,
    URI profileURL,
    URI avatar32x32,
    URI avatar64x64,
    URI avatar184x184,
    SteamUser.Status status,
    Optional<Date> lastLogoff
) {
    public enum Status {
        OFFLINE,
        ONLINE,
        BUSY,
        AWAY,
        SNOOZE,
        LOOKING_FOR_TRADE,
        LOOKING_TO_PLAY,
    }

    @SuppressWarnings("SpellCheckingInspection")
    public record Raw(
        @JsonProperty("steamid") String steamId,
        @JsonProperty("personaname") String name,
        @JsonProperty("profileurl") URI profileURL,
        @JsonProperty("avatar") URI avatar32x32,
        @JsonProperty("avatarmedium") URI avatar64x64,
        @JsonProperty("avatarfull") URI avatar184x184,
        @JsonProperty("profilestate") SteamUser.Status status,
        @JsonProperty("lastlogoff") long lastLogoff
    ) {}
}
