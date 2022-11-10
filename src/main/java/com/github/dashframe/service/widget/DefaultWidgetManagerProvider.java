package com.github.dashframe.service.widget;

import com.github.dashframe.models.json.WidgetType;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

/**
 * The default implementation of {@link WidgetManagerProvider} used when dependency injecting.
 */
@Service
@Primary
public final class DefaultWidgetManagerProvider implements WidgetManagerProvider {

    @Override
    public WidgetManager<?, ?, ?> forType(WidgetType type) {
        // TODO return the widget managers here
        return null;
    }
}
