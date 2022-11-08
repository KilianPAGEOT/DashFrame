package com.github.dashframe.service.event;

import com.github.dashframe.models.json.Event;
import java.util.List;

@FunctionalInterface
public interface EventFactory<E extends Event, D> {
    E makeEvent(Long timestamp, List<D> data);
}
