/* tslint:disable */
/* eslint-disable */
/**
 * Common properties shared by any server-sent event, not a full event by itself
 * @export
 * @interface BaseEvent
 */
export interface BaseEvent {
  /**
   * The type of event, one of the values of EventType
   * @type {string}
   * @memberof BaseEvent
   */
  type?: string;
  /**
   * The UNIX timestamp of the event creation
   * @type {number}
   * @memberof BaseEvent
   */
  timestamp: number;
}
/**
 *
 * @export
 * @interface BaseWidgetConfiguration
 */
export interface BaseWidgetConfiguration {
  /**
   * The type of widget, one of the values of WidgetType
   * @type {string}
   * @memberof BaseWidgetConfiguration
   */
  type: string;
  /**
   * Optional user-defined name for the widget
   * @type {string}
   * @memberof BaseWidgetConfiguration
   */
  name?: string;
  /**
   * Refresh rate of the widget, in seconds
   * @type {number}
   * @memberof BaseWidgetConfiguration
   */
  refreshRate?: number;
}
/**
 * Information about the client that executed this request
 * @export
 * @interface ClientInformation
 */
export interface ClientInformation {
  /**
   * Indicates the identifier of this parameter
   * @type {string}
   * @memberof ClientInformation
   */
  name: string;
  /**
   * Indicates the type of this parameter
   * @type {string}
   * @memberof ClientInformation
   */
  type: ClientInformationTypeEnum;
}

/**
 * @export
 */
export const ClientInformationTypeEnum = {
  String: "string",
  Integer: "integer",
} as const;
export type ClientInformationTypeEnum =
  typeof ClientInformationTypeEnum[keyof typeof ClientInformationTypeEnum];

/**
 *
 * @export
 * @interface CreateServiceRequest
 */
export interface CreateServiceRequest {
  /**
   * A resource identifier
   * @type {number}
   * @memberof CreateServiceRequest
   */
  userId?: number;
  /**
   *
   * @type {ServiceType}
   * @memberof CreateServiceRequest
   */
  type: ServiceType;
  /**
   * The token returned by the external service, optional if not needed
   * @type {string}
   * @memberof CreateServiceRequest
   */
  token?: string;
  /**
   * A string that identifies this specific account, not needed if the services does not have accounts
   * @type {string}
   * @memberof CreateServiceRequest
   */
  username?: string;
}
/**
 *
 * @export
 * @interface CreateServiceRequestAllOf
 */
export interface CreateServiceRequestAllOf {
  /**
   *
   * @type {ServiceType}
   * @memberof CreateServiceRequestAllOf
   */
  type: ServiceType;
  /**
   * The token returned by the external service, optional if not needed
   * @type {string}
   * @memberof CreateServiceRequestAllOf
   */
  token?: string;
  /**
   * A string that identifies this specific account, not needed if the services does not have accounts
   * @type {string}
   * @memberof CreateServiceRequestAllOf
   */
  username?: string;
}
/**
 *
 * @export
 * @interface CreateToken400Response
 */
export interface CreateToken400Response {
  /**
   *
   * @type {Error}
   * @memberof CreateToken400Response
   */
  error?: Error;
}
/**
 *
 * @export
 * @interface CreateToken401Response
 */
export interface CreateToken401Response {
  /**
   *
   * @type {Error}
   * @memberof CreateToken401Response
   */
  error?: Error;
}
/**
 *
 * @export
 * @interface CreateUser404Response
 */
export interface CreateUser404Response {
  /**
   *
   * @type {Error}
   * @memberof CreateUser404Response
   */
  error?: Error;
}
/**
 *
 * @export
 * @interface CreateUserRequest
 */
export interface CreateUserRequest {
  /**
   * A resource identifier
   * @type {number}
   * @memberof CreateUserRequest
   */
  userId?: number;
  /**
   * The user's name
   * @type {string}
   * @memberof CreateUserRequest
   */
  name: string;
  /**
   * A unique identifier that can be any format like email or number sequence
   * @type {string}
   * @memberof CreateUserRequest
   */
  username: string;
  /**
   * The password of the user
   * @type {string}
   * @memberof CreateUserRequest
   */
  hashPassword: string;
  /**
   * The token returned by the external service, optional if not needed
   * @type {string}
   * @memberof CreateUserRequest
   */
  oauthToken?: string;
}
/**
 *
 * @export
 * @interface CreateUserRequestAllOf
 */
export interface CreateUserRequestAllOf {
  /**
   * The user's name
   * @type {string}
   * @memberof CreateUserRequestAllOf
   */
  name: string;
  /**
   * A unique identifier that can be any format like email or number sequence
   * @type {string}
   * @memberof CreateUserRequestAllOf
   */
  username: string;
  /**
   * The password of the user
   * @type {string}
   * @memberof CreateUserRequestAllOf
   */
  hashPassword: string;
  /**
   * The token returned by the external service, optional if not needed
   * @type {string}
   * @memberof CreateUserRequestAllOf
   */
  oauthToken?: string;
}
/**
 *
 * @export
 * @interface CreateWidgetRequest
 */
export interface CreateWidgetRequest {
  /**
   * A resource identifier
   * @type {number}
   * @memberof CreateWidgetRequest
   */
  userId?: number;
  /**
   * A resource identifier
   * @type {number}
   * @memberof CreateWidgetRequest
   */
  serviceId: number;
  /**
   *
   * @type {WidgetConfiguration}
   * @memberof CreateWidgetRequest
   */
  config: WidgetConfiguration;
}
/**
 *
 * @export
 * @interface CreateWidgetRequestAllOf
 */
export interface CreateWidgetRequestAllOf {
  /**
   * A resource identifier
   * @type {number}
   * @memberof CreateWidgetRequestAllOf
   */
  serviceId: number;
  /**
   *
   * @type {WidgetConfiguration}
   * @memberof CreateWidgetRequestAllOf
   */
  config: WidgetConfiguration;
}
/**
 *
 * @export
 * @interface EpicGamesFreeGamesWidgetConfiguration
 */
export interface EpicGamesFreeGamesWidgetConfiguration {
  /**
   * The type of widget, one of the values of WidgetType
   * @type {string}
   * @memberof EpicGamesFreeGamesWidgetConfiguration
   */
  type: string;
  /**
   * Optional user-defined name for the widget
   * @type {string}
   * @memberof EpicGamesFreeGamesWidgetConfiguration
   */
  name?: string;
  /**
   * Refresh rate of the widget, in seconds
   * @type {number}
   * @memberof EpicGamesFreeGamesWidgetConfiguration
   */
  refreshRate?: number;
}
/**
 *
 * @export
 * @interface EpicGamesFriendsListWidgetConfiguration
 */
export interface EpicGamesFriendsListWidgetConfiguration {
  /**
   * The type of widget, one of the values of WidgetType
   * @type {string}
   * @memberof EpicGamesFriendsListWidgetConfiguration
   */
  type: string;
  /**
   * Optional user-defined name for the widget
   * @type {string}
   * @memberof EpicGamesFriendsListWidgetConfiguration
   */
  name?: string;
  /**
   * Refresh rate of the widget, in seconds
   * @type {number}
   * @memberof EpicGamesFriendsListWidgetConfiguration
   */
  refreshRate?: number;
  /**
   *
   * @type {EpicGamesFriendsListWidgetConfigurationAllOfParameters}
   * @memberof EpicGamesFriendsListWidgetConfiguration
   */
  parameters: EpicGamesFriendsListWidgetConfigurationAllOfParameters;
}
/**
 * Configuration for the Epic Games friends list widget
 * @export
 * @interface EpicGamesFriendsListWidgetConfigurationAllOf
 */
export interface EpicGamesFriendsListWidgetConfigurationAllOf {
  /**
   *
   * @type {EpicGamesFriendsListWidgetConfigurationAllOfParameters}
   * @memberof EpicGamesFriendsListWidgetConfigurationAllOf
   */
  parameters: EpicGamesFriendsListWidgetConfigurationAllOfParameters;
}
/**
 *
 * @export
 * @interface EpicGamesFriendsListWidgetConfigurationAllOfParameters
 */
export interface EpicGamesFriendsListWidgetConfigurationAllOfParameters {
  /**
   *
   * @type {number}
   * @memberof EpicGamesFriendsListWidgetConfigurationAllOfParameters
   */
  showOffline: EpicGamesFriendsListWidgetConfigurationAllOfParametersShowOfflineEnum;
}

/**
 * @export
 */
export const EpicGamesFriendsListWidgetConfigurationAllOfParametersShowOfflineEnum =
  {
    NUMBER_0: 0,
    NUMBER_1: 1,
  } as const;
export type EpicGamesFriendsListWidgetConfigurationAllOfParametersShowOfflineEnum =
  typeof EpicGamesFriendsListWidgetConfigurationAllOfParametersShowOfflineEnum[keyof typeof EpicGamesFriendsListWidgetConfigurationAllOfParametersShowOfflineEnum];

/**
 * @type Event
 * Any server-sent event
 * @export
 */
export type Event =
  | ({ type: "widgets/deletion" } & WidgetDeletionEvent)
  | ({ type: "widgets/error" } & WidgetErrorEvent)
  | ({ type: "widgets/refresh" } & WidgetRefreshEvent);

/**
 *
 * @export
 */
export const EventType = {
  Deletion: "widgets/deletion",
  Error: "widgets/error",
  Refresh: "widgets/refresh",
} as const;
export type EventType = typeof EventType[keyof typeof EventType];

/**
 *
 * @export
 * @interface GetAboutJson200Response
 */
export interface GetAboutJson200Response {
  /**
   *
   * @type {ClientInformation}
   * @memberof GetAboutJson200Response
   */
  client: ClientInformation;
  /**
   *
   * @type {ServerInformation}
   * @memberof GetAboutJson200Response
   */
  server: ServerInformation;
}
/**
 *
 * @export
 * @interface ListServices200Response
 */
export interface ListServices200Response {
  /**
   * Indicates the list of Services supported by the server
   * @type {Array<ServiceDescription>}
   * @memberof ListServices200Response
   */
  available: Array<ServiceDescription>;
  /**
   *
   * @type {Array<ServiceInstance>}
   * @memberof ListServices200Response
   */
  instances: Array<ServiceInstance>;
}
/**
 * An error response object
 * @export
 * @interface ModelError
 */
export interface ModelError {
  /**
   * The unique error code
   * @type {number}
   * @memberof ModelError
   */
  code?: number;
  /**
   * The error message in english, not required
   * @type {string}
   * @memberof ModelError
   */
  message?: string;
  /**
   * The error message localization key, not required
   * @type {string}
   * @memberof ModelError
   */
  messageKey?: string;
  /**
   * The parameters used in the localization of this error message, assumed to be empty if not present
   * @type {{ [key: string]: string; }}
   * @memberof ModelError
   */
  messageParams?: { [key: string]: string };
}
/**
 * The ID of an user, assumed to be the current user if not specified
 * @export
 * @interface OptionalUserIdentifier
 */
export interface OptionalUserIdentifier {
  /**
   * A resource identifier
   * @type {number}
   * @memberof OptionalUserIdentifier
   */
  userId?: number;
}
/**
 *
 * @export
 * @interface RssFeedWidgetConfiguration
 */
export interface RssFeedWidgetConfiguration {
  /**
   * The type of widget, one of the values of WidgetType
   * @type {string}
   * @memberof RssFeedWidgetConfiguration
   */
  type: string;
  /**
   * Optional user-defined name for the widget
   * @type {string}
   * @memberof RssFeedWidgetConfiguration
   */
  name?: string;
  /**
   * Refresh rate of the widget, in seconds
   * @type {number}
   * @memberof RssFeedWidgetConfiguration
   */
  refreshRate?: number;
  /**
   *
   * @type {RssFeedWidgetConfigurationAllOfParameters}
   * @memberof RssFeedWidgetConfiguration
   */
  parameters: RssFeedWidgetConfigurationAllOfParameters;
}
/**
 * Configuration for the RSS feed widget
 * @export
 * @interface RssFeedWidgetConfigurationAllOf
 */
export interface RssFeedWidgetConfigurationAllOf {
  /**
   *
   * @type {RssFeedWidgetConfigurationAllOfParameters}
   * @memberof RssFeedWidgetConfigurationAllOf
   */
  parameters: RssFeedWidgetConfigurationAllOfParameters;
}
/**
 *
 * @export
 * @interface RssFeedWidgetConfigurationAllOfParameters
 */
export interface RssFeedWidgetConfigurationAllOfParameters {
  /**
   *
   * @type {string}
   * @memberof RssFeedWidgetConfigurationAllOfParameters
   */
  url: string;
}
/**
 * Information about the server that is receiving this request
 * @export
 * @interface ServerInformation
 */
export interface ServerInformation {
  /**
   * Indicates the server time in the Epoch Unix Time Stamp format
   * @type {number}
   * @memberof ServerInformation
   */
  currentTime: number;
  /**
   * Indicates the list of Services supported by the server
   * @type {Array<ServiceDescription>}
   * @memberof ServerInformation
   */
  services: Array<ServiceDescription>;
}
/**
 *
 * @export
 * @interface ServiceDescription
 */
export interface ServiceDescription {
  /**
   * Indicates the name of the Service
   * @type {string}
   * @memberof ServiceDescription
   */
  name: string;
  /**
   * The image URL of the service
   * @type {string}
   * @memberof ServiceDescription
   */
  iconUrl?: string;
  /**
   * Describes the service
   * @type {string}
   * @memberof ServiceDescription
   */
  description?: string;
  /**
   * Indicates the list of Widgets supported by this Service
   * @type {Array<WidgetDescription>}
   * @memberof ServiceDescription
   */
  widgets: Array<WidgetDescription>;
}
/**
 * A service instance, a user may possess multiple instances (accounts) for any given service
 * @export
 * @interface ServiceInstance
 */
export interface ServiceInstance {
  /**
   * A resource identifier
   * @type {number}
   * @memberof ServiceInstance
   */
  id: number;
  /**
   *
   * @type {ServiceType}
   * @memberof ServiceInstance
   */
  type: ServiceType;
  /**
   * An username/email, unique accross service instances of a user
   * @type {string}
   * @memberof ServiceInstance
   */
  username?: string;
  /**
   * Login token for the service
   * @type {string}
   * @memberof ServiceInstance
   */
  token?: string;
  /**
   * Created date-time
   * @type {string}
   * @memberof ServiceInstance
   */
  createdAt?: string;
}

/**
 * Service unique identifier
 * @export
 */
export const ServiceType = {
  EpicGames: "epic_games",
  Rss: "rss",
  Steam: "steam",
  Twitch: "twitch",
  WeatherTime: "weather_time",
  Youtube: "youtube",
} as const;
export type ServiceType = typeof ServiceType[keyof typeof ServiceType];

/**
 *
 * @export
 * @interface SteamFriendsListWidgetConfiguration
 */
export interface SteamFriendsListWidgetConfiguration {
  /**
   * The type of widget, one of the values of WidgetType
   * @type {string}
   * @memberof SteamFriendsListWidgetConfiguration
   */
  type: string;
  /**
   * Optional user-defined name for the widget
   * @type {string}
   * @memberof SteamFriendsListWidgetConfiguration
   */
  name?: string;
  /**
   * Refresh rate of the widget, in seconds
   * @type {number}
   * @memberof SteamFriendsListWidgetConfiguration
   */
  refreshRate?: number;
  /**
   *
   * @type {EpicGamesFriendsListWidgetConfigurationAllOfParameters}
   * @memberof SteamFriendsListWidgetConfiguration
   */
  parameters: EpicGamesFriendsListWidgetConfigurationAllOfParameters;
}
/**
 * Configuration for the Steam friends list widget
 * @export
 * @interface SteamFriendsListWidgetConfigurationAllOf
 */
export interface SteamFriendsListWidgetConfigurationAllOf {
  /**
   *
   * @type {EpicGamesFriendsListWidgetConfigurationAllOfParameters}
   * @memberof SteamFriendsListWidgetConfigurationAllOf
   */
  parameters: EpicGamesFriendsListWidgetConfigurationAllOfParameters;
}
/**
 *
 * @export
 * @interface SteamGameNewsWidgetConfiguration
 */
export interface SteamGameNewsWidgetConfiguration {
  /**
   * The type of widget, one of the values of WidgetType
   * @type {string}
   * @memberof SteamGameNewsWidgetConfiguration
   */
  type: string;
  /**
   * Optional user-defined name for the widget
   * @type {string}
   * @memberof SteamGameNewsWidgetConfiguration
   */
  name?: string;
  /**
   * Refresh rate of the widget, in seconds
   * @type {number}
   * @memberof SteamGameNewsWidgetConfiguration
   */
  refreshRate?: number;
  /**
   *
   * @type {SteamGameNewsWidgetConfigurationAllOfParameters}
   * @memberof SteamGameNewsWidgetConfiguration
   */
  parameters: SteamGameNewsWidgetConfigurationAllOfParameters;
}
/**
 * Configuration for the Steam game news widget
 * @export
 * @interface SteamGameNewsWidgetConfigurationAllOf
 */
export interface SteamGameNewsWidgetConfigurationAllOf {
  /**
   *
   * @type {SteamGameNewsWidgetConfigurationAllOfParameters}
   * @memberof SteamGameNewsWidgetConfigurationAllOf
   */
  parameters: SteamGameNewsWidgetConfigurationAllOfParameters;
}
/**
 *
 * @export
 * @interface SteamGameNewsWidgetConfigurationAllOfParameters
 */
export interface SteamGameNewsWidgetConfigurationAllOfParameters {
  /**
   *
   * @type {string}
   * @memberof SteamGameNewsWidgetConfigurationAllOfParameters
   */
  gameNameOrId: string;
}
/**
 *
 * @export
 * @interface SteamGamePopulationWidgetConfiguration
 */
export interface SteamGamePopulationWidgetConfiguration {
  /**
   * The type of widget, one of the values of WidgetType
   * @type {string}
   * @memberof SteamGamePopulationWidgetConfiguration
   */
  type: string;
  /**
   * Optional user-defined name for the widget
   * @type {string}
   * @memberof SteamGamePopulationWidgetConfiguration
   */
  name?: string;
  /**
   * Refresh rate of the widget, in seconds
   * @type {number}
   * @memberof SteamGamePopulationWidgetConfiguration
   */
  refreshRate?: number;
  /**
   *
   * @type {SteamGameNewsWidgetConfigurationAllOfParameters}
   * @memberof SteamGamePopulationWidgetConfiguration
   */
  parameters: SteamGameNewsWidgetConfigurationAllOfParameters;
}
/**
 * Configuration for the Steam game population widget
 * @export
 * @interface SteamGamePopulationWidgetConfigurationAllOf
 */
export interface SteamGamePopulationWidgetConfigurationAllOf {
  /**
   *
   * @type {SteamGameNewsWidgetConfigurationAllOfParameters}
   * @memberof SteamGamePopulationWidgetConfigurationAllOf
   */
  parameters: SteamGameNewsWidgetConfigurationAllOfParameters;
}
/**
 *
 * @export
 * @interface TwitchFollowedChannelsWidgetConfiguration
 */
export interface TwitchFollowedChannelsWidgetConfiguration {
  /**
   * The type of widget, one of the values of WidgetType
   * @type {string}
   * @memberof TwitchFollowedChannelsWidgetConfiguration
   */
  type: string;
  /**
   * Optional user-defined name for the widget
   * @type {string}
   * @memberof TwitchFollowedChannelsWidgetConfiguration
   */
  name?: string;
  /**
   * Refresh rate of the widget, in seconds
   * @type {number}
   * @memberof TwitchFollowedChannelsWidgetConfiguration
   */
  refreshRate?: number;
  /**
   *
   * @type {TwitchFollowedChannelsWidgetConfigurationAllOfParameters}
   * @memberof TwitchFollowedChannelsWidgetConfiguration
   */
  parameters: TwitchFollowedChannelsWidgetConfigurationAllOfParameters;
}
/**
 * Configuration for the Twitch list of followed channels
 * @export
 * @interface TwitchFollowedChannelsWidgetConfigurationAllOf
 */
export interface TwitchFollowedChannelsWidgetConfigurationAllOf {
  /**
   *
   * @type {TwitchFollowedChannelsWidgetConfigurationAllOfParameters}
   * @memberof TwitchFollowedChannelsWidgetConfigurationAllOf
   */
  parameters: TwitchFollowedChannelsWidgetConfigurationAllOfParameters;
}
/**
 *
 * @export
 * @interface TwitchFollowedChannelsWidgetConfigurationAllOfParameters
 */
export interface TwitchFollowedChannelsWidgetConfigurationAllOfParameters {
  /**
   *
   * @type {string}
   * @memberof TwitchFollowedChannelsWidgetConfigurationAllOfParameters
   */
  filter: TwitchFollowedChannelsWidgetConfigurationAllOfParametersFilterEnum;
}

/**
 * @export
 */
export const TwitchFollowedChannelsWidgetConfigurationAllOfParametersFilterEnum =
  {
    All: "ALL",
    StreamingOnly: "STREAMING_ONLY",
  } as const;
export type TwitchFollowedChannelsWidgetConfigurationAllOfParametersFilterEnum =
  typeof TwitchFollowedChannelsWidgetConfigurationAllOfParametersFilterEnum[keyof typeof TwitchFollowedChannelsWidgetConfigurationAllOfParametersFilterEnum];

/**
 *
 * @export
 * @interface UnknownWidgetConfiguration
 */
export interface UnknownWidgetConfiguration {
  /**
   * The type of widget, one of the values of WidgetType
   * @type {string}
   * @memberof UnknownWidgetConfiguration
   */
  type: string;
  /**
   * Optional user-defined name for the widget
   * @type {string}
   * @memberof UnknownWidgetConfiguration
   */
  name?: string;
  /**
   * Refresh rate of the widget, in seconds
   * @type {number}
   * @memberof UnknownWidgetConfiguration
   */
  refreshRate?: number;
}
/**
 *
 * @export
 * @interface UpdateWidgetRequest
 */
export interface UpdateWidgetRequest {
  /**
   *
   * @type {WidgetConfiguration}
   * @memberof UpdateWidgetRequest
   */
  config: WidgetConfiguration;
}
/**
 * A user instance
 * @export
 * @interface UserInstance
 */
export interface UserInstance {
  /**
   * A resource identifier
   * @type {number}
   * @memberof UserInstance
   */
  id: number;
  /**
   * The user's name
   * @type {string}
   * @memberof UserInstance
   */
  name: string;
  /**
   * A unique identifier that can be any format like email or number sequence
   * @type {string}
   * @memberof UserInstance
   */
  username?: string;
  /**
   * If the user is admin or not
   * @type {boolean}
   * @memberof UserInstance
   */
  isAdmin: boolean;
  /**
   * Created date-time
   * @type {string}
   * @memberof UserInstance
   */
  createdAt?: string;
}
/**
 *
 * @export
 * @interface WeatherTimeWidgetConfiguration
 */
export interface WeatherTimeWidgetConfiguration {
  /**
   * The type of widget, one of the values of WidgetType
   * @type {string}
   * @memberof WeatherTimeWidgetConfiguration
   */
  type: string;
  /**
   * Optional user-defined name for the widget
   * @type {string}
   * @memberof WeatherTimeWidgetConfiguration
   */
  name?: string;
  /**
   * Refresh rate of the widget, in seconds
   * @type {number}
   * @memberof WeatherTimeWidgetConfiguration
   */
  refreshRate?: number;
  /**
   *
   * @type {WeatherTimeWidgetConfigurationAllOfParameters}
   * @memberof WeatherTimeWidgetConfiguration
   */
  parameters: WeatherTimeWidgetConfigurationAllOfParameters;
}
/**
 * Configuration for the weather & time widget
 * @export
 * @interface WeatherTimeWidgetConfigurationAllOf
 */
export interface WeatherTimeWidgetConfigurationAllOf {
  /**
   *
   * @type {WeatherTimeWidgetConfigurationAllOfParameters}
   * @memberof WeatherTimeWidgetConfigurationAllOf
   */
  parameters: WeatherTimeWidgetConfigurationAllOfParameters;
}
/**
 *
 * @export
 * @interface WeatherTimeWidgetConfigurationAllOfParameters
 */
export interface WeatherTimeWidgetConfigurationAllOfParameters {
  /**
   * City name or address
   * @type {string}
   * @memberof WeatherTimeWidgetConfigurationAllOfParameters
   */
  location: string;
}
/**
 * @type WidgetConfiguration
 *
 * @export
 */
export type WidgetConfiguration =
  | ({ type: "epic_games/free_games" } & EpicGamesFreeGamesWidgetConfiguration)
  | ({
      type: "epic_games/friends_list";
    } & EpicGamesFriendsListWidgetConfiguration)
  | ({ type: "rss/feed" } & RssFeedWidgetConfiguration)
  | ({ type: "steam/friends_list" } & SteamFriendsListWidgetConfiguration)
  | ({ type: "steam/game_news" } & SteamGameNewsWidgetConfiguration)
  | ({ type: "steam/game_population" } & SteamGamePopulationWidgetConfiguration)
  | ({
      type: "twitch/followed_channels";
    } & TwitchFollowedChannelsWidgetConfiguration)
  | ({ type: "weather_time/default" } & WeatherTimeWidgetConfiguration)
  | ({
      type: "youtube/channel_statistics";
    } & YouTubeChannelStatisticsWidgetConfiguration)
  | ({
      type: "youtube/subsribed_channels";
    } & YouTubeSubscribedChannelsWidgetConfiguration)
  | ({
      type: "youtube/video_statistics";
    } & YouTubeVideoStatisticsWidgetConfiguration);
/**
 *
 * @export
 * @interface WidgetDeletionEvent
 */
export interface WidgetDeletionEvent {
  /**
   * The type of event, one of the values of EventType
   * @type {string}
   * @memberof WidgetDeletionEvent
   */
  type?: string;
  /**
   * The UNIX timestamp of the event creation
   * @type {number}
   * @memberof WidgetDeletionEvent
   */
  timestamp: number;
  /**
   *
   * @type {WidgetDeletionEventAllOfData}
   * @memberof WidgetDeletionEvent
   */
  data: WidgetDeletionEventAllOfData;
}
/**
 * Forces a widget to be delete if it still exists on the client side
 * @export
 * @interface WidgetDeletionEventAllOf
 */
export interface WidgetDeletionEventAllOf {
  /**
   *
   * @type {WidgetDeletionEventAllOfData}
   * @memberof WidgetDeletionEventAllOf
   */
  data: WidgetDeletionEventAllOfData;
}
/**
 * Event data
 * @export
 * @interface WidgetDeletionEventAllOfData
 */
export interface WidgetDeletionEventAllOfData {
  /**
   * The IDs of the widgets to be deleted
   * @type {Array<number>}
   * @memberof WidgetDeletionEventAllOfData
   */
  widgetIds: Array<number>;
}
/**
 *
 * @export
 * @interface WidgetDescription
 */
export interface WidgetDescription {
  /**
   * Indicates the identifier of this Widget
   * @type {string}
   * @memberof WidgetDescription
   */
  name: string;
  /**
   *
   * @type {string}
   * @memberof WidgetDescription
   */
  description: string;
  /**
   * Indicates the list of parameters to configure this Widget
   * @type {Array<WidgetParameterDescription>}
   * @memberof WidgetDescription
   */
  params: Array<WidgetParameterDescription>;
}
/**
 *
 * @export
 * @interface WidgetErrorEvent
 */
export interface WidgetErrorEvent {
  /**
   * The type of event, one of the values of EventType
   * @type {string}
   * @memberof WidgetErrorEvent
   */
  type?: string;
  /**
   * The UNIX timestamp of the event creation
   * @type {number}
   * @memberof WidgetErrorEvent
   */
  timestamp: number;
  /**
   *
   * @type {WidgetErrorEventAllOfData}
   * @memberof WidgetErrorEvent
   */
  data: WidgetErrorEventAllOfData;
}
/**
 * Widget error event
 * @export
 * @interface WidgetErrorEventAllOf
 */
export interface WidgetErrorEventAllOf {
  /**
   *
   * @type {WidgetErrorEventAllOfData}
   * @memberof WidgetErrorEventAllOf
   */
  data: WidgetErrorEventAllOfData;
}
/**
 * Event data
 * @export
 * @interface WidgetErrorEventAllOfData
 */
export interface WidgetErrorEventAllOfData {
  /**
   *
   * @type {Array<WidgetErrorEventAllOfDataWidgets>}
   * @memberof WidgetErrorEventAllOfData
   */
  widgets: Array<WidgetErrorEventAllOfDataWidgets>;
}
/**
 *
 * @export
 * @interface WidgetErrorEventAllOfDataWidgets
 */
export interface WidgetErrorEventAllOfDataWidgets {
  /**
   * A resource identifier
   * @type {number}
   * @memberof WidgetErrorEventAllOfDataWidgets
   */
  id: number;
  /**
   *
   * @type {Error}
   * @memberof WidgetErrorEventAllOfDataWidgets
   */
  error: Error;
}
/**
 *
 * @export
 * @interface WidgetInstance
 */
export interface WidgetInstance {
  /**
   * A resource identifier
   * @type {number}
   * @memberof WidgetInstance
   */
  id: number;
  /**
   * User-defined name of this widget component, optional
   * @type {string}
   * @memberof WidgetInstance
   */
  name?: string;
  /**
   * A resource identifier
   * @type {number}
   * @memberof WidgetInstance
   */
  serviceId?: number;
  /**
   * The ordering of this widget in the column
   * @type {number}
   * @memberof WidgetInstance
   */
  order: number;
  /**
   * The column id of the widget
   * @type {number}
   * @memberof WidgetInstance
   */
  columnPos?: number;
  /**
   *
   * @type {WidgetConfiguration}
   * @memberof WidgetInstance
   */
  config?: WidgetConfiguration;
  /**
   * Created date-time
   * @type {string}
   * @memberof WidgetInstance
   */
  createdAt?: string;
}
/**
 * Describes a widget parameter
 * @export
 * @interface WidgetParameterDescription
 */
export interface WidgetParameterDescription {
  /**
   * Indicates the identifier of this parameter
   * @type {string}
   * @memberof WidgetParameterDescription
   */
  name: string;
  /**
   * Indicates the type of this parameter
   * @type {string}
   * @memberof WidgetParameterDescription
   */
  type: WidgetParameterDescriptionTypeEnum;
}

/**
 * @export
 */
export const WidgetParameterDescriptionTypeEnum = {
  String: "string",
  Integer: "integer",
} as const;
export type WidgetParameterDescriptionTypeEnum =
  typeof WidgetParameterDescriptionTypeEnum[keyof typeof WidgetParameterDescriptionTypeEnum];

/**
 *
 * @export
 * @interface WidgetRefreshEvent
 */
export interface WidgetRefreshEvent {
  /**
   * The type of event, one of the values of EventType
   * @type {string}
   * @memberof WidgetRefreshEvent
   */
  type?: string;
  /**
   * The UNIX timestamp of the event creation
   * @type {number}
   * @memberof WidgetRefreshEvent
   */
  timestamp: number;
  /**
   *
   * @type {WidgetRefreshEventAllOfData}
   * @memberof WidgetRefreshEvent
   */
  data: WidgetRefreshEventAllOfData;
}
/**
 * Widget data update event
 * @export
 * @interface WidgetRefreshEventAllOf
 */
export interface WidgetRefreshEventAllOf {
  /**
   *
   * @type {WidgetRefreshEventAllOfData}
   * @memberof WidgetRefreshEventAllOf
   */
  data: WidgetRefreshEventAllOfData;
}
/**
 * Event data
 * @export
 * @interface WidgetRefreshEventAllOfData
 */
export interface WidgetRefreshEventAllOfData {
  /**
   *
   * @type {Array<WidgetRefreshEventAllOfDataWidgets>}
   * @memberof WidgetRefreshEventAllOfData
   */
  widgets: Array<WidgetRefreshEventAllOfDataWidgets>;
}
/**
 *
 * @export
 * @interface WidgetRefreshEventAllOfDataWidgets
 */
export interface WidgetRefreshEventAllOfDataWidgets {
  /**
   * A resource identifier
   * @type {number}
   * @memberof WidgetRefreshEventAllOfDataWidgets
   */
  id: number;
  /**
   * Widget-specific data
   * @type {object}
   * @memberof WidgetRefreshEventAllOfDataWidgets
   */
  data: object;
}

/**
 *
 * @export
 */
export const WidgetType = {
  EpicGamesFreeGames: "epic_games/free_games",
  EpicGamesFriendsList: "epic_games/friends_list",
  RssFeed: "rss/feed",
  SteamFriendsList: "steam/friends_list",
  SteamGameNews: "steam/game_news",
  SteamGamePopulation: "steam/game_population",
  TwitchFollowedChannels: "twitch/followed_channels",
  WeatherTimeDefault: "weather_time/default",
  YoutubeChannelStatistics: "youtube/channel_statistics",
  YoutubeSubsribedChannels: "youtube/subsribed_channels",
  YoutubeVideoStatistics: "youtube/video_statistics",
} as const;
export type WidgetType = typeof WidgetType[keyof typeof WidgetType];

/**
 *
 * @export
 * @interface YouTubeChannelStatisticsWidgetConfiguration
 */
export interface YouTubeChannelStatisticsWidgetConfiguration {
  /**
   * The type of widget, one of the values of WidgetType
   * @type {string}
   * @memberof YouTubeChannelStatisticsWidgetConfiguration
   */
  type: string;
  /**
   * Optional user-defined name for the widget
   * @type {string}
   * @memberof YouTubeChannelStatisticsWidgetConfiguration
   */
  name?: string;
  /**
   * Refresh rate of the widget, in seconds
   * @type {number}
   * @memberof YouTubeChannelStatisticsWidgetConfiguration
   */
  refreshRate?: number;
  /**
   *
   * @type {YouTubeChannelStatisticsWidgetConfigurationAllOfParameters}
   * @memberof YouTubeChannelStatisticsWidgetConfiguration
   */
  parameters: YouTubeChannelStatisticsWidgetConfigurationAllOfParameters;
}
/**
 * Configuration for the YouTube channel stats widget
 * @export
 * @interface YouTubeChannelStatisticsWidgetConfigurationAllOf
 */
export interface YouTubeChannelStatisticsWidgetConfigurationAllOf {
  /**
   *
   * @type {YouTubeChannelStatisticsWidgetConfigurationAllOfParameters}
   * @memberof YouTubeChannelStatisticsWidgetConfigurationAllOf
   */
  parameters: YouTubeChannelStatisticsWidgetConfigurationAllOfParameters;
}
/**
 *
 * @export
 * @interface YouTubeChannelStatisticsWidgetConfigurationAllOfParameters
 */
export interface YouTubeChannelStatisticsWidgetConfigurationAllOfParameters {
  /**
   *
   * @type {string}
   * @memberof YouTubeChannelStatisticsWidgetConfigurationAllOfParameters
   */
  channel: string;
}
/**
 *
 * @export
 * @interface YouTubeSubscribedChannelsWidgetConfiguration
 */
export interface YouTubeSubscribedChannelsWidgetConfiguration {
  /**
   * The type of widget, one of the values of WidgetType
   * @type {string}
   * @memberof YouTubeSubscribedChannelsWidgetConfiguration
   */
  type: string;
  /**
   * Optional user-defined name for the widget
   * @type {string}
   * @memberof YouTubeSubscribedChannelsWidgetConfiguration
   */
  name?: string;
  /**
   * Refresh rate of the widget, in seconds
   * @type {number}
   * @memberof YouTubeSubscribedChannelsWidgetConfiguration
   */
  refreshRate?: number;
  /**
   *
   * @type {TwitchFollowedChannelsWidgetConfigurationAllOfParameters}
   * @memberof YouTubeSubscribedChannelsWidgetConfiguration
   */
  parameters: TwitchFollowedChannelsWidgetConfigurationAllOfParameters;
}
/**
 * Configuration for the YouTube list of subscribed channels
 * @export
 * @interface YouTubeSubscribedChannelsWidgetConfigurationAllOf
 */
export interface YouTubeSubscribedChannelsWidgetConfigurationAllOf {
  /**
   *
   * @type {TwitchFollowedChannelsWidgetConfigurationAllOfParameters}
   * @memberof YouTubeSubscribedChannelsWidgetConfigurationAllOf
   */
  parameters: TwitchFollowedChannelsWidgetConfigurationAllOfParameters;
}
/**
 *
 * @export
 * @interface YouTubeVideoStatisticsWidgetConfiguration
 */
export interface YouTubeVideoStatisticsWidgetConfiguration {
  /**
   * The type of widget, one of the values of WidgetType
   * @type {string}
   * @memberof YouTubeVideoStatisticsWidgetConfiguration
   */
  type: string;
  /**
   * Optional user-defined name for the widget
   * @type {string}
   * @memberof YouTubeVideoStatisticsWidgetConfiguration
   */
  name?: string;
  /**
   * Refresh rate of the widget, in seconds
   * @type {number}
   * @memberof YouTubeVideoStatisticsWidgetConfiguration
   */
  refreshRate?: number;
  /**
   *
   * @type {YouTubeVideoStatisticsWidgetConfigurationAllOfParameters}
   * @memberof YouTubeVideoStatisticsWidgetConfiguration
   */
  parameters: YouTubeVideoStatisticsWidgetConfigurationAllOfParameters;
}
/**
 * Configuration for the YouTube video statistics
 * @export
 * @interface YouTubeVideoStatisticsWidgetConfigurationAllOf
 */
export interface YouTubeVideoStatisticsWidgetConfigurationAllOf {
  /**
   *
   * @type {YouTubeVideoStatisticsWidgetConfigurationAllOfParameters}
   * @memberof YouTubeVideoStatisticsWidgetConfigurationAllOf
   */
  parameters: YouTubeVideoStatisticsWidgetConfigurationAllOfParameters;
}
/**
 *
 * @export
 * @interface YouTubeVideoStatisticsWidgetConfigurationAllOfParameters
 */
export interface YouTubeVideoStatisticsWidgetConfigurationAllOfParameters {
  /**
   *
   * @type {string}
   * @memberof YouTubeVideoStatisticsWidgetConfigurationAllOfParameters
   */
  video: string;
}
