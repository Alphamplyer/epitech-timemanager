<template>
  <v-card
    elevation="2"
    outlined
    style="width: 189px"
    v-if="object.role !== 'GLOBAL_MANAGER'"
  >
    <v-card-title class="text-truncate" style="justify-content: center;">{{
      object.username
    }}</v-card-title>
    <v-row style="justify-content: center">
      <v-card-actions v-if="object.role == 'EMPLOYEE'">
        <v-btn elevation="2" x-small color="primary">
          <Promote v-bind:object="object" />
        </v-btn>
      </v-card-actions>
      <v-card-actions>
        <v-btn elevation="2" x-small color="error">
          <Delete v-bind:object="object" v-bind:type="type" />
        </v-btn>
      </v-card-actions>
    </v-row>
  </v-card>
</template>

<script>
import Delete from "./Dialogs/Delete.vue";
import Promote from "./Dialogs/Promote.vue";
export default {
  name: "User",
  props: ["object", "type"],
  data() {
    return {
      dialogDelete: false,
      dialogPromote: false,
    };
  },
  components: { Delete, Promote },
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
