<script setup lang="ts">
import { Cookies } from "quasar";
import {
  BASE_PATH,
  Configuration,
  InformationApi,
  ServicesApi,
  WidgetsApi,
  WidgetType,
  type WidgetInstance,
} from "../api";

import EpicGamesFreeGames from "./EpicGames/FreeGames.vue";
import EpicGamesFriendsList from "./EpicGames/FriendsList.vue";
import SteamFriendsList from "./Steam/FriendsList.vue";
import SteamGameNews from "./Steam/GameNews.vue";
import SteamGamePopulation from "./Steam/GamePopulation.vue";
import RssFeed from "./Rss/RssFeed.vue";
import TwitchFollowedChannels from "./Twitch/FollowedChannels.vue";
import WeatherTime from "./WeatherTime.vue";
import YouTubeChannelStatistics from "./YouTube/ChannelStatistics.vue";
import YouTubeSubscribedChannels from "./YouTube/SubscribedChannels.vue";
import YouTubeVideoStatistics from "./YouTube/VideoStatistics.vue";
import type { DefineComponent } from "vue";
</script>

<template>
  <div class="columnWidget" v-for="column in maxColumn" :key="column">
    <div class="orderWidget">
      <div v-for="widget in widgets[0]" :key="widget.id">
        <div v-if="widget.config.columnPos == column">
          <component
            :is="getWidgetComponent(widget)"
            v-bind="{ widgetId: widget.id }"
          ></component>
        </div>
      </div>
    </div>
    <button class="Elipse" @click="open(column)">
      <div class="insideElipse">+</div>
    </button>
  </div>
  <div class="Empty">
    <button class="Elipse" @click="open(maxColumn + 1)">
      <div class="insideElipse">+</div>
    </button>
  </div>
</template>

<script lang="ts">
const servicesInit: any[] = [];
const widgetsInit: any[] = [];
export default {
  components: {
    EpicGamesFreeGames,
    EpicGamesFriendsList,
    SteamFriendsList,
    SteamGameNews,
    SteamGamePopulation,
    RssFeed,
    TwitchFollowedChannels,
    WeatherTime,
    YouTubeChannelStatistics,
    YouTubeSubscribedChannels,
    YouTubeVideoStatistics,
  },
  data() {
    return {
      services: servicesInit,
      widgets: widgetsInit,
      maxColumn: 0,
      maxPos: 0,
      showModalAddWidget: (this.$parent as any).$data.showModalAddWidget,
    };
  },
  methods: {
    open(column: number) {
      (this.$parent as any).$data.showModalAddWidget = true;
      (this.$parent as any).$data.column = column;
    },
    getWidgetComponent(widget: WidgetInstance): DefineComponent | null {
      const typeToWidget: Record<WidgetType, unknown> = {
        "epic_games/free_games": EpicGamesFreeGames,
        "epic_games/friends_list": EpicGamesFriendsList,
        "steam/friends_list": SteamFriendsList,
        "steam/game_news": SteamGameNews,
        "steam/game_population": SteamGamePopulation,
        "rss/feed": RssFeed,
        "twitch/followed_channels": TwitchFollowedChannels,
        "weather_time/default": WeatherTime,
        "youtube/channel_statistics": YouTubeChannelStatistics,
        "youtube/subscribed_channels": YouTubeSubscribedChannels,
        "youtube/video_statistics": YouTubeVideoStatistics,
      } as const;
      const type = widget.config?.type;
      return type ? (typeToWidget[type] as DefineComponent) : null;
    },
    async update() {
      const widgetsApi = new WidgetsApi();
      const widgets = await widgetsApi.listWidgets(
        {},
        { credentials: "include" }
      );

      this.widgets = [];
      this.widgets.push(widgets[0]);
    },
  },
  async created() {
    if (Cookies.get("token") != "OAuth2") {
      let widgetsApi = new WidgetsApi(
        new Configuration({
          accessToken: Cookies.get("token"),
        })
      );
      const widgets = await widgetsApi.listWidgets({}, {});
      this.widgets = [];
      this.widgets.push(widgets);
      widgets.forEach((widget: any) => {
        if (this.maxColumn < widget.config.columnPos) {
          this.maxColumn = widget.config.columnPos;
        }
      });
    } else {
      let widgetsApi = new WidgetsApi();
      const widgets = await widgetsApi.listWidgets(
        {},
        { credentials: "include" }
      );
      this.widgets = [];
      this.widgets.push(widgets);
      widgets.forEach((widget: any) => {
        if (this.maxColumn < widget.config.columnPos) {
          this.maxColumn = widget.config.columnPos;
        }
      });
    }
  },
};
</script>

<style>
@import "../assets/modalStyle.css";
.listWidget {
  display: flex;
  align-items: flex-start;
  flex-wrap: wrap;
}
.WidgetWeather {
  width: 450px;
  height: 200px;
  background: #393131;
  border-radius: 20px;
  margin: 10px;
  font-size: medium;
  display: flex;
  flex-direction: column;
}
.WidgetWeather > h6 {
  margin: 20px;
}
.WidgetWeather > span {
  margin: 20px;
  font-size: 28px;
  align-self: center;
}
.orderWidget {
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column;
}
.columnWidget {
  margin: 30px;
  border-radius: 20px;
  background: #d9d9d9;
  display: flex;
  flex-direction: column;
  align-items: center;
  position: relative;
  padding-bottom: 50px;
}
.Empty {
  width: 470px;
  height: 660px;
  border: 5px dashed #d9d9d9;
  margin: 30px;
  border-radius: 20px;
  background-color: transparent;
  display: flex;
  justify-content: center;
  align-items: center;
}
.Elipse {
  box-sizing: border-box;
  width: 77px;
  height: 77px;
  border-radius: 1000px;
  display: flex;
  justify-content: center;
  align-items: center;
  background: #d9d9d9;
  border: 1px solid #393131;
}
.columnWidget > .Elipse {
  width: 77px;
  height: 77px;
  position: absolute;
  top: 100%;
}
.insideElipse {
  position: relative;
  width: 22px;
  height: 39px;
  align-items: center;
  font-family: "Inter";
  font-style: normal;
  font-weight: 400;
  font-size: 32px;
  line-height: 39px;
  /* identical to box height */

  color: #393131;
}
</style>
