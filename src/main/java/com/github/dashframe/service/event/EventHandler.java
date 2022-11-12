package com.github.dashframe.service.event;

import com.github.dashframe.models.User;
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

    private final Map<User, Set<EventListener>> userEventListeners = new ConcurrentHashMap<>();
    private final Logger logger = LoggerFactory.getLogger("EventHandler");

    /**
     * Sends an arbitrary event payload to all listeners.
     *
     * @param user The main concerned user for this event.
     * @param event  The event to broadcast.
     */
    public void broadcast(User user, Event event) {
        Set<EventListener> listeners = this.userEventListeners.get(user);
        int listenerCount = listeners == null ? 0 : listeners.size();

        logger.info(
            "Broadcasting event of type {} to {} listeners for user #{}",
            event.getType(),
            listenerCount,
            user.getId()
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
    public final <E extends Event, D> void broadcast(User user, EventFactory<E, D> eventFactory, D... eventData) {
        this.broadcast(user, eventFactory.makeEvent(System.currentTimeMillis(), Arrays.asList(eventData)));
    }

    public void addListener(User user, EventListener listener) {
        Set<EventListener> listeners = this.userEventListeners.get(user);

        if (listeners == null) {
            listeners = new CopyOnWriteArraySet<>();
            this.userEventListeners.put(user, listeners);
        }
        listeners.add(listener);
    }

    public void removeListener(User user, EventListener listener) {
        Set<EventListener> listeners = this.userEventListeners.get(user);

        if (listeners != null) {
            listeners.remove(listener);
            if (listeners.isEmpty()) this.userEventListeners.remove(user);
        }
    }

    public Set<User> getListeningUsers() {
        return this.userEventListeners.keySet();
    }
}
