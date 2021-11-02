<template>
    <div id="content">
        <UserBar />

        <Grid teams="{{ this.teams }}" />
    </div>
</template>

<script>
import UserBar from '../components/UserBar.vue'
import Grid from '../components/Grid.vue'

export default {
    name: 'Teams',
    methods: {
        getTeams() {
            try {
                console.log('getTeams')

                return [
                    {
                        title: 'team1',
                    },
                    {
                        title: 'team2',
                    },
                ]
            } catch (error) {
                console.log(error)
            }
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
