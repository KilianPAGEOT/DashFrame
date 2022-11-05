<script lang="ts">
import MainPageVue from "./components/MainPage.vue";
import MainHeaderVue from "./components/MainHeader.vue";
import { InformationApi } from "./api";
export default {
  components: {
    MainPageVue,
    MainHeaderVue,
  },
};

// TEMP: API usage example:
const api = new InformationApi();

api.getAboutJson().then((about) => console.log("about.json", about));
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
