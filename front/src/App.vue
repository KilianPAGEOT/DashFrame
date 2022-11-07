<script lang="ts">
import MainPageVue from "./components/MainPage.vue";
import MainHeaderVue from "./components/MainHeader.vue";
import { BASE_PATH, Configuration, InformationApi, ServicesApi } from "./api";
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
      basePath: BASE_PATH.replace("/api/v1", ""),
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

function eventsExample() {
  const path = BASE_PATH.replace(/^(http|https)/, "ws");
  const eventsWs = new WebSocket("ws://127.0.0.1:8080/api/v1/ws/events");

  eventsWs.onopen = () => {
    console.log("Events WS opened, sending ping message");
    eventsWs.send(
      JSON.stringify({ from: "client", message: "lel, I'm the client" })
    );
  };
  eventsWs.onclose = (event) =>
    console.error("Events WS closed, reason: ", event.reason);
  eventsWs.onerror = (event) => console.error("Events WS errored: ", event);
  eventsWs.onmessage = (event) => {
    console.log("server responded with", JSON.parse(event.data));
  };
}

apiExample();
try {
  eventsExample();
} catch (error) {
  console.log(`epic failure: ${error}`);
}
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
