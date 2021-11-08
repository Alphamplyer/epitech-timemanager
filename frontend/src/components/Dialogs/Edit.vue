<template>
    <v-dialog v-model="dialogEdit" width="30%">
      <template v-slot:activator="{ on, attrs }">
        <div v-bind="attrs" v-on="on"
          ><v-icon>mdi-pencil-outline</v-icon>
          Edit user</div
        >
      </template>
      <v-card>
        <v-text-field
          v-model="firstname"
          :rules="nameRules"
          :counter="10"
          label="First name"
          required
        ></v-text-field>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="primary" text @click="dialogEdit = false">YES</v-btn>
          <v-btn color="error" text @click="dialogEdit = false">NO</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
</template>

<script>
export default {
  name: "Delete",
  props: ["object", "type"],
  data() {
    return {
      dialogEdit: false,
    };
  },
};
</script>
