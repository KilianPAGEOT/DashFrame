package com.github.dashframe.service.event;

import com.github.dashframe.models.json.Event;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public final class EventHandler {

    private final Map<Integer, Set<EventListener>> userEventListeners = new ConcurrentHashMap<>();
    private final Logger logger = LoggerFactory.getLogger("EventHandler");

    /**
     * Sends an arbitrary event payload to all listeners.
     *
     * @param userId The main concerned user for this event.
     * @param event  The event to broadcast.
     */
    public void broadcast(int userId, Event event) {
        Set<EventListener> listeners = this.userEventListeners.get(userId);
        int listenerCount = listeners == null ? 0 : listeners.size();

        logger.info(
            "Broadcasting event of type {} to {} listeners for user #{}",
            event.getType(),
            listenerCount,
            userId
        );
        if (listenerCount == 0) return;

        try {
            for (var listener : listeners) listener.onEvent(event);
        } catch (Exception error) {
            throw new RuntimeException(
                "An event listener threw an exception during broadcast of " + event.getType(),
                error
            );
        }
    }

    @SafeVarargs
    public final <E extends Event, D> void broadcast(int userId, EventFactory<E, D> eventFactory, D... eventData) {
        this.broadcast(userId, eventFactory.makeEvent(System.currentTimeMillis(), Arrays.asList(eventData)));
    }

    public void addListener(int userId, EventListener listener) {
        Set<EventListener> listeners = this.userEventListeners.get(userId);

        if (listeners == null) {
            listeners = new CopyOnWriteArraySet<>();
            this.userEventListeners.put(userId, listeners);
        }
        listeners.add(listener);
    }

    public void removeListener(int userId, EventListener listener) {
        Set<EventListener> listeners = this.userEventListeners.get(userId);

        if (listeners != null) listeners.remove(listener);
    }
}
