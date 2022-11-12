package com.github.dashframe.service.event;

import com.github.dashframe.models.Service;
import com.github.dashframe.models.User;
import com.github.dashframe.models.Widget;
import com.github.dashframe.models.json.WidgetRefreshEventAllOfDataWidgets;
import com.github.dashframe.models.json.WidgetType;
import com.github.dashframe.service.service.ServiceContext;
import com.github.dashframe.service.service.ServiceManager;
import com.github.dashframe.service.service.ServiceManagerProvider;
import com.github.dashframe.service.widget.WidgetContext;
import com.github.dashframe.service.widget.WidgetManager;
import com.github.dashframe.service.widget.WidgetManagerProvider;
import java.time.Instant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

/**
 * A repeated {@link Widget} refreshing task.
 *
 * @param <SC> The type of service context.
 * @param <C> The type of widget context.
 */
class WidgetRefreshTask<SC extends ServiceContext, C extends WidgetContext>
    implements Comparable<WidgetRefreshTask<?, ?>> {

    public final User owner;
    public final Widget widget;
    public final WidgetManager<?, C, ?> manager;
    public final SC serviceContext;
    public final C widgetContext;
    public Instant nextUpdate = Instant.MIN;
    private static final Logger logger = LoggerFactory.getLogger("WidgetRefreshTask");

    private WidgetRefreshTask(Widget widget, SC serviceContext, C widgetContext, WidgetManager<SC, C, ?> manager) {
        this.owner = widget.getService().getUser();
        this.widget = widget;
        this.manager = manager;
        this.serviceContext = serviceContext;
        this.widgetContext = widgetContext;
    }

    /**
     * Asynchronously creates a {@link WidgetRefreshTask} from a widget and service/widget manager providers.
     *
     * @param widget The widget to refresh.
     * @param serviceManagerProvider Provides the service manager corresponding to the given widget.
     * @param widgetManagerProvider Provides the manager for the given widget.
     * @return The newly created refresh task, asynchronous.
     *
     * @param <SC> The type of service context.
     * @param <C> The type of widget context.
     */
    public static <SC extends ServiceContext, C extends WidgetContext> Mono<WidgetRefreshTask<SC, C>> fromProviders(
        Widget widget,
        ServiceManagerProvider serviceManagerProvider,
        WidgetManagerProvider widgetManagerProvider
    ) {
        Service service = widget.getService();
        @SuppressWarnings("unchecked")
        ServiceManager<SC> serviceManager = (ServiceManager<SC>) serviceManagerProvider.forType(service.getType());

        if (serviceManager == null) {
            logger.warn("No ServiceManager found for {}", service.getType());
            return Mono.empty();
        }
        return serviceManager
            .open(service)
            .flatMap(context -> fromServiceContext(widget, context, widgetManagerProvider));
    }

    /**
     * Asynchronously creates a {@link WidgetRefreshTask} from a widget, the service context and a widget manager provider.
     *
     * @param widget The widget to refresh.
     * @param serviceContext The context of the service associated with the given widget.
     * @param widgetManagerProvider Provides the manager for the given widget.
     * @return The newly created refresh task, asynchronous.
     *
     * @param <SC> The type of service context.
     * @param <C> The type of widget context.
     */
    public static <
        SC extends ServiceContext, C extends WidgetContext
    > Mono<WidgetRefreshTask<SC, C>> fromServiceContext(
        Widget widget,
        SC serviceContext,
        WidgetManagerProvider widgetManagerProvider
    ) {
        @SuppressWarnings("unchecked")
        WidgetManager<SC, C, ?> widgetManager = (WidgetManager<SC, C, ?>) widgetManagerProvider.forTypeName(
            widget.getType()
        );

        if (widgetManager == null) {
            logger.warn("No WidgetManager found for {}", widget.getType());
            return Mono.empty();
        }
        return widgetManager
            .open(widget, serviceContext)
            .map(widgetContext -> new WidgetRefreshTask<>(widget, serviceContext, widgetContext, widgetManager));
    }

    public void reset(Instant now) {
        this.nextUpdate = now.plusSeconds(this.widget.getRefreshRate());
    }

    /**
     * @return Whether the widget needs to be refreshed.
     */
    public boolean isReady(Instant now) {
        return now.isAfter(this.nextUpdate);
    }

    /**
     * Collects data from the widget's manager.
     */
    public Mono<EventReactor.WidgetDataByOwner> fetchData() {
        return this.manager.fetchData(this.widgetContext)
            .map(data ->
                new EventReactor.WidgetDataByOwner(
                    new WidgetRefreshEventAllOfDataWidgets().id(this.widget.getId()).data(data),
                    this.owner
                )
            );
    }

    public WidgetType getType() {
        return this.manager.getType();
    }

    @Override
    public int compareTo(WidgetRefreshTask<?, ?> other) {
        return this.nextUpdate.compareTo(other.nextUpdate);
    }
}
