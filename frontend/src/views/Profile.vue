<template>
    <div>
        <Navbar/>
        <div id="profile">
            <div id="titleRow" class="customRow">
                <h1 class="userName">Gabriel.R’s Profile</h1>
            </div>
            <div id="secondRow" class="customRow">
                <h1>User</h1>
            </div>
        </div>
    </div>
</template>

<script>
import Navbar from '../components/Navbar.vue'

export default {
    components: {
        Navbar
    }
}
</script>

<style scoped>
#profile {
    display: grid;
    grid-template-columns: 1fr;
    grid-template-rows: 1fr 6fr;
    column-gap: 10px;
    row-gap: 10px;
    height: 100%;
    background-color: #dddde6;
}
.customRow {
    margin: 20px;
    box-shadow: 0px 3px 1px -2px rgba(0, 0, 0, 0.2),
    0px 2px 2px 0px rgba(0, 0, 0, 0.14), 0px 1px 5px 0px rgba(0, 0, 0, 0.12);
}
#titleRow {
    grid-column: 1 / 2;
    grid-row: 1;
    background-color: #ffffff;
    display: flex;
    align-items: center;
}
#secondRow {
    grid-column: 1 / 2;
    grid-row: 2;
    background: #ffffff;
    border-radius: 20px;
}
h1.userName{
    padding: 0.5em;
}
</style>