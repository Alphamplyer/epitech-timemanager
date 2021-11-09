<template>
    <div>
        <Navbar/>
        <div id="profile">
            <div id="titleRow" class="customRow rounded-lg">
                <h1 class="userName">{{ account.username }}’s Profile</h1>
            </div>
            <v-container id="secondRow" class="customRow">
                <div class="secondRowLeft">
                    <v-icon class="userIcon">mdi-account-circle</v-icon>
                    <div class="changeUser">
                        <h3 style="display: flex"><v-icon>mdi-pencil-outline</v-icon><span><Edit :object="token.user"/></span></h3>
                        <h3 style="display: flex"><v-icon>mdi-account</v-icon><span><Promote :object="token.user"/></span></h3>
                        <h3 style="display: flex"><v-icon>mdi-delete</v-icon><span><Delete :object="token.user" :type="'userType'"/></span></h3>
                    </div>
                </div>
                <div class="secondRowRight">
                    <div class="userInfo text-capitalize">
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
                </div>
            </v-container>
        </div>
    </div>
</template>

<script>
import Promote from '../components/Dialogs/Promote.vue'
import Edit from '../components/Dialogs/Edit.vue'
import Navbar from '../components/Navbar.vue'
import Delete from '../components/Dialogs/Delete.vue'

export default {
    components: {
        Navbar,
        Edit,
        Promote,
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
    display: grid;
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
    background-color: #ffffff;
    display: flex;
    align-items: center;
}
#secondRow {
    background: #ffffff;
    border-radius: 20px;
    display: flex;
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