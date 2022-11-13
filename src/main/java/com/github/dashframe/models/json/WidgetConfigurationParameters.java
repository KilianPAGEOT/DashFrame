package com.github.dashframe.models.json;

import java.util.List;

public abstract class WidgetConfigurationParameters {

    protected static WidgetParameterDescription describeParameter(
        String name,
        WidgetParameterDescription.TypeEnum type
    ) {
        return new WidgetParameterDescription().name(name).type(type);
    }

    public abstract List<WidgetParameterDescription> describeParameters();
}
