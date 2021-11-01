<script setup>
function a() {
    console.log('Delete user')
}
</script>

<template>
    <div>
        <h1>User account view</h1>
        <h1>(delete account, etc)</h1>
        <a v-on:click="a">Delete account</a>
    </div>
</template>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped></style>
