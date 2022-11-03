<script lang="ts">
import MainPageVue from "./components/MainPage.vue";
import MainHeaderVue from "./components/MainHeader.vue";
export default {
  components: {
    MainPageVue,
    MainHeaderVue,
  },
};
</script>
<template>
  <body>
    <MainHeaderVue msg="DashFrame" />
    <MainPageVue />
  </body>
</template>

<style>
html {
  background-color: black;
}
</style>