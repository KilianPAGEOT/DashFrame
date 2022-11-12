package com.github.dashframe.service.api;

import static com.github.dashframe.service.service.SteamServiceManager.API_KEY_PROPERTY;

import com.github.dashframe.service.service.MissingApiKeyException;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@Primary
public class DefaultSteamApiProvider implements SteamApiProvider {

    @Override
    public Mono<SteamApi> open(WebClient client, String apiKey) {
        if (apiKey == null || apiKey.isBlank() || apiKey.equals("NOT SET")) return Mono.error(
            new MissingApiKeyException(
                "Steam API key missing, please define " + API_KEY_PROPERTY + " in application.properties"
            )
        );

        return client
            .get()
            .uri(uriBuilder ->
                uriBuilder
                    .path("ISteamUser/GetFriendList/v0001")
                    .queryParam("key", apiKey)
                    .queryParam("steamid", "76561197960434622")
                    .build()
            )
            .retrieve()
            .toBodilessEntity()
            .then(Mono.fromSupplier(() -> new SteamApi(client, apiKey)));
    }
}
