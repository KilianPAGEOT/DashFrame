package com.github.dashframe.service.service;

import com.github.dashframe.models.Service;
import com.github.dashframe.models.json.ServiceType;
import com.github.dashframe.service.api.SteamApi;
import com.github.dashframe.service.api.SteamApiProvider;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@org.springframework.stereotype.Service
public class SteamServiceManager implements ServiceManager<SteamServiceManager.Context> {

    private static final String BASE_URL = "http://api.steampowered.com";

    public static final String API_KEY_PROPERTY = "dashframe.services.steam.api_key";

    private final Map<Integer, Context> OPEN_SERVICES = new ConcurrentHashMap<>();

    @Autowired
    private Environment environment;

    @Autowired
    private SteamApiProvider steamApiProvider;

    @Autowired
    public SteamServiceManager(SteamApiProvider steamApiProvider) {}

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

        var client = WebClient
            .builder()
            .baseUrl(BASE_URL)
            .exchangeStrategies(
                ExchangeStrategies
                    .builder()
                    .codecs(it -> {
                        it.defaultCodecs().maxInMemorySize(10000000); // playing with fire
                    })
                    .build()
            )
            .build();
        var apiKey = this.environment.getProperty(API_KEY_PROPERTY);
        var steamid = service.getToken();

        //noinspection ConstantConditions
        return this.steamApiProvider.open(client, apiKey)
            .map(api -> OPEN_SERVICES.computeIfAbsent(service.getId(), id -> new Context(service, api, steamid)));
    }

    public record Context(Service service, SteamApi api, String steamId) implements ServiceContext {}
}
