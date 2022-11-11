<script setup lang="ts">
defineProps<{
  msg: string;
}>();
</script>

<template>
  <header class="greetings">
    <div class="DashFrame">
      <span class="green">{{ msg }}</span>
    </div>
    <div class="nav_connect">
      <div class="connect_button" @click="showModalLog = true">login</div>
      <div class="connect_button" @click="showModalSign = true">Sign up</div>
    </div>
  </header>
  <div v-if="showModalLog">
    <transition name="modal">
      <div class="modal-mask">
        <div class="modal-wrapper">
          <div class="modal-container">
            <div class="modal-header">
              <h3>Login</h3>
              <q-btn
                color="red"
                label="X"
                class="btn-close"
                @click="showModalLog = false"
              />
            </div>
            <div class="modal-body">
              <div class="oauth-button">
                <a href="http://localhost:8080/oauth2/authorization/google"
                  ><q-btn color="primary" label="Google" class="btn-close"
                /></a>
              </div>
              <div class="oauth-button">
                <a href="http://localhost:8080/oauth2/authorization/github"
                  ><q-btn color="primary" label="Github" class="btn-close"
                /></a>
              </div>

              <q-input rounded outlined v-model="name" label="Username" />
              <q-input rounded outlined v-model="mdp" label="Password" />
            </div>
            <div class="modal-footer">
              <slot name="footer">
                <q-btn
                  color="primary"
                  @click="showModalLog = false"
                  label="Login"
                />
              </slot>
            </div>
          </div>
        </div>
      </div>
    </transition>
  </div>
  <div v-if="showModalSign">
    <transition name="modal" :showModalSign="false">
      <div class="modal-mask">
        <div class="modal-wrapper">
          <div class="modal-container">
            <div class="modal-header">
              <h3>Sign in</h3>
              <q-btn
                color="red"
                label="X"
                class="btn-close"
                @click="showModalSign = false"
              />
            </div>
            <div class="modal-body">
              <div class="oauth-button">
                <a href="http://localhost:8080/oauth2/authorization/google"
                  ><q-btn color="primary" label="Google" class="btn-close"
                /></a>
              </div>
              <div class="oauth-button">
                <a href="http://localhost:8080/oauth2/authorization/github"
                  ><q-btn color="primary" label="Github" class="btn-close"
                /></a>
              </div>
              <q-input rounded outlined v-model="name" label="Username" />
              <q-input rounded outlined v-model="mail" label="Email" />
              <q-input rounded outlined v-model="mdp" label="Password" />
            </div>
            <div class="modal-footer">
              <slot name="footer">
                <q-btn
                  color="primary"
                  @click="showModalSign = false"
                  label="Sign in"
                />
              </slot>
            </div>
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
      showModalLog: false,
      showModalSign: false,
      name: "",
      mdp: "",
      mail: "",
    };
  },
};
</script>

<style scoped>
@import "../assets/modalStyle.css";
header {
  background-color: #adadad;
  top: 0%;
  display: flex;
  display: flex;
  align-items: center;
  justify-content: space-between;
  width: 100%;
}
.oauth-button {
  margin: 10px;
}
.oauth-button > a > * {
  min-width: 100px;
}
span {
  font-size: 36px;
  font-family: monospace;
  color: #bdbdbd;
}
.DashFrame {
  text-align: center;
  vertical-align: middle;
  width: 240px;
  height: 50px;
  margin: 10px;
  background: #2c2c2c;
  border-radius: 20px;
  overflow-y: ;
}
.connect_button {
  box-sizing: border-box;
  padding: 16px;
  gap: 16px;
  background-color: #2c2c2c;
  margin: 10px;
  border-radius: 999px;
  color: #bdbdbd;
  border: 2px solid #bdbdbd;
  box-shadow: 0px 2px 4px rgba(0, 0, 0, 0.2), 0px 5px 15px rgba(0, 0, 0, 0.15);
}
.nav_connect {
  display: flex;
}
</style>
