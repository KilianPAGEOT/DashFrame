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
      <q-btn color="white" text-color="black" label="Standard" />
      <div class="connect_button">login</div>
      <div class="connect_button">Sign up</div>
    </div>
  </header>
</template>

<style scoped>
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
