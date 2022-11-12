<script setup lang="ts">
import {
  BASE_PATH,
  Configuration,
  InformationApi,
  ServicesApi,
  WidgetsApi,
} from "../api";
</script>

<template>
  <div
    class="columnWidget"
    v-if="widgets.length != 0"
    v-for="column in maxColumn"
  >
    <div class="orderWidget">
      <div v-for="widget in widgets[0]">
        <div v-if="widget.config.columnPos == column">
          <div class="WidgetWeather">
            <h6>{{ widget.config.name }}</h6>
            <span>iWidget Info</span>
          </div>
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
  data() {
    return {
      services: servicesInit,
      widgets: widgetsInit,
      maxColumn: 0,
      maxPos: 0,
      showModalAddWidget: (this.$parent as any).$data.showModalAddWidget,
      user: {},
    };
  },
  methods: {
    open(Column: any) {
      (this.$parent as any).$data.showModalAddWidget = true;
      (this.$parent as any).$data.Column = Column;
      console.log((this.$parent as any).$data.Column);
    },
    async update() {
      const widgetsApi = new WidgetsApi();
      const widgets = await Promise.all([
        widgetsApi.listWidgets({}, { credentials: "include" }),
      ]);
      this.widgets = [];
      this.widgets.push(widgets[0]);
    },
  },
  async created() {
    const widgetsApi = new WidgetsApi();
    const widgets = await Promise.all([
      widgetsApi.listWidgets({}, { credentials: "include" }),
    ]);
    this.widgets = [];
    this.widgets.push(widgets[0]);
    widgets[0].forEach((widget) => {
      if (this.maxColumn < (widget.config as any).columnPos) {
        this.maxColumn = (widget.config as any).columnPos;
      }
    });
  },

  watch: {
    async showModalAddWidget() {
      console.log(this.showModalAddWidget);
      const widgetsApi = new WidgetsApi();
      const widgets = await Promise.all([
        widgetsApi.listWidgets({}, { credentials: "include" }),
      ]);
      this.widgets = [];
      this.widgets.push(widgets[0]);
      console.log(this.widgets);
    },
  },
};
</script>

<style>
@import "../assets/modalStyle.css";
.listWidget {
  display: flex;
  align-items: flex-start;
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
