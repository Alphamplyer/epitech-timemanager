<template>
    <div id="user">
        <img alt="user_icon" src="../assets/user.svg" />
        <h2 class="user" v-on:click="goToUser">
            {{ object.firstname }} {{ object.lastname }}
        </h2>
        <h3 class="button delete" v-on:click="deleteUser">Delete</h3>
    </div>
</template>

<script>
export default {
    name: 'User',
    methods: {
        goToUser() {
            console.log('goToUser()')
        },
        deleteUser() {
            console.log('deleteUser()')
        },
    },
    props: ['object', 'user'],
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

#user:hover {
    filter: brightness(110%);
}

img {
    width: 90px;
}

.user {
    cursor: pointer;
    margin: 0 0 40px 0;
}

.button {
    cursor: pointer;
    margin: 5px;
}

.delete {
    color: red;
}
</style>
