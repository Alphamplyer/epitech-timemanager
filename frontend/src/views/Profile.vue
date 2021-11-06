<template>
    <div>
        <Navbar/>
        <div id="profile">
            <div id="titleRow" class="customRow">
                <h1 class="userName">Gabriel.R’s Profile</h1>
            </div>
            <div id="secondRow" class="customRow">
                <div>
                    <v-icon>mdi-account-circle</v-icon>
                    <div class="userInfo">
                        <p>Username: Gabriel.R</p>
                        <p>Password: ########</p>
                        <p>Email: gabriel.riboldi@epitech.eu</p>
                        <p>Role: User</p>
                        <p>ID: 1234567890</p>
                    </div>
                    <div class="workingTimes">
                        <h3>--- Daily Working Time ---</h3>
                        <p>3Hours 32Mins 23Secs</p>
                        <h3>--- Weekly Working Time ---</h3>
                        <p>28Hours 54Mins 09Secs</p>
                    </div>
                </div>
                <div class="changeUser">
                    <v-icon>mdi-pencil-outline</v-icon>
                    <h3>Edit User</h3>
                    <v-icon>mdi-account</v-icon>
                    <h3>Promote User</h3>
                    <v-icon>mdi-delete</v-icon>
                    <h3>Delete User</h3>
                </div>
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