<template>
    <div>
        <Navbar/>
        <div id="profile">
            <v-container id="titleRow" class="customRow rounded-lg">
                <h1 class="userName">{{ account.username }}’s Profile</h1>
            </v-container>
            <v-container id="secondRow" class="rounded-lg customRow">
                <v-row wrap>
                    <v-col class="secondRowLeft align-md-center align-sm-center">
                        <v-icon class="userIcon d-none d-md-flex">mdi-account-circle</v-icon>
                        <div class="changeUser">
                            <h3 style="display: flex"><v-icon>mdi-pencil-outline</v-icon><span><Edit :object="account"/></span></h3>
                            <h3 v-if="this.account.role !== 'GLOBAL_MANAGER'" style="display: flex"><v-icon>mdi-delete</v-icon><span><Delete :object="account" :type="'userType'"/></span></h3>
                        </div>
                    </v-col>
                    <v-row wrap class="secondRowRight">
                        <div class="userInfo">
                            <p><span>Username:</span> {{ account.username }}</p>
                            <p><span>Password:</span> ########</p>
                            <p><span>Email:</span> {{ account.email }}</p>
                            <p><span>Role:</span> {{ account.role }} </p>
                            <p><span>ID:</span> {{ account.id }} </p>
                        </div>
                        <div class="workingTimes">
                            <h3>--- Daily Working Time ---</h3>
                            <p>3Hours 32Mins 23Secs</p>
                            <h3>--- Weekly Working Time ---</h3>
                            <p>28Hours 54Mins 09Secs</p>
                        </div>
                    </v-row>
                </v-row>
            </v-container>
        </div>
    </div>
</template>

<script>
import Edit from '../components/Dialogs/Edit.vue'
import Navbar from '../components/Navbar.vue'
import Delete from '../components/Dialogs/Delete.vue'

export default {
    components: {
        Navbar,
        Edit,
        Delete
    },
    data() {
        return {
            account: this.$store.state.user, 
        }
    },
}
</script>

<style scoped>
#profile {
    display: flex;
    flex-direction: column;
    align-items: center;
    height: 100%;
    background-color: #dddde6;
}
.customRow {
    margin: 20px;
    box-shadow: 0px 3px 1px -2px rgba(0, 0, 0, 0.2),
    0px 2px 2px 0px rgba(0, 0, 0, 0.14), 0px 1px 5px 0px rgba(0, 0, 0, 0.12);
}
#titleRow {
    background-color: #ffffff;
    display: flex;
    align-items: center;
    width: 95%;
    height: 15%;
}
#secondRow {
    background: #ffffff;
    width: 95%;
    height: 85%;
}
.secondRowLeft {
    display: flex;
    flex-direction: column;
    justify-content: space-between;
    margin: 1em;
}
.secondRowRight {
    margin: 2em;
    display: flex;
    align-items: flex-start;
    justify-content: space-around;
}
h1.userName{
    padding: 0.5em;
}
.userIcon{
    font-size: 20em;
}
.userInfo,.workingTimes {
    padding: 10px;
    font-size: 24px;
}
.changeUser {
    padding: 2em;
}
.changeUser h3 {
    margin-top: 1em;
}
.changeUser span {
    padding-left: 0.5em;
}
.userInfo span {
    font-weight: bold;
}
</style>