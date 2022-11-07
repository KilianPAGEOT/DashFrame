package com.github.dashframe.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
public class EventsWebSocketHandler extends TextWebSocketHandler {

    private final Logger logger = LoggerFactory.getLogger(EventsWebSocketHandler.class);

    @Override
    public void handleTransportError(WebSocketSession session, Throwable throwable) {
        logger.error("error occurred at sender " + session, throwable);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        logger.info(String.format("Session %s closed because of %s", session.getId(), status.getReason()));
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        logger.info("Connected  " + session.getId() + ", headers: " + session.getHandshakeHeaders());
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage jsonTextMessage) throws Exception {
        logger.debug("message received: " + jsonTextMessage.getPayload());
        session.sendMessage(new TextMessage("""
                {"value": "This is a response from the server"}"""));
    }
}
