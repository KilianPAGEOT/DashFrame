package com.github.dashframe.service.widget;

import com.github.dashframe.service.service.ServiceContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractWidgetManager<
    ServiceContextT extends ServiceContext, WidgetContextT extends WidgetContext, DataT
>
    implements WidgetManager<ServiceContextT, WidgetContextT, DataT> {

    protected final Logger logger;

    protected AbstractWidgetManager() {
        this.logger = LoggerFactory.getLogger("WidgetManager(" + this.getType() + ')');
    }
}
