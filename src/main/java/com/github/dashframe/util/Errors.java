package com.github.dashframe.util;

import com.github.dashframe.models.json.ApiError;
import com.github.dashframe.models.json.WrappedApiError;
import java.util.Map;

/**
 * Utility functions for creating {@link Error} instances.
 */
public final class Errors {

    private static final int SOCKET_SERVER_TO_CLIENT_ONLY = 100;

    /**
     * An error that occurs when a client tried to send a message to a server message only WebSocket.
     *
     * @param channelName The WebSocket channel name
     * @return The error instance.
     */
    public static WrappedApiError serverToClientOnlySocket(String channelName) {
        return new WrappedApiError()
            .error(
                new ApiError()
                    .code(SOCKET_SERVER_TO_CLIENT_ONLY)
                    .message("WebSocket channel `" + channelName + "` cannot accept messages from clients")
                    .messageKey("error.socket.server_to_client_only")
                    .messageParams(Map.of("channelName", channelName))
            );
    }
}
