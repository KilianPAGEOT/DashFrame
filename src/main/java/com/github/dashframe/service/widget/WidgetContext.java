package com.github.dashframe.service.widget;

import com.github.dashframe.models.Widget;

/**
 * {@link WidgetManager}-specific data to pass to the {@link WidgetManager#fetchData} method.
 */
public interface WidgetContext {
    /**
     * @return The associated {@link Widget} instance.
     */
    Widget widget();
}
