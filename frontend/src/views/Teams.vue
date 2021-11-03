<template>
    <div id="content">
        <UserBar />
        <Grid v-bind:teams="this.teams" />
    </div>
</template>

<script>
import UserBar from '../components/UserBar.vue'
import Grid from '../components/Grid.vue'

export default {
    name: 'Teams',
    methods: {
        getTeams() {
            return [
                {
                    id: 1,
                    title: 'team1',
                },
                {
                    id: 2,
                    title: 'team2',
                },
            ]
        },
    },
    components: {
        UserBar,
        Grid,
    },
    data() {
        return {
            teams: this.getTeams(),
        }
    },
}
</script>

<style>
#content {
    display: flex;
    flex-direction: column;
    width: 100%;
}
</style>
