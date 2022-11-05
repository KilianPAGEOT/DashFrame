<script lang="ts">
import MainPageVue from "./components/MainPage.vue";
import MainHeaderVue from "./components/MainHeader.vue";
import { Configuration, InformationApi, ServicesApi } from "./api";
export default {
  components: {
    MainPageVue,
    MainHeaderVue,
  },
};

/** TEMP: API usage example */
async function apiExample() {
  const infoApi = new InformationApi(
    new Configuration({
      basePath: "http://localhost:8080",
    })
  );
  const servicesApi = new ServicesApi();

  const [about, services] = await Promise.all([
    infoApi.getAboutJson(),
    servicesApi.listServices(),
  ]);

  console.groupCollapsed("Example API calls");
  console.log("client information", about.client);
  console.log("server information", about.server);
  console.log("detailed service information", services);
  console.groupEnd();
}

apiExample();
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
