package com.github.dashframe.service.service;

import com.github.dashframe.models.Service;
import com.github.dashframe.models.json.ServiceType;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@org.springframework.stereotype.Service
public class SteamServiceManager implements ServiceManager<SteamServiceManager.Context> {

    private static final String BASE_URL = "http://api.steampowered.com";

    private static final String API_KEY_PROPERTY = "dashframe.services.steam.api_key";

    private final Map<Integer, Context> OPEN_SERVICES = new ConcurrentHashMap<>();

    @Autowired
    private Environment environment;

    @Override
    public ServiceType getType() {
        return ServiceType.STEAM;
    }

    @Override
    public Mono<Void> destroy(Context serviceContext) {
        OPEN_SERVICES.remove(serviceContext.service.getId());
        return Mono.empty();
    }

    @Override
    public Mono<Context> open(Service service) {
        this.checkType(service);

        var client = WebClient.create(BASE_URL);
        var apiKey = this.environment.getProperty(API_KEY_PROPERTY);
        var steamid = service.getToken();

        if (apiKey == null || apiKey.isBlank()) return Mono.error(
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
                    .queryParam("steamid", steamid)
                    .build()
            )
            .retrieve()
            .toBodilessEntity()
            .then(
                Mono.fromSupplier(() ->
                    OPEN_SERVICES.computeIfAbsent(service.getId(), id -> new Context(service, client, apiKey, steamid))
                )
            );
    }

    public record Context(Service service, WebClient client, String apiKey, String steamid) implements ServiceContext {}
}
