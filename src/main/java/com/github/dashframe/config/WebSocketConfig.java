package com.github.dashframe.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    protected static final String WS_BASE_PATH = WebMvcConfig.API_BASE_PATH + "/ws/";

    @Autowired
    private EventsWebSocketHandler eventsWebSocketHandler;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(eventsWebSocketHandler, WS_BASE_PATH + "events").setAllowedOrigins("*");
    }
}
