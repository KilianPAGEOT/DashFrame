<script setup lang="ts">
function logout() {
  document.cookie = "token=; Max-Age=-99999999;";
  window.location.reload();
}
defineProps<{
  msg: string;
  isConnected: boolean;
}>();
</script>

<template>
  <header>
    <div class="DashFrame">
      <span>{{ msg }}</span>
    </div>
    <button v-on:click="logout" v-if="isConnected" class="logout">
      Logout
    </button>
  </header>
</template>

<script lang="ts">
export default {
  data() {
    return {
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
}
.logout {
  text-align: center;
  vertical-align: middle;
  width: 240px;
  height: 50px;
  margin: 10px;
  background: rgb(200, 63, 63);
  border-radius: 10px;
  border: none;
  font-size: 36px;
  font-family: monospace;
  color: #bdbdbd;
  cursor: pointer;
}
</style>
