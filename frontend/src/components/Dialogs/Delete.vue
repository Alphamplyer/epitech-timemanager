<template>
  <v-dialog v-model="dialogDelete" width="30%">
    <template v-slot:activator="{ on, attrs }">
      <div v-bind="attrs" v-on="on">Delete</div>
    </template>
    <v-card>
      <v-card-title v-if="type == 'userType'" class="text-h6 lighten-2"
        >Are you sure you want to delete {{ object.username }} ?
      </v-card-title>
      <v-card-title v-if="type == 'teamType'" class="text-h6 lighten-2"
        >Are you sure you want to delete {{ object.name }} ?
      </v-card-title>
      <v-card-actions>
        <v-spacer></v-spacer>
        <v-btn color="primary" text @click="deleteData">YES</v-btn>
        <v-btn color="error" text @click="dialogDelete = false">NO</v-btn>
      </v-card-actions>
    </v-card>
  </v-dialog>
</template>

<script>
import { apiCall } from '../../../lib/api'

export default {
  name: "Delete",
  props: ["object", "type"],
  data() {
    return {
      dialogDelete: false,
      account: this.$store.state.user
    };
  },
  methods: {
    async deleteData() {
      this.dialogDelete = false;
      if(this.type == 'userType') {
        await apiCall({
          route: `/api/users/${this.object.id}`, 
          method: 'DELETE'
        })
      }
      else if(this.type == 'teamType'){
        await apiCall({
          route: `/api/teams/${this.object.id}`, 
          method: 'DELETE'
        })
      }
    },
  },
};
</script>
