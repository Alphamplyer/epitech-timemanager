<template>
  <v-container
    fill-height
    class="d-flex flex-row mr-12 align-center justify-center"
  >
    <v-responsive
      style="
        border: 1px solid;
        border-color: #a7a7a7;
        background-color: white;
        font-size: 48px;
      "
      class="rounded-lg pa-8 blue--text text-center"
      max-width="350px"
      min-width="250px"
    >
      <h3 class="mb-8">Register</h3>

      <v-divider style="background-color: #becdda" />

      <v-form class="py-4" v-model="isFormValid">
        <v-text-field
          required
          outlined
          autofocus
          placeholder="example@gmail.com"
          label="Email"
          v-model="email"
          :rules="[emailRules]"
        />

        <v-text-field
          required
          outlined
          placeholder="Gabriel"
          label="Username"
          v-model="username"
          :rules="[usernameRules]"
        />

        <v-text-field
          required
          outlined
          placeholder="Secret"
          label="Password"
          type="password"
          v-model="password"
          :rules="[passwordRules]"
        />
        <v-text-field
          required
          outlined
          placeholder="Secret again"
          label="Password again"
          type="password"
          v-model="rePassword"
          :rules="[passwordRules, passwordConfirmationRule]"
        />
      </v-form>

      <v-divider style="background-color: #becdda" />

      <v-container class="d-flex flex-column justify-space-between">
        <v-btn
          block
          color="primary"
          :disabled="!isFormValid"
          style="font-size: 18px; border: 2px solid black !important"
          class="font-weight-bold rounded-0 text-capitalize"
          v-on:click="
            () => {
              register();
            }
          "
        >
          Register
        </v-btn>
      </v-container>
    </v-responsive>

    <v-responsive>
      <v-img src="@/assets/register.svg" max-height="700px" lazy />
    </v-responsive>
  </v-container>
</template>

<script>
import { createUser } from "../../lib/user.js";
import ref from "vue";

export default {
  setup() {
    const { email, username, password } = ref("");

    return {
      email,
      username,
      password,
    };
  },
  computed: {
    passwordConfirmationRule() {
      return () => this.password === this.rePassword || "Password must match";
    },
    passwordRules() {
      return (
        () => (v) => !!v || "Password is required",
        (v) =>
          (v && v.length >= 8) || "Password must have at least 8 characters"
      );
    },
    emailRules() {
      return (
        () => (v) => !!v || "Email is required",
        (v) => (v && !!v.trim()) || "Value cannot be blank",
        (v) =>
          !v ||
          /^\w+([.-]?\w+)*@\w+([.-]?\w+)*(\.\w{2,3})+$/.test(v) ||
          "E-mail must be valid"
      );
    },
    usernameRules() {
      return (
        () => (v) => !!v || "Username is required",
        (v) => (v && !!v.trim()) || "Value cannot be blank"
      );
    },
  },
  methods: {
    async register() {
      const res = await createUser({
        email: this.email,
        username: this.username,
        password: this.password,
      });

      if (!res.ok) {
        if (res.status === 500) {
          this.error = "";
        }
        //TODO: Toaster de non-register
      } else {
        await this.$router.push("/").then(() => {
          //TODO: Toaster de register
        });
      }
    },
  },
  data() {
    return {
      email: this.email,
      username: this.username,
      password: this.password,
      rePassword: undefined,
      isFormValid: false,
    };
  },
};
</script>
