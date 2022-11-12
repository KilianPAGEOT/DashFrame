<script setup lang="ts">
import "../assets/modalStyle.css";
import AddWidgetModalVue from "./AddWidgetModal.vue";
import WidgetsVue from "./Widgets.vue";
</script>

<template>
  <body class="listWidget">
    <div v-if="showModalAddWidget">
      <AddWidgetModalVue showModalAddWidget />
    </div>
    <WidgetsVue />
  </body>
</template>

<script lang="ts">
export default {
  data() {
    return {
      showModalAddWidget: false,
    };
  },
  mounted() {
    console.log(this.$watch);
    console.log(this.$props);

    console.log(this.$emit);
  },
};
</script>
