<template>
<div>
    <Navbar/>
    <div id="teams">
        <div id="titleRow" class="row">
            <h1 class="teamsTitle">All Teams</h1>
        </div>
        <div id="secondrow" class="row">
            <div class="team" v-for="team in teams" :key="team">
                <v-card
                    elevation="2"
                    outlined>
                    <span>{{ team.title }}</span>

                </v-card>
            </div>
        </div>
        <div id="thirdrow" class="row">
        </div>
    </div>
</div>
</template>

<script>
import Navbar from "../components/Navbar.vue"
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
        };
    },
    components: { Navbar }
}</script>

<style>

#teams {
    display: grid;
    grid-template-columns: repeat(2, 1fr);
    grid-template-rows: 0.5fr 1fr 1fr;
    column-gap: 10px;
    row-gap: 10px;
    height: 100%;
    background-color: #DDDDE6;
}

.row {
    margin: 20px;
    box-shadow: 0px 3px 1px -2px rgba(0, 0, 0, 0.2), 0px 2px 2px 0px rgba(0, 0, 0, 0.14), 0px 1px 5px 0px rgba(0, 0, 0, 0.12);
}

.teamsTitle {
    font-size: xxx-large;
    margin-left: 2%;
}

#titleRow {
    grid-column: 1 / 3;
    grid-row: 1;
    background-color: #FFFFFF;
}

#secondrow {
    grid-column: 1 / 3;
    grid-row: 2;
    background-color: #FFFFFF;
}

#thirdrow {
    grid-column: 1 / 3;
    grid-row: 3;
    background-color: #FFFFFF;
}

</style>