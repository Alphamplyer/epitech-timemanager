<template>
    <div id="content">
        <Navbar />
        <Grid v-bind:objects="this.users" v-bind:type="type" />
    </div>
</template>

<script>
import Navbar from '../components/Navbar.vue'
import Grid from '../components/Grid.vue'

export default {
    name: 'Users',
    methods: {
        getUsers() {
            return [
                {
                    id: 1,
                    firstname: 'Théo',
                    lastname: 'Ackermann',
                },
                {
                    id: 2,
                    firstname: 'user2',
                },
            ]
        },
    },
    components: {
        Navbar,
        Grid,
    },
    data() {
        return {
            users: this.getUsers(),
            type: 'userType',
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
