<template>
    <div id="user">
        <img alt="user_icon" src="../assets/user.svg" />
        <h1 class="title">{{ title }}</h1>
        <h3 class="button delete" v-on:click="deleteUser">Delete</h3>
    </div>
</template>

<script>
export default {
    name: 'User',
    methods: {
        getUsers() {
            console.log('getUsers()')
        },
        deleteUser() {
            console.log('deleteUser()')
        },
    },
    props: ['title', 'user'],
}
</script>

<style>
#user {
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

img {
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
