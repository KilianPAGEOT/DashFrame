<script setup lang="ts">
import MainHeaderVue from "./components/MainHeader.vue";
</script>

<template>
  <header>
    <div class="wrapper">
      <MainHeaderVue msg="DashFrame" />
    </div>
  </header>

  <main>
    <q-btn color="amber" glossy label="Amber" />
  </main>
</template>

<style scoped>
html > body {
  margin: 0px;
}
</style>
