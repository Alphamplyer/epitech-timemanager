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
    props: ['teams'],
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
</style>
