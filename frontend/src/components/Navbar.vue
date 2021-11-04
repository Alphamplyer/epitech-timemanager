<template>
  <v-card
    height="100%"
    width="256"
    class="mx-auto"
    style="float: left;"
  >
    <v-navigation-drawer permanent>
      <v-list-item>
        <v-list-item-content>
          <v-list-item-title class="text-h6" style="color: #2196F3;">
            TIME MANAGER
          </v-list-item-title>
          <v-list-item-subtitle>
            username
          </v-list-item-subtitle>
        </v-list-item-content>
      </v-list-item>

      <v-divider/>

    <v-list-item>
        <v-list-item-content>
          <v-list-item>
            {{ this.workingDate }}
          </v-list-item>
          <v-list-item>
            is working
          </v-list-item>
        </v-list-item-content>
    </v-list-item>

    <v-divider/>

    <v-list-item>
        <v-list-item-content>
          <v-list-item>
            <p>DASHBOARD</p>
          </v-list-item>
          <v-list-item>
            <p v-on:click="toProfile()">PROFILE</p>
          </v-list-item>
          <v-list-item>
            <p v-on:click="toTeams()">ALL TEAMS</p>
          </v-list-item>
          <v-list-item>
            <p v-on:click="toUsers()">ALL USERS</p>
          </v-list-item>
          <v-list-item style="color: #FF4500;">
            <p v-on:click="logOut()">LOG OUT</p>
          </v-list-item>
        </v-list-item-content>
    </v-list-item>

    </v-navigation-drawer>
  </v-card>
</template>

<script>
import { ref } from 'vue'
import moment from 'moment'
export default {
    methods: {
        setWorking() {
            this.isWorking = !this.isWorking
            console.log('isWorking:', this.isWorking)
        },
        toProfile() {
            this.$router.push('/user/profile')
        },
        toTeams() {
            this.$router.push('/user/teams')
        },
        toUsers() {
            this.$router.push('/user/users')
        },
        logOut() {
            this.$router.push('/')
        }
    },
    created() {
        setInterval(() => {
            this.workingDate = moment().format('DD-MM-YYYY HH:mm:ss')
        })
    },
    setup() {
        const isWorking = ref(false)
        const workingDate = ref(moment().format('DD-MM-YYYY HH:mm:ss'))
        return {
            isWorking,
            workingDate,
        }
    },
    data: function() {
      return {
        workingDate: moment().format('DD-MM-YYYY HH:mm:ss')
      }
  },
}
</script>

<style>

.v-list-item {
  justify-content: center;
}

.v-list-item > p{
  cursor: pointer;
}

</style>