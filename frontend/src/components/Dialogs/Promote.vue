<template>
  <v-dialog v-model="dialogPromote" width="30%">
    <template v-slot:activator="{ on, attrs }">
      <div v-bind="attrs" v-on="on">Promote</div>
    </template>
    <v-card>
      <v-card-title class="text-h6 lighten-2"
        >Are you sure you want to promote {{ object.username }} ?</v-card-title
      >
      <v-card-actions>
        <v-spacer></v-spacer>
        <v-btn color="primary" text @click="dialogPromote = false">YES</v-btn>
        <v-btn color="error" text @click="dialogPromote = false">NO</v-btn>
      </v-card-actions>
    </v-card>
  </v-dialog>
</template>

<script>
export default {
  name: "Promote",
  props: ["object"],
  data() {
    return {
      dialogPromote: false,
    };
  },
};
</script>
