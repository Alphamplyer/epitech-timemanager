<template>
  <div id="secondrow" class="customRow rounded-lg">
    <div class="item" v-for="object in objects" :key="object.id">
      <div v-if="type == 'teamType'">
        <Team v-bind:object="object" v-bind:type="type" />
      </div>
      <div v-if="type == 'userType'">
        <User v-bind:object="object" v-bind:type="type" />
      </div>
    </div>
  </div>
</template>

<script>
import User from "./User.vue";
import Team from "./Team.vue";

export default {
  name: "Grid",
  components: { User, Team },
  props: ["objects", "type"],
};
</script>

<style>
#secondrow {
  grid-column: 1 / 3;
  grid-row: 2;
  background-color: #ffffff;
  display: inline-flex;
  flex-wrap: wrap;
  overflow-y: auto;
  max-height: 80vh;
}
</style>
