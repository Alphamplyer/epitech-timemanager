<template>
  <v-card elevation="2" outlined style="width: 200px; height: 33%">
    <v-card-title style="justify-content: center">
      {{
        object.title
      }}
    </v-card-title>
    <v-row style="justify-content: center">
      <v-card-actions>
        <div class="text-center">
          <v-dialog v-model="dialogUsers" width="30%">
            <template v-slot:activator="{ on, attrs }">
              <v-btn elevation="2" x-small v-bind="attrs" v-on="on">Users</v-btn>
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
                    <tr>
                      <td>dab</td>
                      <v-btn elevation="2" x-small color="error">Delete</v-btn>
                    </tr>
                  </tbody>
                </template>
              </v-simple-table>
              <v-divider></v-divider>
              <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn color="primary" text @click="dialogUsers = false">I accept</v-btn>
              </v-card-actions>
            </v-card>
          </v-dialog>
        </div>
      </v-card-actions>
      <v-card-actions>
        <Delete v-bind:object="object" v-bind:type="type"/>
      </v-card-actions>
    </v-row>
  </v-card>
</template>

<script>
import Delete from "./Dialogs/Delete.vue";
export default {
    name: "Team",
    props: ["object", "type"],
    data() {
        return {
            dialogUsers: false,
            dialogDelete: false
        };
    },
    components: { Delete }
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

.teamsTitle {
  font-size: xxx-large;
  margin-left: 2%;
}
</style>
