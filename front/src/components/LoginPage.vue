<template>
  <div class="content_page">
    <div class="vertical">
      <div class="q-pa-md q-gutter-sm">
        <q-btn
          class="oauth_button"
          QBtnProps.align="center"
          v-on:click="google_redirection"
          color="black"
        >
          <img class="oauth_img" src="./../../public/Google_Logo.png" />
          Access with Google
        </q-btn>
        <q-btn
          class="oauth_button"
          QBtnProps.align="center"
          v-on:click="github_redirection"
          color="black"
        >
          <img class="oauth_img" src="./../../public/GitHub_Logo.png" />
          Access with GitHub
        </q-btn>
      </div>
      <div class="center_switch_link">
        <div class="switch_link" href="" v-on:click="state = !state">
          <p v-if="state">No account yet? Register here</p>
          <p v-if="!state">Already have an account? Login here</p>
        </div>
      </div>
      <div class="center_input">
        <div class="vertical_input">
          <div v-if="!state" style="visibility: hidden; font-size: 15px">
            just a space
          </div>
          <q-input
            v-if="!state"
            rounded
            standout
            outlined
            v-model="name"
            lazy-rules
            :rules="[(val) => (val && val.length > 0) || 'Name is required']"
            label="Name"
            placeholder="Enter your name ..."
          >
            <template v-slot:append>
              <q-icon
                v-if="username !== ''"
                name="close"
                @click="username = ''"
                class="cursor-pointer"
              />
            </template>
          </q-input>
          <q-input
            class="input"
            rounded
            standout
            outlined
            v-model="username"
            lazy-rules
            :rules="[
              (val) => (val && val.length > 0) || 'Email address is required',
            ]"
            label="Email address"
            placeholder="Enter your email address ..."
          >
            <template v-slot:append>
              <q-icon
                v-if="username !== ''"
                name="close"
                @click="username = ''"
                class="cursor-pointer"
              />
            </template>
          </q-input>
          <q-input
            class="input"
            rounded
            standout
            outlined
            v-model="password"
            lazy-rules
            :rules="[
              (val) => (val && val.length > 0) || 'Password is required',
            ]"
            type="password"
            label="Password"
            placeholder="Enter your password ..."
          >
            <template v-slot:append>
              <q-icon
                v-if="password !== ''"
                name="close"
                @click="password = ''"
                class="cursor-pointer"
              />
            </template>
          </q-input>
          <p style="color: green" class="ok">
            A verification email has been sent to you!
          </p>
          <p style="color: red" class="error">
            A user with the same email address already exists.
          </p>
          <q-btn
            v-if="state"
            color="black"
            v-on:click="login(username, password)"
          >
            LOGIN
          </q-btn>
          <q-btn
            v-else
            color="black"
            v-on:click="register(name, username, password)"
          >
            REGISTER
          </q-btn>
          <div v-if="!state" style="visibility: hidden; font-size: 13px">
            just a space
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import { AuthenticationApi } from "@/api";

export default {
  data() {
    return {
      name: "",
      username: "",
      password: "",
      google_redirection: () => {
        window.location.href =
          "http://localhost:8080/oauth2/authorization/google";
      },
      github_redirection: () => {
        window.location.href =
          "http://localhost:8080/oauth2/authorization/github";
      },
      login: (username: string, password: string) => {
        if (username !== "" && password !== "") {
          let authenticationApi = new AuthenticationApi();
          authenticationApi
            .createToken({
              headers: {
                Authorization: "Basic " + btoa(username + ":" + password),
              },
            })
            .then((response) => {
              var date = new Date();
              date.setTime(date.getTime() + 8 * 60 * 60 * 1000);
              document.cookie =
                "token=" +
                (response || "") +
                "; expires=" +
                date.toUTCString() +
                "; path=/";
              window.location.reload();
            });
        }
      },
      register: (name: string, username: string, password: string) => {
        if (name !== "" && username !== "" && password !== "") {
          let authenticationApi = new AuthenticationApi();
          authenticationApi
            .createUserAndSendEmail({
              createUserAndSendEmailRequest: {
                name: name,
                username: username,
                hashPassword: password,
              },
            })
            .then(() => {
              let message: HTMLElement | null = document.querySelector(".ok");
              if (message) {
                message.style.display = "initial";
                message = document.querySelector(".error");
                if (message?.style.display == "initial") {
                  message.style.display = "none";
                }
              }
            })
            .catch(() => {
              let message: HTMLElement | null =
                document.querySelector(".error");
              if (message) {
                message.style.display = "initial";
                message = document.querySelector(".ok");
                if (message?.style.display == "initial") {
                  message.style.display = "none";
                }
              }
            });
        }
      },
      state: false,
    };
  },
};
</script>

<style>
.content_page {
  background-color: white;
  display: flex;
  justify-content: center;
  margin-top: 7.5%;
  margin-left: 24%;
  width: 50%;
  padding: 20px 0px 45px 0px;
}
.vertical {
  display: flex;
  flex-direction: column;
  justify-content: center;
  width: 75%;
}
.center_input {
  display: flex;
  justify-content: center;
}
.vertical_input {
  display: flex;
  flex-direction: column;
  justify-content: center;
  row-gap: 5%;
}
.oauth_button {
  width: 50% !important;
  padding: 1% !important;
}
.oauth_img {
  width: 15%;
  margin-right: 5%;
  margin-top: 2.5%;
  margin-bottom: 2.5%;
}
.q-gutter-sm {
  display: flex !important;
  justify-content: center;
  gap: 2.5% !important;
}
.input {
  width: 100%;
}
.center_switch_link {
  display: flex;
  justify-content: center;
}
.switch_link > p {
  display: inline-block;
  cursor: pointer;
}
.ok,
.error {
  display: none;
}
</style>
