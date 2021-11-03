<template>
    <div id="grid">
        <div id="gridItem" v-for="object in objects" :key="object.id">
            <div v-if="type == 'teamType'">
                <Team v-bind:title="object.title" />
            </div>
            <div v-if="type == 'userType'">
                <User v-bind:title="object.title" />
            </div>
        </div>
    </div>
</template>

<script>
import Team from './Team.vue'
import User from './User.vue'

export default {
    name: 'Grid',
    components: {
        Team,
        User,
    },
    props: ['objects', 'type'],
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
