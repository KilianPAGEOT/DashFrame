/* tslint:disable */
/* eslint-disable */
/**
 * DashFrame API
 * ## Authentification  This API uses tokens of an unknown type. The token is in the `Authorization` header of the request.    Example: ``` Authorization: gho_pJ9dGXVKpfzZp4PUHSxYEq9hjk0h288Gwj4S ```  ## Typical usage:  Once logged-in, the client will fetch the widgets on the board: ``` GET $API_URL/widgets ```  Then it will listen for widget update events by starting a WebSocket connection: ``` GET $API_URL/ws/events ```  In the meantime, the client will fetch the list of available widget and services: ``` GET $API_URL/services ```
 *
 * The version of the OpenAPI document: 0.1.0
 *
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */

import * as runtime from "../runtime";
import type { CreateToken401Response, CreateUser404Response } from "../models";

export interface EventsWebsocketRequest {
  userId?: number;
  upgrade?: string;
  secWebSocketKey?: string;
  secWebSocketProtocol?: string;
  secWebSocketVersion?: string;
}

/**
 *
 */
export class EventsApi extends runtime.BaseAPI {
  /**
   * Subscribes to the events WebSocket of the given user, or the current user if not present
   */
  async eventsWebsocketRaw(
    requestParameters: EventsWebsocketRequest,
    initOverrides?: RequestInit | runtime.InitOverrideFunction
  ): Promise<runtime.ApiResponse<void>> {
    const queryParameters: any = {};

    if (requestParameters.userId !== undefined) {
      queryParameters["userId"] = requestParameters.userId;
    }

    const headerParameters: runtime.HTTPHeaders = {};

    if (
      requestParameters.upgrade !== undefined &&
      requestParameters.upgrade !== null
    ) {
      headerParameters["Upgrade"] = String(requestParameters.upgrade);
    }

    if (
      requestParameters.secWebSocketKey !== undefined &&
      requestParameters.secWebSocketKey !== null
    ) {
      headerParameters["Sec-WebSocket-Key"] = String(
        requestParameters.secWebSocketKey
      );
    }

    if (
      requestParameters.secWebSocketProtocol !== undefined &&
      requestParameters.secWebSocketProtocol !== null
    ) {
      headerParameters["Sec-WebSocket-Protocol"] = String(
        requestParameters.secWebSocketProtocol
      );
    }

    if (
      requestParameters.secWebSocketVersion !== undefined &&
      requestParameters.secWebSocketVersion !== null
    ) {
      headerParameters["Sec-WebSocket-Version"] = String(
        requestParameters.secWebSocketVersion
      );
    }

    if (this.configuration && this.configuration.accessToken) {
      const token = this.configuration.accessToken;
      const tokenString = await token("apiKey", []);

      if (tokenString) {
        headerParameters["Authorization"] = `Bearer ${tokenString}`;
      }
    }
    const response = await this.request(
      {
        path: `/ws/events`,
        method: "GET",
        headers: headerParameters,
        query: queryParameters,
      },
      initOverrides
    );

    return new runtime.VoidApiResponse(response);
  }

  /**
   * Subscribes to the events WebSocket of the given user, or the current user if not present
   */
  async eventsWebsocket(
    requestParameters: EventsWebsocketRequest = {},
    initOverrides?: RequestInit | runtime.InitOverrideFunction
  ): Promise<void> {
    await this.eventsWebsocketRaw(requestParameters, initOverrides);
  }
}
