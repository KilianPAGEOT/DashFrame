package com.github.dashframe.service.service;

import com.github.dashframe.models.json.ServiceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

/**
 * The default implementation of {@link ServiceManagerProvider} used when dependency injecting.
 */
@Service
@Primary
public final class DefaultServiceManagerProvider implements ServiceManagerProvider {

    @Autowired
    private ApplicationContext context;

    @Override
    public ServiceManager<?> forType(ServiceType type) {
        //noinspection SwitchStatementWithTooFewBranches
        return switch (type) {
            case STEAM -> context.getBean(SteamServiceManager.class);
            case WEATHER_TIME -> context.getBean(WeatherServiceManager.class);
            default -> null;
        };
    }
}
