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
            <div class="listService">
              <h4 style="margin: 10px">List of Widgets</h4>
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
  methods: {
    close() {
      (
        this.$parent || { $data: { showModalAddWidget: true } }
      ).$data.showModalAddWidget = false;
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
