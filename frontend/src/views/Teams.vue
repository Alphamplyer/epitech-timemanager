<template>
  <div>
    <Navbar />
    <div id="teams">
      <div id="titleRow" class="customRow rounded-lg">
        <h1 class="teamsTitle rounded-lg">All Teams</h1>
      </div>
      <Grid v-bind:objects="teams" v-bind:type="type" />
      <div id="thirdrow" class="customRow rounded-lg"></div>
    </div>
  </div>
</template>

<script>
import Navbar from "../components/Navbar.vue";
import Grid from "../components/Grid.vue";
import { apiCall } from "../../lib/api";

export default {
  name: "Teams",
  beforeMount() {
    this.getTeams();
  },
  methods: {
    async getTeams() {
      console.log(this.account.role);
      if (this.account.role == "GLOBAL_MANAGER") {
        const response = await apiCall({
          route: "/api/teams",
        });
        this.teams = await response.json();
        console.log(this.teams);
      } else {
        const response = await apiCall({
          route: `/api/teams/${this.account.id}`,
        });
        this.teams = await response.json();
        console.log(this.teams);
      }
    },
  },
  data() {
    return {
      teams: null,
      type: "teamType",
      account: this.$store.state.user, 
    };
  },
  components: { Navbar, Grid },
};
</script>

<style scoped>
#teams {
  display: grid;
  grid-template-columns: repeat(2, 1fr);
  grid-template-rows: 110px 1fr;
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
