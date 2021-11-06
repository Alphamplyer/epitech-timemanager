<template>
  <div>
    <Navbar />
    <div id="teams">
      <div id="titleRow" class="customRow">
        <h1 class="teamsTitle">All Teams</h1>
      </div>
      <Grid v-bind:objects="teams" v-bind:type="type" />
      <div id="thirdrow" class="customRow"></div>
    </div>
  </div>
</template>

<script>
import Navbar from "../components/Navbar.vue";
import Grid from "../components/Grid.vue";
export default {
  name: "Teams",
  methods: {
    getTeams() {
      return [
        {
          id: 1,
          title: "Team 1",
        },
        {
          id: 2,
          title: "Team 2",
        },
      ];
    },
  },
  data() {
    return {
      teams: this.getTeams(),
      type: "teamType",
    };
  },
  props: ["object"],
  components: { Navbar, Grid },
};
</script>

<style scoped>
#teams {
  display: grid;
  grid-template-columns: repeat(2, 1fr);
  grid-template-rows: 0.2fr 1fr 1fr;
  column-gap: 10px;
  row-gap: 10px;
  height: 100%;
  background-color: #dddde6;
}

.customRow {
  margin: 20px;
  box-shadow: 0px 3px 1px -2px rgba(0, 0, 0, 0.2),
    0px 2px 2px 0px rgba(0, 0, 0, 0.14), 0px 1px 5px 0px rgba(0, 0, 0, 0.12);
}

.teamsTitle {
  font-size: xxx-large;
  margin-left: 2%;
}

#titleRow {
  grid-column: 1 / 3;
  grid-row: 1;
  background-color: #ffffff;
}

#thirdrow {
  grid-column: 1 / 3;
  grid-row: 3;
  background-color: #ffffff;
}
</style>
