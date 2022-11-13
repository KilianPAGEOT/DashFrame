import exp from "constants";
import {
  BASE_PATH,
  type ApiError,
  type Event,
  type WidgetDeletionEvent,
  type WidgetErrorEvent,
  type WidgetRefreshEvent,
  type WrappedApiError,
} from "./api";

interface UnknownEvent {
  type: string;
}

export function init() {
  const path = BASE_PATH.replace(/^(http|https)/, "ws");
  let eventsWs: WebSocket;

  try {
    eventsWs = new WebSocket(`${path}/ws/events`);
  } catch (error) {
    console.error("Failed to open events WebSocket", error);
    return;
  }

  console.debug("Sucessfully connected to events WebSocket");

  eventsWs.onerror = (event) => console.error("Events WS errored: ", event);

  eventsWs.onmessage = (event) => {
    const data: Event | WrappedApiError = JSON.parse(event.data);

    if ("type" in data && typeof data.type === "string") {
      onEvent(data);
    } else if ("error" in data) {
      onError(data.error);
    }
  };
}

function onEvent(event: Event) {
  console.debug(
    `Recieved event of type ${event.type} at timestamp ${event.timestamp}`
  );
  switch (event.type) {
    case "widgets/deletion":
      onWidgetDeletion(event);
      break;
    case "widgets/error":
      onWidgetError(event);
      break;
    case "widgets/refresh":
      onWidgetRefresh(event);
      break;
    default:
      console.error(`Unknown event type ${(event as UnknownEvent).type}`);
  }
}

function onError(error: ApiError) {
  if ("message" in error) {
    console.error(`Received error ${error.code}: ${error.message}`);
  } else {
    console.error(`Receieved unknown error ${error.code}`);
  }
}

function onWidgetError(event: WidgetErrorEvent) {
  for (let error of event.data.widgets) {
    if ("message" in error.error) {
      console.error(
        `Error ${error.error.code} for widget ${error.id}: ${error.error.message}`
      );
    } else {
      console.error(`Error ${error.error.code} for widget ${error.id}`);
    }
  }
}

function onWidgetDeletion(event: WidgetDeletionEvent) {
  for (let widgetId of event.data.widgetIds) {
    console.debug(`Requested deletion of widget ${widgetId}`);

    // inject your widget deleting code here
  }
}

function onWidgetRefresh(event: WidgetRefreshEvent) {
  for (let widget of event.data.widgets) {
    console.debug(`Requested update of widget ${widget.id}`, widget.data);

    widgetsData.set(widget.id, widget.data);
    const updater = widgetsUpdater.get(widget.id);
    if (updater !== undefined) {
      updater();
    }
  }
}

export const widgetsData: Map<number, unknown> = new Map();
export const widgetsUpdater: Map<number, () => void> = new Map();
