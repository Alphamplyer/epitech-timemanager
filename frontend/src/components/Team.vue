<template>
    <div id="team">
        <img id="teamPic" alt="Team" src="../assets/team.svg" />
        <h1 class="title">{{ title }}</h1>
        <h3 class= "button" v-on:click="getUsers">Users</h3>
        <h3 class="button delete" v-on:click="deleteUser">Delete</h3>
    </div>
</template>

<script>
export default {
    name: 'Team',
    methods: {
        getUsers() {
            console.log('getUsers()')
        },
        deleteUser() {
            console.log('deleteUser()')
        },
    },
    props: ['title', 'team'],
}
</script>

<style>
#team {
    display: flex;
    flex-direction: column;
    align-items: center;
    color: #2c3e50;
    width: 200px;
    height: 200px;
    border-radius: 32px;
    margin: 4px;
    background-color: #dddde6;
}

#teamPic {
    width: 90px;
}

.title {
    margin: 0;
}

.button {
    cursor: pointer;
    margin: 5px;
}

.delete {
    color: red;
}
</style>
