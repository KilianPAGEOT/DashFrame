package com.github.dashframe.service.api;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public interface SteamApiProvider {
    Mono<SteamApi> open(WebClient client, String apiKey);
}
