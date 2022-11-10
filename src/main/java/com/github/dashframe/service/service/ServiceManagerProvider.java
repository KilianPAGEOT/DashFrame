package com.github.dashframe.service.service;

import com.github.dashframe.models.json.ServiceType;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

/**
 * Provides access to {@link ServiceManager} instances for specific types.
 */
@Service
@FunctionalInterface
public interface ServiceManagerProvider {
    /**
     * @return A {@link ServiceManager} instance corresponding to the given type, or null if not supported/implemented.
     */
    @Nullable
    ServiceManager<?> forType(ServiceType type);

    /**
     * @return A {@link ServiceManager} instance corresponding to the given type, or null if not supported/implemented.
     */
    @Nullable
    default ServiceManager<?> forTypeName(String typeName) {
        for (var type : ServiceType.values()) {
            if (type.getValue().equals(typeName)) {
                return this.forType(type);
            }
        }
        return null;
    }
}
