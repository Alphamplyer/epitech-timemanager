<template>
  <v-card elevation="2" outlined style="width: 200px; height: 33%">
    <v-card-title style="justify-content: center">{{
      object.title
    }}</v-card-title>
    <v-row style="justify-content: center">
      <v-card-actions>
        <v-btn elevation="2" x-small> Users </v-btn>
      </v-card-actions>
      <v-card-actions>
        <v-btn elevation="2" x-small color="error"> Delete </v-btn>
      </v-card-actions>
    </v-row>
  </v-card>
</template>

<script>
export default {
  name: "Team",
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

.teamsTitle {
  font-size: xxx-large;
  margin-left: 2%;
}
</style>
