package com.github.dashframe.service.event;

import com.github.dashframe.dao.ServiceDAO;
import com.github.dashframe.dao.WidgetDAO;
import com.github.dashframe.models.User;
import com.github.dashframe.models.Widget;
import com.github.dashframe.models.json.EventType;
import com.github.dashframe.models.json.WidgetRefreshEvent;
import com.github.dashframe.models.json.WidgetRefreshEventAllOfData;
import com.github.dashframe.models.json.WidgetRefreshEventAllOfDataWidgets;
import com.github.dashframe.service.service.ServiceManagerProvider;
import com.github.dashframe.service.widget.WidgetContext;
import com.github.dashframe.service.widget.WidgetManagerProvider;
import com.github.dashframe.util.MonoUtil;
import java.time.Instant;
import java.util.*;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.scheduling.TriggerContext;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * This class is responsible for sending events based on the refresh rates of event sources.
 */
@org.springframework.stereotype.Service
public final class EventReactor {

    private final Logger logger = LoggerFactory.getLogger("EventReactor");

    private final Set<Widget> widgets = new CopyOnWriteArraySet<>();
    private final Queue<WidgetRefreshTask<?, ?>> tasks = new PriorityQueue<>();

    @Autowired
    private EventHandler eventHandler;

    @Autowired
    private ServiceDAO serviceDAO;

    @Autowired
    private WidgetDAO widgetDAO;

    @Autowired
    private ServiceManagerProvider serviceManagerProvider;

    @Autowired
    private WidgetManagerProvider widgetManagerProvider;

    /**
     * Called on a timer.
     */
    public void tick() {
        this.synchronizeTasks();
        this.refreshWidgets();
    }

    /**
     * @return The date of the next update.
     */
    public Date nextTick(TriggerContext triggerContext) {
        var nextTask = this.tasks.peek();

        if (nextTask == null) {
            var lastCompletion = triggerContext.lastCompletionTime();

            if (lastCompletion == null) {
                // if no tasks are scheduled, check ASAP
                return new Date();
            }

            // check again in two seconds
            return Date.from(lastCompletion.toInstant().plusMillis(2000));
        } else {
            // return the date of the next task
            return Date.from(nextTask.nextUpdate);
        }
    }

    private void synchronizeTasks() {
        var allWidgets =
            this.eventHandler.getListeningUsers()
                .stream()
                .flatMap(user -> this.serviceDAO.findByUser(user).stream())
                .flatMap(service -> this.widgetDAO.findByService(service).stream())
                .collect(Collectors.toUnmodifiableSet());

        // remove tasks that belongs to widget that are not observed
        this.widgets.stream().filter(widget -> !allWidgets.contains(widget)).forEach(this::removeTask);

        Flux
            .fromIterable(allWidgets)
            .filter(widget -> !this.widgets.contains(widget))
            .flatMap(this::addTask)
            .collectList()
            .block();
    }

    private void removeTask(Widget toRemove) {
        this.logger.info("Removing refresh task of widget #{}", toRemove.getId());

        this.widgets.remove(toRemove);
        this.tasks.removeIf(task -> Objects.equals(task.widget.getId(), toRemove.getId()));
    }

    private Mono<Void> addTask(Widget toAdd) {
        this.logger.info(
                "Watching widget #{} for data changes every {} seconds",
                toAdd.getId(),
                toAdd.getRefreshRate()
            );

        Optional<WidgetRefreshTask<?, ?>> previousTask =
            this.tasks.stream()
                .filter(task -> Objects.equals(task.widget.getService().getId(), toAdd.getService().getId()))
                .findAny();

        // try to construct a widget task from an existing ServiceContext, if possible
        Mono<? extends WidgetRefreshTask<?, WidgetContext>> task = previousTask
            .map(prev -> WidgetRefreshTask.fromServiceContext(toAdd, prev.serviceContext, this.widgetManagerProvider))
            .orElseGet(() ->
                WidgetRefreshTask.fromProviders(toAdd, this.serviceManagerProvider, this.widgetManagerProvider)
            );

        return MonoUtil.ignoreExceptions(
            task
                .map(t -> {
                    this.widgets.add(t.widget);
                    return this.tasks.add(t);
                })
                .then(),
            error -> this.logger.error("Failed to watch widget #{}", toAdd.getId(), error)
        );
    }

    /**
     * Collects new data from widgets that need it and sends events to users that are currently displaying these widgets.
     */
    private void refreshWidgets() {
        var beforeExecution = Instant.now();
        WidgetRefreshTask<?, ?> next;
        List<WidgetRefreshTask<?, ?>> toExecute = new ArrayList<>();

        while ((next = this.tasks.peek()) != null && next.isReady(beforeExecution)) {
            toExecute.add(this.tasks.remove());
        }

        if (!toExecute.isEmpty()) {
            this.logger.info("{} widget(s) to refresh, {} not ready", toExecute.size(), this.tasks.size());
        }

        var eventsByOwner = this.fetchRefreshEvents(toExecute);

        if (eventsByOwner != null) {
            for (var eventByOwner : eventsByOwner) {
                this.eventHandler.broadcast(eventByOwner.owner(), eventByOwner.event);
            }
        }

        var afterExecution = Instant.now();
        for (var task : toExecute) {
            task.reset(afterExecution);
            this.tasks.add(task);
        }
    }

    /**
     * Executes the given tasks in order to get widget refresh events out of them.
     *
     * @param toExecute The tasks to execute.
     * @return A list of {@link WidgetRefreshEvent} instances associated with the owner of their widgets.
     */
    @Nullable
    private List<WidgetRefreshEventByOwner> fetchRefreshEvents(Iterable<WidgetRefreshTask<?, ?>> toExecute) {
        return Flux
            .fromIterable(toExecute)
            .flatMap(task ->
                MonoUtil.ignoreExceptions(
                    task.fetchData(),
                    error ->
                        this.logger.error(
                                "Failed to fetch data for widget #{} of type {}",
                                task.widget.getId(),
                                task.manager.getType(),
                                error
                            )
                )
            )
            .groupBy(WidgetDataByOwner::owner)
            .flatMap(group ->
                group
                    .collectList()
                    .map(dataByOwner ->
                        new WidgetRefreshEventByOwner(
                            makeWidgetRefreshEvent(dataByOwner.stream().map(WidgetDataByOwner::data).toList()),
                            group.key()
                        )
                    )
            )
            .collectList()
            .block();
    }

    /**
     * Convenience method for creating a {@link WidgetRefreshEvent} out of a list of widget data.
     */
    private static WidgetRefreshEvent makeWidgetRefreshEvent(List<WidgetRefreshEventAllOfDataWidgets> data) {
        return new WidgetRefreshEvent()
            .type(EventType.REFRESH.getValue())
            .timestamp(Instant.now().getEpochSecond())
            .data(new WidgetRefreshEventAllOfData().widgets(data));
    }

    record WidgetDataByOwner(WidgetRefreshEventAllOfDataWidgets data, User owner) {}

    private record WidgetRefreshEventByOwner(WidgetRefreshEvent event, User owner) {}
}
