package com.github.dashframe.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.dashframe.dao.UserDAO;
import com.github.dashframe.models.User;
import com.github.dashframe.models.json.Event;
import com.github.dashframe.service.event.EventHandler;
import com.github.dashframe.service.event.EventListener;
import com.github.dashframe.util.Errors;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

/**
 * Handles the sending of server events to clients
 */
@Component
public class EventsWebSocketHandler extends TextWebSocketHandler {

    private final Logger logger = LoggerFactory.getLogger("EventsWebSocketHandler");

    private final Map<WebSocketSession, WebSocketEventListener> sessions = new ConcurrentHashMap<>();

    /**
     * The JSON object mapper instance.
     * <p>
     * <i>Brought to you by the magic of Spring™.</i>
     */
    @Autowired
    private ObjectMapper objectMapper;

    /**
     * The event handler instance.
     * <p>
     * <i>Brought to you by the magic of Spring™.</i>
     */
    @Autowired
    private EventHandler eventHandler;

    @Autowired
    private UserDAO userDAO;

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        this.logger.info("Connection established from {} with session {}", session.getRemoteAddress(), session);

        var user = this.findSessionUser(session);

        if (user != null) {
            var listener = new WebSocketEventListener(session, user, this::sendEvent);
            this.sessions.put(session, listener);
            this.logger.info("listening to user #{}", user.getId());
            this.eventHandler.addListener(user, listener);
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        this.logger.info("Connection closed for session {}, reason: {}", session, status.getReason());
        this.tryCloseSession(this.sessions.get(session));
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage jsonTextMessage) throws Exception {
        this.sendJsonMessage(session, Errors.serverToClientOnlySocket("events"));
    }

    /**
     * Sends an object as a JSON-encoded text message through a WebSocket.
     *
     * @param session The target WS session.
     * @param toSend  The object to convert to JSON and send.
     * @throws IOException The object is non JSON-compatible, or some IO error happened.
     */
    private void sendJsonMessage(WebSocketSession session, Object toSend) throws IOException {
        session.sendMessage(new TextMessage(this.objectMapper.writeValueAsString(toSend)));
    }

    /**
     * Sends an {@link Event} (as JSON) to the given session.
     *
     * @param event The event to send to the client.
     */
    private void sendEvent(WebSocketEventListener listener, Event event) {
        try {
            if (listener.session.isOpen()) {
                this.sendJsonMessage(listener.session, event);
                return;
            }
        } catch (Exception error) {
            this.logger.error("Closing session due to exception for " + listener.session, error);
        }
        this.tryCloseSession(listener);
    }

    private void tryCloseSession(@Nullable WebSocketEventListener listener) {
        if (listener == null) {
            return;
        }
        if (listener.session.isOpen()) {
            try {
                listener.session.close(CloseStatus.SERVER_ERROR);
            } catch (Throwable ex) {
                // ignore
            }
        }
        this.sessions.remove(listener.session);
        this.eventHandler.removeListener(listener.user, listener);
    }

    /**
     * Attempts to retrieve the current logged-in user from the given WebSocket session.
     */
    @Nullable
    private User findSessionUser(WebSocketSession session) {
        var principal = session.getPrincipal();
        var username = principal != null ? principal.getName() : null;

        return this.userDAO.findByUsername(username);
    }

    private record WebSocketEventListener(
        WebSocketSession session,
        User user,
        BiConsumer<WebSocketEventListener, Event> onEvent
    )
        implements EventListener {
        @Override
        public void onEvent(Event event) {
            this.onEvent.accept(this, event);
        }
    }
}
