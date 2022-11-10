package com.github.dashframe.service.service;

import com.github.dashframe.models.json.ServiceType;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

/**
 * The default implementation of {@link ServiceManagerProvider} used when dependency injecting.
 */
@Service
@Primary
public final class DefaultServiceManagerProvider implements ServiceManagerProvider {

    @Override
    public ServiceManager<?> forType(ServiceType type) {
        // TODO return the service managers here
        return null;
    }
}
