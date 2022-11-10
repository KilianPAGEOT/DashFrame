package com.github.dashframe.service.service;

import com.github.dashframe.models.Service;
import com.github.dashframe.service.widget.WidgetManager;

/**
 * {@link ServiceManager}-specific data to pass to the {@link ServiceManager#destroy} and {@link WidgetManager#open} methods.
 */
public interface ServiceContext {
    /**
     * @return The corresponding {@link Service} instance.
     */
    Service service();
}
