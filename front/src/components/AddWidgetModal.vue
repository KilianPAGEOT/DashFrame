<script setup lang="ts">
import iconSet from "quasar/icon-set/ionicons-v4";
import "@quasar/extras/ionicons-v4/ionicons-v4.css";
</script>

<template>
  <div>
    <transition name="modal" :showModalSign="false">
      <div class="modal-mask">
        <div class="modal-wrapper">
          <div class="modal-back">
            <div class="listService" style="overflow-y: scroll">
              <h4 style="margin: 10px">List of Widgets</h4>
              <q-list bordered v-for="service in services">
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
                      <q-list v-for="widget in service.widgets">
                        <q-item
                          style="
                            border: 2px solid rgb(36 36 36);
                            border-radius: 10px;
                            margin: 2px;
                            z-index: 10;
                          "
                          clickable
                          v-ripple
                          @click.stop="showInfo(widget.id, service.widgets)"
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
          widgets: [
            { id: 1, name: "Friend List", display: false },
            { id: 2, name: "Game News", display: false },
            { id: 3, name: "Game Population", display: false },
          ],
        },
        {
          id: 2,
          name: "Epic Games",
          displayWidget: false,
          widgets: [
            { id: 1, name: "Friend List", display: false },
            { id: 2, name: "Free Game", display: false },
          ],
        },
        {
          id: 3,
          name: "Youtube",
          displayWidget: false,
          widgets: [
            { id: 1, name: "List of Followed channels", display: false },
            { id: 2, name: "Stats for your channel", display: false },
            { id: 3, name: "Specific video stats", display: false },
          ],
        },
        {
          id: 4,
          name: "Weather and Time",
          displayWidget: false,
          widgets: [{ id: 1, name: "Wheater Widget", display: false }],
        },
        {
          id: 5,
          name: "Twitch",
          displayWidget: false,
          widgets: [
            { id: 1, name: "List of Followed channels", display: false },
          ],
        },
        {
          id: 6,
          name: "RSS",
          displayWidget: false,
          widgets: [{ id: 1, name: "RSS Feed", display: false }],
        },
      ],
    };
  },
  methods: {
    close() {
      (
        this.$parent || { $data: { showModalAddWidget: true } }
      ).$data.showModalAddWidget = false;
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
    showInfo(id: number, widgets: any[]) {
      console.log(widgets[id - 1].name);
      widgets.forEach((widget: { display: boolean }) => {
        widget.display = false;
      });
      widgets[id - 1].display = true;
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
  width: 75vw;
  height: 50vh;
  margin: 50px;
  display: flex;
  position: relative;
  display: flex;
  position: relative;
  align-items: flex-start;
  justify-content: flex-end;
}
.listService {
  position: absolute;
  left: 0.83%;
  right: 66.69%;
  top: 1.51%;
  bottom: 1.91%;
  background: #4d4d4d;
  border-radius: 20px;
}
.btnClose {
  position: absolute;
  width: 33.62px;
  height: 33.62px;
}
</style>
