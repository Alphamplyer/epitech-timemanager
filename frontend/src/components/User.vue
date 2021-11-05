<template>
  <v-card elevation="2" outlined style="width: 200px;">
    <v-card-title
      style="justify-content: center"
      class="cursor-pointer"
      v-on:click="goToUserProfile(object.id)"
      >{{ object.firstname }} {{ object.lastname }}</v-card-title
    >
    <v-row style="justify-content: center">
      <v-card-actions> </v-card-actions>
      <v-card-actions>
        <v-btn elevation="2" x-small color="error"> Delete </v-btn>
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
};
</script>

<style scoped>
.v-card {
  margin: 5px;
}

.v-card__title {
  padding: 10px;
}
</style>
