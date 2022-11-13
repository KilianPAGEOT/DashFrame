<script lang="ts">
import MainPageVue from "./components/MainPage.vue";
import MainHeaderVue from "./components/MainHeader.vue";
import LoginPageVue from "./components/LoginPage.vue";
import { BASE_PATH, Configuration, InformationApi, ServicesApi } from "./api";
import * as events from "./events";

function isConnected() {
  const parts: any = `; ${document.cookie}`.split(`; ${"token"}=`);
  if (parts.length === 2 && parts.pop().split(";").shift()) return true;
  return false;
}

export default {
  components: {
    MainPageVue,
    MainHeaderVue,
    LoginPageVue,
  },
  data() {
    return {
      login: isConnected(),
    };
  },
};
try {
  events.init();
} catch (error) {
  console.log(`epic failure: ${error}`);
}
</script>
<template>
  <body>
    <MainHeaderVue msg="DashFrame" v-bind:is-connected="login" />
    <MainPageVue v-if="login" />
    <LoginPageVue v-if="!login" />
  </body>
</template>

<style>
html {
  background-color: black;
}
</style>
