<script lang="ts">
import { init } from "@/events";
import type { WeatherWidget } from "@/api";
import { widgetsData } from "@/events";
export default {
  props: {
    widget: {},
  },
  data() {
    return {
      date: new Date(),
    };
  },
  computed: {
    Data(): WeatherWidget[] {
      if (this.widget.id == null) return [];
      const data = widgetsData.get(this.widgetId) as
        | WeatherWidget[]
        | null
        | undefined;
      return data ?? [];
    },
  },
};
</script>

<template>
  <div class="WidgetWeather">
    <h6>Weather</h6>
    <span class="span">Time : {{ date.getHours() }}</span>
    <span class="span"
      >City : {{ (widget as any).config.parameters.location }}</span
    >
    <span class="span">Temperature : {{ Data }}</span>
  </div>
</template>

<script lang="ts"></script>

<style>
.span {
  font-size: 18px;
  margin: 10px;
}
</style>
