<template>
  <div class="text-center">
    <v-dialog v-model="dialogAddUser" width="40%">
      <template v-slot:activator="{ on, attrs }">
        <div v-bind="attrs" v-on="on">Add user(s)</div>
      </template>
      <v-card>
        <v-card-title class="text-h5 lighten-2"
          >Users a{{ this.team.id }}</v-card-title
        >

        <v-simple-table>
          <template v-slot:default>
            <thead>
              <tr>
                <th class="text-left">Username</th>
                <th class="text-left"></th>
              </tr>
            </thead>
            <tbody>
              <tr v-for="user in users" :key="user.id">
                <td>{{ user.username }} {{ user.id }}</td>
                <td>
                  <v-btn
                    elevation="2"
                    x-small
                    color="success"
                    v-on:click="addUserToTeam(user.id)"
                    >Add</v-btn
                  >
                </td>
              </tr>
            </tbody>
          </template>
        </v-simple-table>
        <v-divider></v-divider>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="primary" text @click="dialogAddUser = false">OK</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </div>
</template>

<script>
import { apiCall } from "../../../lib/api";

export default {
  name: "addUser",
  props: ["team"],
  mounted() {
    this.getUsers();
  },
  methods: {
    async getUsers() {
      const response = await apiCall({
        route: "/api/users",
      });
      this.users = await response.json();
      console.log(this.users);
    },
    async addUserToTeam(userID) {
      console.log(this.team);
      console.log(userID);
      const response = await apiCall({
        route: `/api/teams/${this.team}/add/${userID}`,
        method: "POST",
      });
      console.log(await response);
    },
  },
  data() {
    return {
      users: null,
      dialogAddUser: false,
    };
  },
};
</script>

<style scoped></style>
