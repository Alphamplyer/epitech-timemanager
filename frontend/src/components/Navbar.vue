<template>
    <div id="barDesign">
        <div id="left">
            <span style="cursor: pointer" v-on:click="dashboard()">
                TIME MANAGER
            </span>

            <span style="cursor: pointer" v-on:click="teams()">Teams</span>

            <span style="cursor: pointer" v-on:click="users()">Users</span>
        </div>

        <div id="right">
            <span style="cursor: pointer" v-on:click="profile()">
                👤 Gabriel
            </span>

            <span
                style="color: orangered; cursor: pointer"
                v-on:click="logOut()">
                Log Out
            </span>
        </div>
    </div>
</template>

<script>
export default {
    name: 'Navbar',
    methods: {
        dashboard() {
            this.$router.push('/user/dashboard')
        },
        teams() {
            this.$router.push('/user/teams')
        },
        users() {
            this.$router.push('/user/users')
        },
        profile() {
            this.$router.push('/user/profile')
        },
        logOut() {
            this.$router.push('/')
        },
    },
}
</script>

<style>
#barDesign {
    display: flex;
    flex-direction: row;
    justify-content: space-between;
    align-items: center;
    color: #dddde6;
    margin: 12px;
    padding: 12px;
    border-radius: 24px;
    background-color: #2c3e50;
    font-weight: bold;
}

#barDesign span + span {
    margin-left: 12px;
}

#left {
    margin-left: 4px;
    left: 0;
}

#right {
    margin-right: 4px;
    right: 0;
}

span:hover {
    filter: brightness(150%);
}
</style>
