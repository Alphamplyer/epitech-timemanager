<template>
  <div class="text-center">
    <v-dialog v-model="dialogUsers" width="30%">
      <template v-slot:activator="{ on, attrs }">
        <v-btn
          elevation="2"
          x-small
          v-bind="attrs"
          v-on="on"
          v-on:click="getTeamMember()"
          >Users</v-btn
        >
      </template>
      <v-card>
        <v-card-title class="text-h5 lighten-2">Users</v-card-title>

        <v-simple-table>
          <template v-slot:default>
            <thead>
              <tr>
                <th class="text-left">Name</th>
                <th class="text-left"></th>
              </tr>
            </thead>
            <tbody>
              <tr v-for="member in object.members" :key="member.id">
                <td>{{ member }}</td>
                <v-btn elevation="2" x-small color="error">Delete</v-btn>
              </tr>
            </tbody>
          </template>
        </v-simple-table>
        <v-divider></v-divider>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="primary" text @click="dialogUsers = false"
            >I accept</v-btn
          >
        </v-card-actions>
      </v-card>
    </v-dialog>
  </div>
</template>

<script>
import { apiCall } from "../../../lib/api";

export default {
  name: "Users",
  props: ["object"],
  methods: {
    async getTeamMember() {
      const response = await apiCall({
        route: `/api/teams/${this.object.id}/members`,
      });
      console.log(response);
    //   this.members = await response.json();
    //   console.log(this.members);
    },
  },
  data() {
    return {
      members: null,
      dialogUsers: false,
    };
  },
};
</script>
