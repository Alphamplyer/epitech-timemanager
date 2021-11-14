<template>
  <v-dialog v-model="dialogEdit" width="40%">
    <template v-slot:activator="{ on, attrs }">
      <div v-bind="attrs" v-on="on">Edit</div>
    </template>
    <v-card>
      <v-text-field
        v-model="username"
        label="Username"
        required
        :placeholder="object.username"
      ></v-text-field>
      <v-text-field
        v-model="email"
        label="Email"
        required
        :placeholder="object.email"
        :rules="[emailRules]"
      ></v-text-field>
      <!-- <v-text-field v-model="password" label="Password" required></v-text-field>
      <v-text-field
        v-model="rePassword"
        :rules="[passwordConfirmationRule]"
        label="re-enter password"
        required
      ></v-text-field> -->
      <v-card-actions>
        <v-spacer></v-spacer>
        <v-btn color="primary" text @click="editUserInfo">CONFIRM</v-btn>
        <v-btn color="error" text @click="dialogEdit = false">CANCEL</v-btn>
      </v-card-actions>
    </v-card>
  </v-dialog>
</template>

<script>
import { apiCall } from "../../../lib/api";

export default {
  name: "Delete",
  props: ["object", "type", "rules"],
  methods: {
    async editUserInfo() {
      this.dialogEdit = false;
      await apiCall({
        route: `/api/users/${this.object.id}`,
        method: "PUT",
        body: JSON.stringify({
          username: this.username,
          email: this.email,
        })
      })

      this.$store.dispatch('editUser', {
        ...this.object,
        email: this.email,
        username: this.username
      })

      this.$router.go()
    },
  },
  computed: {
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
  },
  data() {
    return {
      dialogEdit: false,
      // password: null,
      // rePassword: null,
      username: null,
      email: null,
    };
  },
};
</script>
