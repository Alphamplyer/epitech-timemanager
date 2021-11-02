<template>
    <div id="grid">
        <div id="teams" v-for="team in teams" :key="team">
            <Team team="{{ team }}" />
        </div>
    </div>
</template>

<script>
import Team from './Team.vue'

export default {
    name: 'Grid',
    components: {
        Team,
    },
    data() {
        return {
            teams: [
                {
                    title: 'team1',
                    id: '1',
                },
                {
                    title: 'team2',
                    id: '2',
                },
            ],
        }
    },
}
</script>

<style>
#grid {
    display: flex;
    flex-direction: row;
    flex-wrap: wrap;
    justify-content: center;
    background-color: #2c3e50;
    padding: 12px;
    min-height: 85vh;
    border-radius: 48px;
    margin-inline: 12px;
}

#grid div + div {
    margin-left: 12px;
}
</style>
