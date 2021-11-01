<template>
    <div id="barDesign">
        <div id="left">👤 User</div>

        <div id="right">
            <span style="cursor: pointer">Settings</span>

            <span
                style="font-weight: bold; color: orangered; cursor: pointer"
                v-on:click="logOut()"
            >
                Log Out
            </span>
        </div>
    </div>
</template>

<script>
export default {
    name: 'UserBar',
    methods: {
        logOut() {
            this.$router.push('/')
        }
    }
}
</script>

<style>
#barDesign {
    display: flex;
    justify-content: space-between;
    align-items: center;
    color: #dddde6;
    margin: 12px;
    padding: 12px;
    border-radius: 24px;
    background-color: #2c3e50;
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
</style>
