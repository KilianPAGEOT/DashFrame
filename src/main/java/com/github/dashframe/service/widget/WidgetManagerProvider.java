package com.github.dashframe.service.widget;

import com.github.dashframe.models.json.WidgetType;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

/**
 * Provides access to {@link WidgetManager} instances for specific types.
 */
@Service
@FunctionalInterface
public interface WidgetManagerProvider {
    /**
     * @return A {@link WidgetManager} instance corresponding to the given type, or null if not supported/implemented.
     */
    @Nullable
    WidgetManager<?, ?, ?> forType(WidgetType type);

    /**
     * @return A {@link WidgetManager} instance corresponding to the given type, or null if not supported/implemented.
     */
    @Nullable
    default WidgetManager<?, ?, ?> forTypeName(String typeName) {
        for (var type : WidgetType.values()) {
            if (type.getValue().equals(typeName)) {
                return this.forType(type);
            }
        }
        return null;
    }
}
