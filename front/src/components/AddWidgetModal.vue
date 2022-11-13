<script setup lang="ts">
import iconSet from "quasar/icon-set/ionicons-v4";
import "@quasar/extras/ionicons-v4/ionicons-v4.css";
import {
  BASE_PATH,
  Configuration,
  InformationApi,
  ServicesApi,
  WidgetsApi,
} from "../api";
import { Cookies, QList, QItem, QItemSection, QInput, QBtn } from "quasar";
</script>

<template>
  <div>
    <transition name="modal" :showModalSign="false">
      <div class="modal-mask">
        <div class="modal-wrapper">
          <div class="modal-back">
            <div class="listService">
              <h4 style="margin: 10px">List of Widgets</h4>
              <q-list
                bordered
                v-for="service in services"
                v-bind:key="service.id"
              >
                <q-item
                  style="z-index: 1"
                  clickable
                  v-ripple
                  @click="showWidget(service.id, services)"
                >
                  <q-item-section
                    ><h6 style="margin: 4px">
                      {{ service.name }}
                    </h6>
                    <div v-if="service.displayWidget">
                      <q-list
                        v-for="widget in service.widgets"
                        v-bind:key="widget.id"
                      >
                        <q-item
                          style="
                            border: 2px solid rgb(36 36 36);
                            border-radius: 10px;
                            margin: 2px;
                            z-index: 10;
                          "
                          clickable
                          v-ripple
                          @click.stop="
                            showInfo(widget.id, services, service.widgets)
                          "
                        >
                          <q-item-section
                            ><h8 style="margin: 4px">
                              {{ widget.name }}
                            </h8>
                          </q-item-section>
                          <q-item-section style="text-align: right"
                            >-></q-item-section
                          >
                        </q-item>
                      </q-list>
                    </div>
                  </q-item-section>
                </q-item>
              </q-list>
            </div>
            <div
              style="
                background: #d9d9d9;
                border-radius: 20px;
                margin: 12px;
                width: 65%;
                height: 95%;
                flex-grow: 6;
                overflow-y: scroll;
              "
            >
              <div v-for="service in services" v-bind:key="service.id">
                <div v-for="widget in service.widgets" v-bind:key="widget.id">
                  <div
                    v-if="widget.display"
                    style="display: flex; flex-direction: column"
                  >
                    <div
                      style="
                        display: flex;
                        align-items: flex-start;
                        justify-content: space-between;
                        margin: 20px;
                      "
                    >
                      <h3 style="margin: 10px">{{ widget.name }}</h3>
                      <div
                        v-if="service.name == 'Steam'"
                        style="margin-top: 15px"
                      >
                        <label for="inputparam">User ID :</label>
                        <q-input
                          style="margin-top: 10px"
                          filled
                          v-model="service.token"
                        />
                      </div>
                    </div>
                    <div style="margin: 20px">
                      <h6 style="margin: 0px; margin-bottom: 10px">
                        Widget Description :
                      </h6>
                      {{ widget.description }}
                    </div>
                    <div style="width: 40%; margin: 20px">
                      <label for="inputparam">refreshRate :</label>
                      <q-input
                        style="margin-top: 10px"
                        filled
                        v-model="widget.refreshRate"
                      />
                    </div>
                    <div
                      v-for="param in widget.parameters"
                      v-bind:key="param.name"
                      style="width: 40%; margin: 20px"
                    >
                      <label for="inputparam"
                        >{{ (param as any).name }} :</label
                      >
                      <q-input
                        style="margin-top: 10px"
                        filled
                        v-model="(param as any)[(param as any).name]"
                      />
                    </div>
                    <div style="align-self: end; margin: 25px">
                      <q-btn
                        class="btnSubmit"
                        style="background: rgb(89 89 89); color: white"
                        label="Add Widget"
                        @click="CreateWidget(widget, service)"
                      />
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <q-btn
              class="btnClose"
              round
              icon="ion-close-circle-outline"
              @click="close"
            />
          </div>
        </div>
      </div>
    </transition>
  </div>
</template>

<script lang="ts">
export default {
  data() {
    return {
      services: [
        {
          id: 1,
          name: "Steam",
          displayWidget: false,
          token: "token",
          type: "steam",
          widgets: [
            {
              id: 1,
              name: "Friend List",
              display: false,
              description: "Widget to display your friend list",
              type: "steam/friends_list",
              refreshRate: 10,
              parameters: [{ name: "showOffline", showOffline: true }],
            },
            {
              id: 2,
              name: "Game News",
              display: false,
              description: "Widget to news on a game",
              type: "steam/game_news",
              refreshRate: 10,
              parameters: [
                { name: "gameNameOrId", gameNameOrId: "You game ID" },
              ],
            },
            {
              id: 3,
              name: "Game Population",
              display: false,
              description:
                "Widget to display population in a game of your choice",
              type: "steam/game_population",
              refreshRate: 10,
              parameters: [
                { name: "gameNameOrId", gameNameOrId: "You game ID" },
              ],
            },
          ],
        },
        {
          id: 2,
          name: "Epic Games",
          displayWidget: false,
          token: "token",
          type: "epic_games",
          widgets: [
            {
              id: 1,
              name: "Friend List",
              display: false,
              description: "Widget to display your friend list",
              type: "epic_games/friends_list",
              refreshRate: 10,
              parameters: [{ name: "showOffline", showOffline: true }],
            },
            {
              id: 2,
              name: "Free Game",
              display: false,
              type: "epic_games/free_games",
              description: "Widget to display free games on Epic Games",
              refreshRate: 10,
            },
          ],
        },
        {
          id: 3,
          name: "Youtube",
          displayWidget: false,
          token: "token",
          type: "youtube",
          widgets: [
            {
              id: 1,
              name: "List of Followed channels",
              display: false,
              description:
                "Widget to see if your subscriptions are live on Youtube",
              type: "youtube/subscribed_channels",
              refreshRate: 10,
              parameters: [{ name: "filter", filter: "Choose Channel" }],
            },
            {
              id: 2,
              name: "Stats for your channel",
              display: false,
              description:
                "Widget to display some statistics on your Youtube channel",
              type: "youtube/channel_statistics",
              refreshRate: 10,
              parameters: [{ name: "channel", channel: "Choose Channel" }],
            },
            {
              id: 3,
              name: "Specific video stats",
              display: false,
              description:
                "Widget to display some statistics on specific Video",
              type: "youtube/video_statistics",
              refreshRate: 10,
              parameters: [{ name: "video", video: "Choose video" }],
            },
          ],
        },
        {
          id: 4,
          name: "Weather and Time",
          displayWidget: false,
          token: "token",
          type: "weather_time",
          widgets: [
            {
              id: 1,
              name: "Weather Widget",
              display: false,
              description: "Widget to display Weather in you city",
              type: "weather_time/default",
              refreshRate: 10,
              parameters: [{ name: "location", location: "Choose location" }],
            },
          ],
        },
        {
          id: 5,
          name: "Twitch",
          displayWidget: false,
          token: "token",
          type: "twitch",
          widgets: [
            {
              id: 1,
              name: "List of Followed channels",
              display: false,
              description:
                "Widget to see if your subscriptions are live on Twitch",
              type: "twitch/followed_channels",
              refreshRate: 10,
              parameters: [{ name: "filter", filter: "Choose channel" }],
            },
          ],
        },
        {
          id: 6,
          name: "RSS",
          displayWidget: false,
          token: "token",
          type: "rss",
          widgets: [
            {
              id: 1,
              name: "RSS Feed",
              display: false,
              description: "Widget to display RSS Feed of your choice",
              type: "rss/feed",
              refreshRate: 10,
              parameters: [{ name: "url", url: "Choose url" }],
            },
          ],
        },
      ],
    };
  },
  methods: {
    close() {
      (this.$parent as any).$data.showModalAddWidget = false;
    },
    showWidget(id: number, services: any[]) {
      if (services[id - 1].displayWidget) {
        services[id - 1].displayWidget = false;
      } else {
        services.forEach((service) => {
          service.displayWidget = false;
        });

        services[id - 1].displayWidget = true;
      }
    },
    showInfo(id: number, services: any[], widgets: any[]) {
      services.forEach((service: { widgets: { display: boolean }[] }) => {
        service.widgets.forEach((widget: { display: boolean }) => {
          widget.display = false;
        });
      });
      widgets[id - 1].display = true;
    },
    async CreateWidget(widget: any, service: any) {
      if (Cookies.get("token") != "OAuth2") {
        const servicesApi = new ServicesApi(
          new Configuration({
            accessToken: Cookies.get("token"),
          })
        );
        const services = await servicesApi.createService(
          {
            createServiceRequest: {
              type: service.type,
              username: "gg",
              token: service.token,
            },
          },
          {}
        );
        const widgetApi = new WidgetsApi(
          new Configuration({
            accessToken: Cookies.get("token"),
          })
        );

        let nameparm = undefined;
        let widgets = undefined;
        if (widget.type != "epic_games/free_games") {
          nameparm = widget.parameters[0].name;
          widgets = await widgetApi.createWidget(
            {
              createWidgetRequest: {
                serviceId: services.id,
                config: {
                  name: widget.name,
                  refreshRate: widget.refreshRate,
                  type: widget.type,
                  columnPos: (this.$parent as any).$data.column,
                  position: 0,
                  parameters: widget.parameters[0],
                },
              },
            },
            {}
          );
        } else {
          widgets = await widgetApi.createWidget(
            {
              createWidgetRequest: {
                serviceId: services.id,
                config: {
                  name: widget.name,
                  refreshRate: widget.refreshRate,
                  type: "epic_games/free_games",
                  columnPos: (this.$parent as any).$data.column,
                  position: 1,
                },
              },
            },
            { credentials: "include" }
          );
        }
      } else {
        const servicesApi = new ServicesApi();
        const services = await servicesApi.createService(
          {
            createServiceRequest: {
              type: service.type,
              username: "gg",
              token: service.token,
            },
          },
          { credentials: "include" }
        );
        const widgetsApi = new WidgetsApi();

        let nameparm = undefined;
        let widgets = undefined;
        if (widget.type != "epic_games/free_games") {
          nameparm = widget.parameters[0].name;
          widgets = await widgetsApi.createWidget(
            {
              createWidgetRequest: {
                serviceId: services.id,
                config: {
                  name: widget.name,
                  refreshRate: widget.refreshRate,
                  type: widget.type,
                  columnPos: (this.$parent as any).$data.column,
                  position: 0,
                  parameters: widget.parameters[0],
                },
              },
            },
            { credentials: "include" }
          );
        } else {
          widgets = await widgetsApi.createWidget(
            {
              createWidgetRequest: {
                serviceId: services.id,
                config: {
                  name: widget.name,
                  refreshRate: widget.refreshRate,
                  type: "epic_games/free_games",
                  columnPos: (this.$parent as any).$data.column,
                  position: 1,
                },
              },
            },
            { credentials: "include" }
          );
        }
        window.location.reload();
      }
    },
  },
};
</script>
<style>
@import "../assets/modalStyle.css";
.modal-wrapper {
  display: flex;
}
.modal-back {
  background: #878787;
  backdrop-filter: blur(10px);
  border-radius: 25px;
  width: 65vw;
  height: 50vh;
  margin: 50px;
  display: flex;
  position: relative;
  align-items: flex-start;
  justify-content: flex-end;
}
.listService {
  background: #4d4d4d;
  flex-grow: 2;
  border-radius: 20px;
  margin: 12px;
  overflow-y: scroll;
  max-height: 95%;
}
.btnClose {
  position: absolute;
  width: 33.62px;
  height: 33.62px;
  margin: 5px;
}
</style>
