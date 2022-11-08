package com.github.dashframe.service.event;

import com.github.dashframe.models.json.Event;

@FunctionalInterface
public interface EventListener {
    void onEvent(Event event);
}
