<script setup lang="ts">
import "../assets/modalStyle.css";
</script>

<template>
  <body class="listWidget">
    <div class="columnWidget">
      <div class="orderWidget">
        <div class="WidgetWeather">
          <h3>Wethear of Nantes</h3>
          <span>27°C</span>
        </div>
      </div>
      <div class="Elipse">
        <div class="insideElipse">+</div>
      </div>
    </div>
    <div class="Empty">
      <div class="Elipse">
        <div class="insideElipse">+</div>
      </div>
    </div>
  </body>
</template>

<script lang="ts">
export default {
  data() {
    return {
      showModal: false,
    };
  },
};
</script>

<style>
@import "../assets/modalStyle.css";
.listWidget {
  display: flex;
  align-items: flex-start;
}
.WidgetWeather {
  width: 450px;
  height: 200px;
  background: #393131;
  border-radius: 20px;
  margin: 10px;
  font-size: medium;
  display: flex;
  flex-direction: column;
}
.WidgetWeather > h3 {
  margin: 20px;
}
.WidgetWeather > span {
  margin: 20px;
  font-size: 28px;
  align-self: center;
}
.orderWidget {
  display: flex;
  justify-content: center;
  align-items: center;
}
.columnWidget {
  margin: 30px;
  border-radius: 20px;
  background: #d9d9d9;
  display: flex;
  flex-direction: column;
  align-items: center;
  position: relative;
  padding-bottom: 50px;
}
.Empty {
  width: 470px;
  height: 660px;
  border: 5px dashed #d9d9d9;
  margin: 30px;
  border-radius: 20px;
  background-color: transparent;
  display: flex;
  justify-content: center;
  align-items: center;
}
.Elipse {
  box-sizing: border-box;
  width: 77px;
  height: 77px;
  border-radius: 1000px;
  display: flex;
  justify-content: center;
  align-items: center;
  background: #d9d9d9;
  border: 1px solid #393131;
}
.columnWidget > .Elipse {
  width: 77px;
  height: 77px;
  position: absolute;
  top: 87%;
}
.insideElipse {
  position: relative;
  width: 22px;
  height: 39px;
  align-items: center;
  font-family: "Inter";
  font-style: normal;
  font-weight: 400;
  font-size: 32px;
  line-height: 39px;
  /* identical to box height */

  color: #393131;
}
</style>
