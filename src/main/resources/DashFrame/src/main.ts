import { createApp } from "vue";
import App from "./App.vue";
import { Quasar } from "quasar";
import quasarUserOptions from "./quasar-user-options";

//import "./assets/main.css";

createApp(App)
  .use(Quasar, quasarUserOptions)
  .use(Quasar, quasarUserOptions)
  .mount("#app");
