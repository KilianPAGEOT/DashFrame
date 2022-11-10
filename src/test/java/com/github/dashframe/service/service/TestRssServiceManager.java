package com.github.dashframe.service.service;

import com.github.dashframe.models.Service;
import com.github.dashframe.models.json.ServiceType;
import reactor.core.publisher.Mono;

public class TestRssServiceManager implements ServiceManager<TestRssServiceManager.Context> {

    @Override
    public ServiceType getType() {
        return ServiceType.RSS;
    }

    @Override
    public Mono<Void> destroy(Context serviceContext) {
        return Mono.empty();
    }

    @Override
    public Mono<Context> open(Service service) {
        this.checkType(service);

        if (service.getToken().equals("secret")) {
            return Mono.just(new Context(service));
        }
        return Mono.error(new RuntimeException("Invalid token"));
    }

    public record Context(Service service) implements ServiceContext {}
}
