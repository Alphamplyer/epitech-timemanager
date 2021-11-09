<template>
  <v-card elevation="2" outlined style="width: 200px; height: 33%">
    <v-card-title style="justify-content: center">
      {{
        object.name
      }}
    </v-card-title>
    <v-row style="justify-content: center">
      <v-card-actions>
        <Users v-bind:object="object" />
      </v-card-actions>
      <v-card-actions>
        <v-btn elevation="2" x-small color="error">
          <Delete v-bind:object="object" v-bind:type="type"/>
        </v-btn>
      </v-card-actions>
    </v-row>
  </v-card>
</template>

<script>
import Delete from "./Dialogs/Delete.vue";
import Users from "./Dialogs/Users.vue";
export default {
    name: "Team",
    props: ["object", "type"],
    data() {
        return {
            dialogUsers: false,
            dialogDelete: false
        };
    },
    components: { Delete, Users }
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
