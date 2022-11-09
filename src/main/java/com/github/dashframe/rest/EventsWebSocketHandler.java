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

    private final Map<WebSocketSession, EventListener> sessions = new ConcurrentHashMap<>();

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

        EventListener listener = event -> this.sendEvent(session, event);
        this.sessions.put(session, listener);

        var user = this.findSessionUser(session);

        if (user != null) this.eventHandler.addListener(user.getId(), listener);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        this.logger.info("Connection closed for session {}, reason: {}", session, status.getReason());

        EventListener listener = this.sessions.remove(session);
        if (listener != null) {
            var user = this.findSessionUser(session);

            if (user != null) this.eventHandler.removeListener(user.getId(), listener);
        }
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
     * @param session The target WS session.
     * @param event   The event to send to the client.
     */
    private void sendEvent(WebSocketSession session, Event event) {
        try {
            this.sendJsonMessage(session, event);
        } catch (Exception error) {
            logger.error("Closing session due to exception for " + session, error);
            if (session.isOpen()) {
                try {
                    session.close(CloseStatus.SERVER_ERROR);
                } catch (Throwable ex) {
                    // ignore
                }
            }
        }
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
}
