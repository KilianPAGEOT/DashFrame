package com.github.dashframe.service.widget;

import com.github.dashframe.models.Widget;
import com.github.dashframe.models.json.WidgetType;
import com.github.dashframe.service.service.ServiceContext;
import reactor.core.publisher.Mono;

/**
 * Manages multiple instances of widgets of the same type (and service).
 *
 * @param <ServiceContextT> Per-service custom data.
 * @param <WidgetContextT> Per-widget custom data, mostly used to keep the HTTP connection client.
 * @param <DataT> The kind of data that is fetched from the external service, must be JSON-serializable.
 */
public interface WidgetManager<ServiceContextT extends ServiceContext, WidgetContextT extends WidgetContext, DataT> {
    /**
     * @return The type of widgets supported by this manager.
     */
    WidgetType getType();

    /**
     * @param widget A widget model.
     * @param serviceContext The context object of the corresponding service.
     * @return The newly created request, use this for future calls {@link #fetchData}.
     */
    Mono<WidgetContextT> open(Widget widget, ServiceContextT serviceContext);

    /**
     * @return Arbitrary JSON-compatible widget data to send to the client(s).
     */
    Mono<DataT> fetchData(WidgetContextT widgetContext);
}
