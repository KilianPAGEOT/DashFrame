<template>
  <p>Steam Friends List</p>
  <ul v-for="friend in friendsList" :key="friend.name">
    <li>{{ friend.name }} loll</li>
  </ul>
</template>

<script lang="ts">
import type { SteamUser } from "@/api";
import { widgetsData, widgetsUpdater } from "@/events";

export default {
  props: {
    widgetId: Number,
  },
  computed: {
    friendsList(): SteamUser[] {
      if (this.widgetId == null) return [];
      const data = widgetsData.get(this.widgetId) as
        | SteamUser[]
        | null
        | undefined;
      return data ?? [];
    },
  },
  created() {
    if (this.widgetId) {
      widgetsUpdater.set(this.widgetId, () => {
        this.$forceUpdate();
      });
    }
  },
};
</script>

<style></style>
