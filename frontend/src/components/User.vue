<template>
  <v-card elevation="2" outlined style="width: 200px;">
    <v-card-title
      style="justify-content: center"
      class="cursor-pointer"
      v-on:click="goToUserProfile(object.id)"
      >{{ object.firstname }} {{ object.lastname }}</v-card-title
    >
    <v-row style="justify-content: center">
      <v-card-actions>
        <div class="text-center">
          <v-dialog v-model="dialogPromote" width="30%">
            <template v-slot:activator="{ on, attrs }">
              <v-btn elevation="2" x-small color="primary" v-bind="attrs" v-on="on">PROMOTE</v-btn>
            </template>
            <v-card>
              <v-card-title class="text-h6 lighten-2">Are you sure you want to promote {{object.firstname}} {{object.lastname}}?</v-card-title>
              <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn color="primary" text @click="dialogPromote = false">YES</v-btn>
                <v-btn color="error" text @click="dialogPromote = false">NO</v-btn>
              </v-card-actions>
            </v-card>
          </v-dialog>
        </div>
      </v-card-actions>
      <v-card-actions>
        <div class="text-center">
          <v-dialog v-model="dialogDelete" width="30%">
            <template v-slot:activator="{ on, attrs }">
              <v-btn elevation="2" x-small color="error" v-bind="attrs" v-on="on">Delete</v-btn>
            </template>
            <v-card>
              <v-card-title class="text-h6 lighten-2">Are you sure you want to delete {{object.firstname}} {{object.lastname}}?</v-card-title>
              <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn color="primary" text @click="dialogDelete = false">YES</v-btn>
                <v-btn color="error" text @click="dialogDelete = false">NO</v-btn>
              </v-card-actions>
            </v-card>
          </v-dialog>
        </div>
      </v-card-actions>
    </v-row>
  </v-card>
</template>

<script>
export default {
  name: "User",
  methods: {
    goToUserProfile(id) {
      this.$router.push(`/user/${id}`);
    },
  },
  props: ["object"],
  data() {
    return {
      dialogDelete: false,
      dialogPromote: false
    }
  }
};
</script>

<style scoped>
.v-card {
  margin: 5px;
}

>>> .v-dialog {
  overflow-y: visible;
}

.v-card__title {
  padding: 10px;
}
</style>
