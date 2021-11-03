<template>
    <div id="content">
        <Navbar />
        <Grid v-bind:teams="this.users" />
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
                    title: 'user1',
                },
                {
                    id: 2,
                    title: 'user2',
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
