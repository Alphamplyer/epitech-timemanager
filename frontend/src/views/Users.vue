<template>
<div>
    <Navbar/>
    <div id="users">
        <div id="titleRow" class="customRow">
            <h1 class="usersTitle">All Users</h1>
        </div>
        <div id="secondrow" class="customRow">
            <div class="user" v-for="user in users" :key="user.id">
                <v-card
                    elevation="2"
                    outlined
                    style="width: 200px; height: 33%;">
                    <v-card-title style="justify-content: center;" class="cursor-pointer" v-on:click="goToUserProfile(user.id)">{{ user.firstname }} {{ user.lastname }}</v-card-title>
                    <v-row
                    style="justify-content: center;">
                        <v-card-actions>
                            <v-btn
                            elevation="2"
                            x-small>
                                Users
                            </v-btn>
                        </v-card-actions>
                        <v-card-actions>
                            <v-btn
                            elevation="2"
                            x-small
                            color="error">
                                Delete
                            </v-btn>
                        </v-card-actions>
                    </v-row>
                </v-card>
            </div>
        </div>
        <div id="thirdrow" class="customRow">
        </div>
    </div>
</div>
</template>

<script>
import Navbar from "../components/Navbar.vue"
export default {
    name: "Users",
    methods: {
        getUsers() {
            return [
                {
                    id: 1,
                    firstname: "Théo",
                    lastname: "Ackermann",
                },
                {
                    id: 2,
                    firstname: "user2",
                    lastname: "user2",
                },
            ];
        },
        goToUserProfile(id) {
            this.$router.push(`/user/${id}`);
        }
    },
    data() {
        return {
            users: this.getUsers(),
        };
    },
    components: { Navbar }
}</script>

<style scoped>

#users {
    display: grid;
    grid-template-columns: repeat(2, 1fr);
    grid-template-rows: 0.2fr 1fr 1fr;
    column-gap: 10px;
    row-gap: 10px;
    height: 100%;
    background-color: #DDDDE6;
}

.customRow {
    margin: 20px;
    box-shadow: 0px 3px 1px -2px rgba(0, 0, 0, 0.2), 0px 2px 2px 0px rgba(0, 0, 0, 0.14), 0px 1px 5px 0px rgba(0, 0, 0, 0.12);
}

.usersTitle {
    font-size: xxx-large;
    margin-left: 2%;
}

#titleRow {
    grid-column: 1 / 3;
    grid-row: 1;
    background-color: #FFFFFF;
}

#secondrow {
    grid-column: 1 / 3;
    grid-row: 2;
    background-color: #FFFFFF;
    display: inline-flex;
}

#thirdrow {
    grid-column: 1 / 3;
    grid-row: 3;
    background-color: #FFFFFF;
}

</style>