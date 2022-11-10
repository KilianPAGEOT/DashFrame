package com.github.dashframe.service.service;

import com.github.dashframe.models.Service;
import com.github.dashframe.models.json.ServiceType;
import reactor.core.publisher.Mono;

/**
 * Manages multiple instances if services of the same type.
 * <p>
 * A service instance (mostly) corresponds to an external account.
 * A single DashFrame user may possess multiple accounts for the same service.
 *
 * @param <ContextT> Per-service custom data.
 */
public interface ServiceManager<ContextT extends ServiceContext> {
    /**
     * @return The type of service this manager can handle.
     */
    ServiceType getType();

    /**
     * Performs cleanup of manager-specific data.
     * <p>
     * The method <b>DOES NOT</b> delete the instance from the database.
     *
     * @param serviceContext The service to destroy.
     * @return nothing, asynchronously.
     *
     * @throws IllegalArgumentException if the passed service's type does match the type handled by the service manager.
     */
    Mono<Void> destroy(ContextT serviceContext);

    /**
     * Opens a connection to the external service.
     *
     * @param service The service to open a connection for.
     * @return The newly opened connection's context, asynchronously.
     *
     * @throws IllegalArgumentException if the passed service's type does match the type handled by this manager.
     */
    Mono<ContextT> open(Service service);

    /**
     * @param service The service instance to check.
     *
     * @throws IllegalArgumentException if the passed service's type does match the type handled by this manager.
     */
    default void checkType(Service service) {
        if (service.getType() != this.getType()) throw new IllegalArgumentException(
            "expected service of type " + this.getType() + ", got " + service.getType()
        );
    }
}
