package com.github.dashframe.service.event;

import com.github.dashframe.models.json.*;

public final class Events {

    public static final EventFactory<WidgetErrorEvent, WidgetErrorEventAllOfDataWidgets> WIDGET_ERROR = (
            timestamp,
            widgetErrors
        ) ->
        new WidgetErrorEvent()
            .type("widgets/error")
            .timestamp(timestamp)
            .data(new WidgetErrorEventAllOfData().widgets(widgetErrors));

    public static final EventFactory<WidgetDeletionEvent, Integer> WIDGET_DELETION = (timestamp, toDeleteIds) ->
        new WidgetDeletionEvent()
            .type("widgets/deletion")
            .timestamp(timestamp)
            .data(new WidgetDeletionEventAllOfData().widgetIds(toDeleteIds));

    public static final EventFactory<WidgetRefreshEvent, WidgetRefreshEventAllOfDataWidgets> WIDGET_REFRESH = (
            timestamp,
            newData
        ) ->
        new WidgetRefreshEvent()
            .type("widgets/refresh")
            .timestamp(timestamp)
            .data(new WidgetRefreshEventAllOfData().widgets(newData));
}
