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
import type {
  CreateToken400Response,
  CreateToken401Response,
  CreateUser404Response,
  CreateUserRequest,
  UserInstance,
} from "../models";

export interface CreateUserOperationRequest {
  createUserRequest?: CreateUserRequest;
}

/**
 *
 */
export class UsersApi extends runtime.BaseAPI {
  /**
   * Create a new user instance
   */
  async createUserRaw(
    requestParameters: CreateUserOperationRequest,
    initOverrides?: RequestInit | runtime.InitOverrideFunction
  ): Promise<runtime.ApiResponse<UserInstance>> {
    const queryParameters: any = {};

    const headerParameters: runtime.HTTPHeaders = {};

    headerParameters["Content-Type"] = "application/json";

    const response = await this.request(
      {
        path: `/users`,
        method: "POST",
        headers: headerParameters,
        query: queryParameters,
        body: requestParameters.createUserRequest,
      },
      initOverrides
    );

    return new runtime.JSONApiResponse(response);
  }

  /**
   * Create a new user instance
   */
  async createUser(
    requestParameters: CreateUserOperationRequest = {},
    initOverrides?: RequestInit | runtime.InitOverrideFunction
  ): Promise<UserInstance> {
    const response = await this.createUserRaw(requestParameters, initOverrides);
    return await response.value();
  }
}
