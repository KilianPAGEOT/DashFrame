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

/** TEMP: API usage example */
async function apiExample() {
  const infoApi = new InformationApi(
    new Configuration({
      basePath: BASE_PATH.replace("/api/v1", ""),
    })
  );
  const servicesApi = new ServicesApi();

  const [about, services] = await Promise.all([
    infoApi.getAboutJson(),
    servicesApi.listServices({}, { credentials: "include" }),
  ]);

  console.groupCollapsed("Example API calls");
  console.log("client information", about.client);
  console.log("server information", about.server);
  console.log("detailed service information", services);
  console.groupEnd();
}

apiExample();
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
