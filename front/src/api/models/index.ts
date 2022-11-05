/* tslint:disable */
/* eslint-disable */
/**
 * Common properties shared by any server-sent event, not a full event by itself
 * @export
 * @interface BaseEvent
 */
export interface BaseEvent {
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
 * @interface CreateService400Response
 */
export interface CreateService400Response {
  /**
   *
   * @type {Error}
   * @memberof CreateService400Response
   */
  error?: Error;
}
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
  /**
   * The unique id of this service widget
   * @type {string}
   * @memberof EpicGamesFreeGamesWidgetConfiguration
   */
  type: EpicGamesFreeGamesWidgetConfigurationTypeEnum;
}

/**
 * @export
 */
export const EpicGamesFreeGamesWidgetConfigurationTypeEnum = {
  EpicGamesFreeGames: "epic_games/free_games",
} as const;
export type EpicGamesFreeGamesWidgetConfigurationTypeEnum =
  typeof EpicGamesFreeGamesWidgetConfigurationTypeEnum[keyof typeof EpicGamesFreeGamesWidgetConfigurationTypeEnum];

/**
 * Configuration for the Epic Games free games widget
 * @export
 * @interface EpicGamesFreeGamesWidgetConfigurationAllOf
 */
export interface EpicGamesFreeGamesWidgetConfigurationAllOf {
  /**
   * The unique id of this service widget
   * @type {string}
   * @memberof EpicGamesFreeGamesWidgetConfigurationAllOf
   */
  type: EpicGamesFreeGamesWidgetConfigurationAllOfTypeEnum;
}

/**
 * @export
 */
export const EpicGamesFreeGamesWidgetConfigurationAllOfTypeEnum = {
  EpicGamesFreeGames: "epic_games/free_games",
} as const;
export type EpicGamesFreeGamesWidgetConfigurationAllOfTypeEnum =
  typeof EpicGamesFreeGamesWidgetConfigurationAllOfTypeEnum[keyof typeof EpicGamesFreeGamesWidgetConfigurationAllOfTypeEnum];

/**
 *
 * @export
 * @interface EpicGamesFriendsListWidgetConfiguration
 */
export interface EpicGamesFriendsListWidgetConfiguration {
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
   * The unique id of this service widget
   * @type {string}
   * @memberof EpicGamesFriendsListWidgetConfiguration
   */
  type: EpicGamesFriendsListWidgetConfigurationTypeEnum;
  /**
   *
   * @type {EpicGamesFriendsListWidgetConfigurationAllOfParameters}
   * @memberof EpicGamesFriendsListWidgetConfiguration
   */
  parameters: EpicGamesFriendsListWidgetConfigurationAllOfParameters;
}

/**
 * @export
 */
export const EpicGamesFriendsListWidgetConfigurationTypeEnum = {
  EpicGamesFriendsList: "epic_games/friends_list",
} as const;
export type EpicGamesFriendsListWidgetConfigurationTypeEnum =
  typeof EpicGamesFriendsListWidgetConfigurationTypeEnum[keyof typeof EpicGamesFriendsListWidgetConfigurationTypeEnum];

/**
 * Configuration for the Epic Games friends list widget
 * @export
 * @interface EpicGamesFriendsListWidgetConfigurationAllOf
 */
export interface EpicGamesFriendsListWidgetConfigurationAllOf {
  /**
   * The unique id of this service widget
   * @type {string}
   * @memberof EpicGamesFriendsListWidgetConfigurationAllOf
   */
  type: EpicGamesFriendsListWidgetConfigurationAllOfTypeEnum;
  /**
   *
   * @type {EpicGamesFriendsListWidgetConfigurationAllOfParameters}
   * @memberof EpicGamesFriendsListWidgetConfigurationAllOf
   */
  parameters: EpicGamesFriendsListWidgetConfigurationAllOfParameters;
}

/**
 * @export
 */
export const EpicGamesFriendsListWidgetConfigurationAllOfTypeEnum = {
  EpicGamesFriendsList: "epic_games/friends_list",
} as const;
export type EpicGamesFriendsListWidgetConfigurationAllOfTypeEnum =
  typeof EpicGamesFriendsListWidgetConfigurationAllOfTypeEnum[keyof typeof EpicGamesFriendsListWidgetConfigurationAllOfTypeEnum];

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
 *
 * @export
 * @interface ListServices401Response
 */
export interface ListServices401Response {
  /**
   *
   * @type {Error}
   * @memberof ListServices401Response
   */
  error?: Error;
}
/**
 *
 * @export
 * @interface ListServices404Response
 */
export interface ListServices404Response {
  /**
   *
   * @type {Error}
   * @memberof ListServices404Response
   */
  error?: Error;
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
   * The unique id of this service widget
   * @type {string}
   * @memberof RssFeedWidgetConfiguration
   */
  type: RssFeedWidgetConfigurationTypeEnum;
  /**
   *
   * @type {RssFeedWidgetConfigurationAllOfParameters}
   * @memberof RssFeedWidgetConfiguration
   */
  parameters: RssFeedWidgetConfigurationAllOfParameters;
}

/**
 * @export
 */
export const RssFeedWidgetConfigurationTypeEnum = {
  RssFeed: "rss/feed",
} as const;
export type RssFeedWidgetConfigurationTypeEnum =
  typeof RssFeedWidgetConfigurationTypeEnum[keyof typeof RssFeedWidgetConfigurationTypeEnum];

/**
 * Configuration for the RSS feed widget
 * @export
 * @interface RssFeedWidgetConfigurationAllOf
 */
export interface RssFeedWidgetConfigurationAllOf {
  /**
   * The unique id of this service widget
   * @type {string}
   * @memberof RssFeedWidgetConfigurationAllOf
   */
  type: RssFeedWidgetConfigurationAllOfTypeEnum;
  /**
   *
   * @type {RssFeedWidgetConfigurationAllOfParameters}
   * @memberof RssFeedWidgetConfigurationAllOf
   */
  parameters: RssFeedWidgetConfigurationAllOfParameters;
}

/**
 * @export
 */
export const RssFeedWidgetConfigurationAllOfTypeEnum = {
  RssFeed: "rss/feed",
} as const;
export type RssFeedWidgetConfigurationAllOfTypeEnum =
  typeof RssFeedWidgetConfigurationAllOfTypeEnum[keyof typeof RssFeedWidgetConfigurationAllOfTypeEnum];

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
   * The unique id of this service widget
   * @type {string}
   * @memberof SteamFriendsListWidgetConfiguration
   */
  type: SteamFriendsListWidgetConfigurationTypeEnum;
  /**
   *
   * @type {EpicGamesFriendsListWidgetConfigurationAllOfParameters}
   * @memberof SteamFriendsListWidgetConfiguration
   */
  parameters: EpicGamesFriendsListWidgetConfigurationAllOfParameters;
}

/**
 * @export
 */
export const SteamFriendsListWidgetConfigurationTypeEnum = {
  SteamFriendsList: "steam/friends_list",
} as const;
export type SteamFriendsListWidgetConfigurationTypeEnum =
  typeof SteamFriendsListWidgetConfigurationTypeEnum[keyof typeof SteamFriendsListWidgetConfigurationTypeEnum];

/**
 * Configuration for the Steam friends list widget
 * @export
 * @interface SteamFriendsListWidgetConfigurationAllOf
 */
export interface SteamFriendsListWidgetConfigurationAllOf {
  /**
   * The unique id of this service widget
   * @type {string}
   * @memberof SteamFriendsListWidgetConfigurationAllOf
   */
  type: SteamFriendsListWidgetConfigurationAllOfTypeEnum;
  /**
   *
   * @type {EpicGamesFriendsListWidgetConfigurationAllOfParameters}
   * @memberof SteamFriendsListWidgetConfigurationAllOf
   */
  parameters: EpicGamesFriendsListWidgetConfigurationAllOfParameters;
}

/**
 * @export
 */
export const SteamFriendsListWidgetConfigurationAllOfTypeEnum = {
  SteamFriendsList: "steam/friends_list",
} as const;
export type SteamFriendsListWidgetConfigurationAllOfTypeEnum =
  typeof SteamFriendsListWidgetConfigurationAllOfTypeEnum[keyof typeof SteamFriendsListWidgetConfigurationAllOfTypeEnum];

/**
 *
 * @export
 * @interface SteamGameNewsWidgetConfiguration
 */
export interface SteamGameNewsWidgetConfiguration {
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
   * The unique id of this service widget
   * @type {string}
   * @memberof SteamGameNewsWidgetConfiguration
   */
  type: SteamGameNewsWidgetConfigurationTypeEnum;
  /**
   *
   * @type {SteamGameNewsWidgetConfigurationAllOfParameters}
   * @memberof SteamGameNewsWidgetConfiguration
   */
  parameters: SteamGameNewsWidgetConfigurationAllOfParameters;
}

/**
 * @export
 */
export const SteamGameNewsWidgetConfigurationTypeEnum = {
  SteamGameNews: "steam/game_news",
} as const;
export type SteamGameNewsWidgetConfigurationTypeEnum =
  typeof SteamGameNewsWidgetConfigurationTypeEnum[keyof typeof SteamGameNewsWidgetConfigurationTypeEnum];

/**
 * Configuration for the Steam game news widget
 * @export
 * @interface SteamGameNewsWidgetConfigurationAllOf
 */
export interface SteamGameNewsWidgetConfigurationAllOf {
  /**
   * The unique id of this service widget
   * @type {string}
   * @memberof SteamGameNewsWidgetConfigurationAllOf
   */
  type: SteamGameNewsWidgetConfigurationAllOfTypeEnum;
  /**
   *
   * @type {SteamGameNewsWidgetConfigurationAllOfParameters}
   * @memberof SteamGameNewsWidgetConfigurationAllOf
   */
  parameters: SteamGameNewsWidgetConfigurationAllOfParameters;
}

/**
 * @export
 */
export const SteamGameNewsWidgetConfigurationAllOfTypeEnum = {
  SteamGameNews: "steam/game_news",
} as const;
export type SteamGameNewsWidgetConfigurationAllOfTypeEnum =
  typeof SteamGameNewsWidgetConfigurationAllOfTypeEnum[keyof typeof SteamGameNewsWidgetConfigurationAllOfTypeEnum];

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
   * The unique id of this service widget
   * @type {string}
   * @memberof SteamGamePopulationWidgetConfiguration
   */
  type: SteamGamePopulationWidgetConfigurationTypeEnum;
  /**
   *
   * @type {SteamGameNewsWidgetConfigurationAllOfParameters}
   * @memberof SteamGamePopulationWidgetConfiguration
   */
  parameters: SteamGameNewsWidgetConfigurationAllOfParameters;
}

/**
 * @export
 */
export const SteamGamePopulationWidgetConfigurationTypeEnum = {
  SteamGamePopulation: "steam/game_population",
} as const;
export type SteamGamePopulationWidgetConfigurationTypeEnum =
  typeof SteamGamePopulationWidgetConfigurationTypeEnum[keyof typeof SteamGamePopulationWidgetConfigurationTypeEnum];

/**
 * Configuration for the Steam game population widget
 * @export
 * @interface SteamGamePopulationWidgetConfigurationAllOf
 */
export interface SteamGamePopulationWidgetConfigurationAllOf {
  /**
   * The unique id of this service widget
   * @type {string}
   * @memberof SteamGamePopulationWidgetConfigurationAllOf
   */
  type: SteamGamePopulationWidgetConfigurationAllOfTypeEnum;
  /**
   *
   * @type {SteamGameNewsWidgetConfigurationAllOfParameters}
   * @memberof SteamGamePopulationWidgetConfigurationAllOf
   */
  parameters: SteamGameNewsWidgetConfigurationAllOfParameters;
}

/**
 * @export
 */
export const SteamGamePopulationWidgetConfigurationAllOfTypeEnum = {
  SteamGamePopulation: "steam/game_population",
} as const;
export type SteamGamePopulationWidgetConfigurationAllOfTypeEnum =
  typeof SteamGamePopulationWidgetConfigurationAllOfTypeEnum[keyof typeof SteamGamePopulationWidgetConfigurationAllOfTypeEnum];

/**
 *
 * @export
 * @interface TwitchFollowedChannelsWidgetConfiguration
 */
export interface TwitchFollowedChannelsWidgetConfiguration {
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
   * The unique id of this service widget
   * @type {string}
   * @memberof TwitchFollowedChannelsWidgetConfiguration
   */
  type: TwitchFollowedChannelsWidgetConfigurationTypeEnum;
  /**
   *
   * @type {TwitchFollowedChannelsWidgetConfigurationAllOfParameters}
   * @memberof TwitchFollowedChannelsWidgetConfiguration
   */
  parameters: TwitchFollowedChannelsWidgetConfigurationAllOfParameters;
}

/**
 * @export
 */
export const TwitchFollowedChannelsWidgetConfigurationTypeEnum = {
  TwitchFollowedChannels: "twitch/followed_channels",
} as const;
export type TwitchFollowedChannelsWidgetConfigurationTypeEnum =
  typeof TwitchFollowedChannelsWidgetConfigurationTypeEnum[keyof typeof TwitchFollowedChannelsWidgetConfigurationTypeEnum];

/**
 * Configuration for the Twitch list of followed channels
 * @export
 * @interface TwitchFollowedChannelsWidgetConfigurationAllOf
 */
export interface TwitchFollowedChannelsWidgetConfigurationAllOf {
  /**
   * The unique id of this service widget
   * @type {string}
   * @memberof TwitchFollowedChannelsWidgetConfigurationAllOf
   */
  type: TwitchFollowedChannelsWidgetConfigurationAllOfTypeEnum;
  /**
   *
   * @type {TwitchFollowedChannelsWidgetConfigurationAllOfParameters}
   * @memberof TwitchFollowedChannelsWidgetConfigurationAllOf
   */
  parameters: TwitchFollowedChannelsWidgetConfigurationAllOfParameters;
}

/**
 * @export
 */
export const TwitchFollowedChannelsWidgetConfigurationAllOfTypeEnum = {
  TwitchFollowedChannels: "twitch/followed_channels",
} as const;
export type TwitchFollowedChannelsWidgetConfigurationAllOfTypeEnum =
  typeof TwitchFollowedChannelsWidgetConfigurationAllOfTypeEnum[keyof typeof TwitchFollowedChannelsWidgetConfigurationAllOfTypeEnum];

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
  /**
   * The unique id of this service widget
   * @type {string}
   * @memberof UnknownWidgetConfiguration
   */
  type: string;
  /**
   *
   * @type {object}
   * @memberof UnknownWidgetConfiguration
   */
  parameters: object;
}
/**
 *
 * @export
 * @interface UnknownWidgetConfigurationAllOf
 */
export interface UnknownWidgetConfigurationAllOf {
  /**
   * The unique id of this service widget
   * @type {string}
   * @memberof UnknownWidgetConfigurationAllOf
   */
  type: string;
  /**
   *
   * @type {object}
   * @memberof UnknownWidgetConfigurationAllOf
   */
  parameters: object;
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
 *
 * @export
 * @interface WeatherTimeWidgetConfiguration
 */
export interface WeatherTimeWidgetConfiguration {
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
   * The unique id of this service widget
   * @type {string}
   * @memberof WeatherTimeWidgetConfiguration
   */
  type: WeatherTimeWidgetConfigurationTypeEnum;
  /**
   *
   * @type {WeatherTimeWidgetConfigurationAllOfParameters}
   * @memberof WeatherTimeWidgetConfiguration
   */
  parameters: WeatherTimeWidgetConfigurationAllOfParameters;
}

/**
 * @export
 */
export const WeatherTimeWidgetConfigurationTypeEnum = {
  WeatherTimeDefault: "weather_time/default",
} as const;
export type WeatherTimeWidgetConfigurationTypeEnum =
  typeof WeatherTimeWidgetConfigurationTypeEnum[keyof typeof WeatherTimeWidgetConfigurationTypeEnum];

/**
 * Configuration for the weather & time widget
 * @export
 * @interface WeatherTimeWidgetConfigurationAllOf
 */
export interface WeatherTimeWidgetConfigurationAllOf {
  /**
   * The unique id of this service widget
   * @type {string}
   * @memberof WeatherTimeWidgetConfigurationAllOf
   */
  type: WeatherTimeWidgetConfigurationAllOfTypeEnum;
  /**
   *
   * @type {WeatherTimeWidgetConfigurationAllOfParameters}
   * @memberof WeatherTimeWidgetConfigurationAllOf
   */
  parameters: WeatherTimeWidgetConfigurationAllOfParameters;
}

/**
 * @export
 */
export const WeatherTimeWidgetConfigurationAllOfTypeEnum = {
  WeatherTimeDefault: "weather_time/default",
} as const;
export type WeatherTimeWidgetConfigurationAllOfTypeEnum =
  typeof WeatherTimeWidgetConfigurationAllOfTypeEnum[keyof typeof WeatherTimeWidgetConfigurationAllOfTypeEnum];

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
  | ({ type: "steam/friends_list" } & SteamFriendsListWidgetConfiguration)
  | ({ type: "steam/game_news" } & SteamGameNewsWidgetConfiguration)
  | ({
      type: "steam/game_population";
    } & SteamGamePopulationWidgetConfiguration);
/**
 *
 * @export
 * @interface WidgetDeletionEvent
 */
export interface WidgetDeletionEvent {
  /**
   * The UNIX timestamp of the event creation
   * @type {number}
   * @memberof WidgetDeletionEvent
   */
  timestamp: number;
  /**
   * The unique ID of this event
   * @type {string}
   * @memberof WidgetDeletionEvent
   */
  type: WidgetDeletionEventTypeEnum;
  /**
   *
   * @type {WidgetDeletionEventAllOfData}
   * @memberof WidgetDeletionEvent
   */
  data: WidgetDeletionEventAllOfData;
}

/**
 * @export
 */
export const WidgetDeletionEventTypeEnum = {
  WidgetsDeletion: "widgets/deletion",
} as const;
export type WidgetDeletionEventTypeEnum =
  typeof WidgetDeletionEventTypeEnum[keyof typeof WidgetDeletionEventTypeEnum];

/**
 * Forces a widget to be delete if it still exists on the client side
 * @export
 * @interface WidgetDeletionEventAllOf
 */
export interface WidgetDeletionEventAllOf {
  /**
   * The unique ID of this event
   * @type {string}
   * @memberof WidgetDeletionEventAllOf
   */
  type: WidgetDeletionEventAllOfTypeEnum;
  /**
   *
   * @type {WidgetDeletionEventAllOfData}
   * @memberof WidgetDeletionEventAllOf
   */
  data: WidgetDeletionEventAllOfData;
}

/**
 * @export
 */
export const WidgetDeletionEventAllOfTypeEnum = {
  WidgetsDeletion: "widgets/deletion",
} as const;
export type WidgetDeletionEventAllOfTypeEnum =
  typeof WidgetDeletionEventAllOfTypeEnum[keyof typeof WidgetDeletionEventAllOfTypeEnum];

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
   * The UNIX timestamp of the event creation
   * @type {number}
   * @memberof WidgetErrorEvent
   */
  timestamp: number;
  /**
   * The unique ID of this event
   * @type {string}
   * @memberof WidgetErrorEvent
   */
  type: WidgetErrorEventTypeEnum;
  /**
   *
   * @type {WidgetErrorEventAllOfData}
   * @memberof WidgetErrorEvent
   */
  data: WidgetErrorEventAllOfData;
}

/**
 * @export
 */
export const WidgetErrorEventTypeEnum = {
  WidgetsError: "widgets/error",
} as const;
export type WidgetErrorEventTypeEnum =
  typeof WidgetErrorEventTypeEnum[keyof typeof WidgetErrorEventTypeEnum];

/**
 * Widget error event
 * @export
 * @interface WidgetErrorEventAllOf
 */
export interface WidgetErrorEventAllOf {
  /**
   * The unique ID of this event
   * @type {string}
   * @memberof WidgetErrorEventAllOf
   */
  type: WidgetErrorEventAllOfTypeEnum;
  /**
   *
   * @type {WidgetErrorEventAllOfData}
   * @memberof WidgetErrorEventAllOf
   */
  data: WidgetErrorEventAllOfData;
}

/**
 * @export
 */
export const WidgetErrorEventAllOfTypeEnum = {
  WidgetsError: "widgets/error",
} as const;
export type WidgetErrorEventAllOfTypeEnum =
  typeof WidgetErrorEventAllOfTypeEnum[keyof typeof WidgetErrorEventAllOfTypeEnum];

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
   * The UNIX timestamp of the event creation
   * @type {number}
   * @memberof WidgetRefreshEvent
   */
  timestamp: number;
  /**
   * The unique ID of this event
   * @type {string}
   * @memberof WidgetRefreshEvent
   */
  type: WidgetRefreshEventTypeEnum;
  /**
   *
   * @type {WidgetRefreshEventAllOfData}
   * @memberof WidgetRefreshEvent
   */
  data: WidgetRefreshEventAllOfData;
}

/**
 * @export
 */
export const WidgetRefreshEventTypeEnum = {
  WidgetsRefresh: "widgets/refresh",
} as const;
export type WidgetRefreshEventTypeEnum =
  typeof WidgetRefreshEventTypeEnum[keyof typeof WidgetRefreshEventTypeEnum];

/**
 * Widget data update event
 * @export
 * @interface WidgetRefreshEventAllOf
 */
export interface WidgetRefreshEventAllOf {
  /**
   * The unique ID of this event
   * @type {string}
   * @memberof WidgetRefreshEventAllOf
   */
  type: WidgetRefreshEventAllOfTypeEnum;
  /**
   *
   * @type {WidgetRefreshEventAllOfData}
   * @memberof WidgetRefreshEventAllOf
   */
  data: WidgetRefreshEventAllOfData;
}

/**
 * @export
 */
export const WidgetRefreshEventAllOfTypeEnum = {
  WidgetsRefresh: "widgets/refresh",
} as const;
export type WidgetRefreshEventAllOfTypeEnum =
  typeof WidgetRefreshEventAllOfTypeEnum[keyof typeof WidgetRefreshEventAllOfTypeEnum];

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
  FriendsList: "steam/friends_list",
  GameNews: "steam/game_news",
  GamePopulation: "steam/game_population",
} as const;
export type WidgetType = typeof WidgetType[keyof typeof WidgetType];

/**
 *
 * @export
 * @interface YouTubeChannelStatisticsWidgetConfiguration
 */
export interface YouTubeChannelStatisticsWidgetConfiguration {
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
   * The unique id of this service widget
   * @type {string}
   * @memberof YouTubeChannelStatisticsWidgetConfiguration
   */
  type: YouTubeChannelStatisticsWidgetConfigurationTypeEnum;
  /**
   *
   * @type {YouTubeChannelStatisticsWidgetConfigurationAllOfParameters}
   * @memberof YouTubeChannelStatisticsWidgetConfiguration
   */
  parameters: YouTubeChannelStatisticsWidgetConfigurationAllOfParameters;
}

/**
 * @export
 */
export const YouTubeChannelStatisticsWidgetConfigurationTypeEnum = {
  YoutubeChannelStatistics: "youtube/channel_statistics",
} as const;
export type YouTubeChannelStatisticsWidgetConfigurationTypeEnum =
  typeof YouTubeChannelStatisticsWidgetConfigurationTypeEnum[keyof typeof YouTubeChannelStatisticsWidgetConfigurationTypeEnum];

/**
 * Configuration for the YouTube channel stats widget
 * @export
 * @interface YouTubeChannelStatisticsWidgetConfigurationAllOf
 */
export interface YouTubeChannelStatisticsWidgetConfigurationAllOf {
  /**
   * The unique id of this service widget
   * @type {string}
   * @memberof YouTubeChannelStatisticsWidgetConfigurationAllOf
   */
  type: YouTubeChannelStatisticsWidgetConfigurationAllOfTypeEnum;
  /**
   *
   * @type {YouTubeChannelStatisticsWidgetConfigurationAllOfParameters}
   * @memberof YouTubeChannelStatisticsWidgetConfigurationAllOf
   */
  parameters: YouTubeChannelStatisticsWidgetConfigurationAllOfParameters;
}

/**
 * @export
 */
export const YouTubeChannelStatisticsWidgetConfigurationAllOfTypeEnum = {
  YoutubeChannelStatistics: "youtube/channel_statistics",
} as const;
export type YouTubeChannelStatisticsWidgetConfigurationAllOfTypeEnum =
  typeof YouTubeChannelStatisticsWidgetConfigurationAllOfTypeEnum[keyof typeof YouTubeChannelStatisticsWidgetConfigurationAllOfTypeEnum];

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
   * The unique id of this service widget
   * @type {string}
   * @memberof YouTubeSubscribedChannelsWidgetConfiguration
   */
  type: YouTubeSubscribedChannelsWidgetConfigurationTypeEnum;
  /**
   *
   * @type {TwitchFollowedChannelsWidgetConfigurationAllOfParameters}
   * @memberof YouTubeSubscribedChannelsWidgetConfiguration
   */
  parameters: TwitchFollowedChannelsWidgetConfigurationAllOfParameters;
}

/**
 * @export
 */
export const YouTubeSubscribedChannelsWidgetConfigurationTypeEnum = {
  YoutubeSubsribedChannels: "youtube/subsribed_channels",
} as const;
export type YouTubeSubscribedChannelsWidgetConfigurationTypeEnum =
  typeof YouTubeSubscribedChannelsWidgetConfigurationTypeEnum[keyof typeof YouTubeSubscribedChannelsWidgetConfigurationTypeEnum];

/**
 * Configuration for the YouTube list of subscribed channels
 * @export
 * @interface YouTubeSubscribedChannelsWidgetConfigurationAllOf
 */
export interface YouTubeSubscribedChannelsWidgetConfigurationAllOf {
  /**
   * The unique id of this service widget
   * @type {string}
   * @memberof YouTubeSubscribedChannelsWidgetConfigurationAllOf
   */
  type: YouTubeSubscribedChannelsWidgetConfigurationAllOfTypeEnum;
  /**
   *
   * @type {TwitchFollowedChannelsWidgetConfigurationAllOfParameters}
   * @memberof YouTubeSubscribedChannelsWidgetConfigurationAllOf
   */
  parameters: TwitchFollowedChannelsWidgetConfigurationAllOfParameters;
}

/**
 * @export
 */
export const YouTubeSubscribedChannelsWidgetConfigurationAllOfTypeEnum = {
  YoutubeSubsribedChannels: "youtube/subsribed_channels",
} as const;
export type YouTubeSubscribedChannelsWidgetConfigurationAllOfTypeEnum =
  typeof YouTubeSubscribedChannelsWidgetConfigurationAllOfTypeEnum[keyof typeof YouTubeSubscribedChannelsWidgetConfigurationAllOfTypeEnum];

/**
 *
 * @export
 * @interface YouTubeVideoStatisticsWidgetConfiguration
 */
export interface YouTubeVideoStatisticsWidgetConfiguration {
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
   * The unique id of this service widget
   * @type {string}
   * @memberof YouTubeVideoStatisticsWidgetConfiguration
   */
  type: YouTubeVideoStatisticsWidgetConfigurationTypeEnum;
  /**
   *
   * @type {YouTubeVideoStatisticsWidgetConfigurationAllOfParameters}
   * @memberof YouTubeVideoStatisticsWidgetConfiguration
   */
  parameters: YouTubeVideoStatisticsWidgetConfigurationAllOfParameters;
}

/**
 * @export
 */
export const YouTubeVideoStatisticsWidgetConfigurationTypeEnum = {
  YoutubeVideoStatistics: "youtube/video_statistics",
} as const;
export type YouTubeVideoStatisticsWidgetConfigurationTypeEnum =
  typeof YouTubeVideoStatisticsWidgetConfigurationTypeEnum[keyof typeof YouTubeVideoStatisticsWidgetConfigurationTypeEnum];

/**
 * Configuration for the YouTube video statistics
 * @export
 * @interface YouTubeVideoStatisticsWidgetConfigurationAllOf
 */
export interface YouTubeVideoStatisticsWidgetConfigurationAllOf {
  /**
   * The unique id of this service widget
   * @type {string}
   * @memberof YouTubeVideoStatisticsWidgetConfigurationAllOf
   */
  type: YouTubeVideoStatisticsWidgetConfigurationAllOfTypeEnum;
  /**
   *
   * @type {YouTubeVideoStatisticsWidgetConfigurationAllOfParameters}
   * @memberof YouTubeVideoStatisticsWidgetConfigurationAllOf
   */
  parameters: YouTubeVideoStatisticsWidgetConfigurationAllOfParameters;
}

/**
 * @export
 */
export const YouTubeVideoStatisticsWidgetConfigurationAllOfTypeEnum = {
  YoutubeVideoStatistics: "youtube/video_statistics",
} as const;
export type YouTubeVideoStatisticsWidgetConfigurationAllOfTypeEnum =
  typeof YouTubeVideoStatisticsWidgetConfigurationAllOfTypeEnum[keyof typeof YouTubeVideoStatisticsWidgetConfigurationAllOfTypeEnum];

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
