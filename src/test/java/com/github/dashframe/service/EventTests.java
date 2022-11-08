package com.github.dashframe.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasEntry;
import static org.hamcrest.collection.IsIterableContainingInOrder.contains;
import static org.junit.jupiter.api.Assertions.*;

import com.github.dashframe.models.json.*;
import com.github.dashframe.service.event.EventHandler;
import com.github.dashframe.service.event.EventListener;
import com.github.dashframe.service.event.Events;
import com.github.dashframe.util.Errors;
import java.util.concurrent.atomic.AtomicInteger;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { EventTests.EventTestsConfig.class })
public final class EventTests {

    @Autowired
    private EventHandler eventHandler;

    @TestConfiguration
    static class EventTestsConfig {

        @Bean
        @Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
        public EventHandler eventHandler() {
            return new EventHandler();
        }
    }

    @Test
    void singleListener() {
        this.eventHandler.addListener(
                0,
                event -> {
                    assertEquals("widgets/deletion", event.getType());
                    assertInstanceOf(WidgetDeletionEvent.class, event);

                    var data = ((WidgetDeletionEvent) event).getData();

                    assertThat(data.getWidgetIds(), contains(42, 3, 8));
                }
            );

        this.eventHandler.broadcast(0, Events.WIDGET_DELETION, 42, 3, 8);
    }

    @Test
    void widgetError() {
        this.eventHandler.addListener(
                80,
                event -> {
                    assertEquals("widgets/error", event.getType());
                    assertInstanceOf(WidgetErrorEvent.class, event);

                    var data = ((WidgetErrorEvent) event).getData();
                    var error = data.getWidgets().get(0).getError();

                    assertNotNull(error);
                    assertEquals("error.socket.server_to_client_only", error.getMessageKey());
                    assertThat(error.getMessageParams(), hasEntry("channelName", "test"));
                }
            );

        this.eventHandler.broadcast(
                80,
                Events.WIDGET_ERROR,
                new WidgetErrorEventAllOfDataWidgets().error(Errors.serverToClientOnlySocket("test"))
            );
    }

    @Test
    void noMatchingListener() {
        this.eventHandler.addListener(80, event -> fail("The event handler for user 80 should not have been called"));

        this.eventHandler.broadcast(
                1,
                Events.WIDGET_ERROR,
                new WidgetErrorEventAllOfDataWidgets().error(Errors.serverToClientOnlySocket("test"))
            );
    }

    @Test
    @SuppressWarnings("FunctionalExpressionCanBeFolded")
    void multipleListeners() {
        var counter = new AtomicInteger(0);
        EventListener listener = event -> {
            assertEquals("widgets/refresh", event.getType());
            assertInstanceOf(WidgetRefreshEvent.class, event);
            counter.incrementAndGet();
        };

        this.eventHandler.addListener(42, listener::onEvent);
        this.eventHandler.addListener(42, listener::onEvent);
        this.eventHandler.addListener(42, listener::onEvent);
        this.eventHandler.addListener(84, listener::onEvent);

        this.eventHandler.broadcast(42, Events.WIDGET_REFRESH, new WidgetRefreshEventAllOfDataWidgets());

        assertEquals(3, counter.get(), "3 event listeners should have been called");
    }
}
