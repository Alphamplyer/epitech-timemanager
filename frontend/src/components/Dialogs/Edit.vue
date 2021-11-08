<template>
  <v-dialog v-model="dialogEdit" width="30%">
    <template v-slot:activator="{ on, attrs }">
      <div v-bind="attrs" v-on="on">
        <v-icon>mdi-pencil-outline</v-icon> Edit user
      </div>
    </template>
    <v-card>
      <v-text-field
        v-model="firstname"
        label="First name"
        required
      ></v-text-field>
      <v-text-field
        v-model="password"
        label="Password"
        required
      ></v-text-field>
      <v-text-field
        v-model="rePassword"
        :rules="[passwordConfirmationRule]"
        label="re-enter password"
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
  props: ["object", "type", "rules", "firstname"],
  computed: {
    passwordConfirmationRule() {
      return () => (this.password === this.rePassword) || 'Password must match'
    }
},
  data() {
    return {
      dialogEdit: false,
      password: undefined,
      rePassword: undefined
    };
  },
};
</script>
