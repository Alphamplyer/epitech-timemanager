<template>
  <div class="text-center">
    <v-dialog v-model="dialogUsers" width="40%">
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
        <v-card-title class="text-h5 lighten-2"
          >Users in {{ object.name }}</v-card-title
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
              <tr v-for="(member, index) in members" :key="member.id">
                <td>{{ member.username }}</td>
                <td>
                  <v-btn
                    elevation="2"
                    x-small
                    color="error"
                    v-on:click="removeTeamMember(member.id, index)"
                    >Remove</v-btn
                  >
                </td>
              </tr>
            </tbody>
          </template>
        </v-simple-table>
        <v-divider></v-divider>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="primary" text
            ><addUser v-bind:team="object.id"
          /></v-btn>
          <v-btn color="primary" text @click="dialogUsers = false">OK</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </div>
</template>

<script>
import { apiCall } from "../../../lib/api";
import addUser from "./AddUser.vue";

export default {
  name: "Users",
  props: ["object"],
  methods: {
    async getTeamMember() {
      const response = await apiCall({
        route: `/api/teams/${this.object.id}/members`,
      });
      this.members = await response.json();
      console.log(this.members);
    },
    async removeTeamMember(userID, index) {
      const response = await apiCall({
        route: `/api/teams/${this.object.id}/remove/${userID}`,
        method: "POST",
      });
      if (response.status == 200) {
        this.$delete(this.members, index);
      }
    },
  },
  data() {
    return {
      members: null,
      dialogUsers: false,
    };
  },
  components: { addUser },
};
</script>
