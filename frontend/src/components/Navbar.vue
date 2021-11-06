<template>
  <v-card
    height="100vh"
    width="256"
    style="float: left;"
  >
    <v-container class="font-weight-bold" permanent>
      <v-list-item class="d-flex justify-center align-center text-center">
        <v-list-item-content>
          <v-container style="color: #2196F3; font-size: 32px; overflow-wrap: break-word; word-wrap: break-word; hyphens: auto;">
            TIME MANAGER
          </v-container>

          <v-list-item-subtitle class="text-capitalize black--text">
            <v-icon color="black">mdi-account</v-icon>username
          </v-list-item-subtitle>

          <v-container>
            {{ this.now }}
          </v-container>
        </v-list-item-content>
      </v-list-item>

      <v-divider class="black"/>

      <v-list-item>
          <v-container class="text-center">
            <v-container style="font-size: 22px;">
              {{
                `Working Time ${this.secToDuration(this.workingTime)}`
              }}
            </v-container>

            <v-container
              style="font-size: 24px"
              :class="isWorking ? 'green--text' : 'red--text'"
            >
              {{ isWorking ? 'Working' : 'Not Working' }}
            </v-container>
          </v-container>
      </v-list-item>

      <v-divider class="black"/>

      <v-container class="d-flex flex-column" style="font-size: 24px;">
        <v-container
          class="cursor-pointer"
          v-on:click="toDashboard()"
        >
          <v-icon>mdi-view-dashboard</v-icon> Dashboard
        </v-container>

        <v-container
          class="cursor-pointer"
          v-on:click="toProfile()"
        >
          <v-icon>mdi-account</v-icon> Profile
        </v-container>

        <v-container
          class="cursor-pointer"
          v-on:click="toTeams()"
        >
          <v-icon>mdi-tie</v-icon> All Teams
        </v-container>

        <v-container
          class="cursor-pointer"
          v-on:click="toUsers()"
        >
          <v-icon>mdi-account-group</v-icon> All Users
        </v-container>

        <v-container
          class="red--text text-center cursor-pointer"
          v-on:click="logOut()"
        >
          Log Out
        </v-container>
      </v-container>
    </v-container>
  </v-card>
</template>

<script>
import ref from 'vue'
import moment from 'moment'
import { secToDuration } from '../../lib/date'

export default {
    methods: {
      toDashboard() {
          this.$router.push('/user/dashboard')
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
          localStorage.removeItem('user')
          this.$router.push('/')
      },
      secToDuration,
    },
    created() {
        setInterval(() => {
            this.now = moment().format('HH:mm:ss')
        }, 1000)
    },
    setup() {
        const now = ref(moment().format('HH:mm:ss'))

        return {
            now,
        }
    },
    props: ['isWorking', 'workingTime'],
    data: function() {
      return {
        now: moment().format('HH:mm:ss')
      }
  },
}
</script>

<style>

.v-list-item {
  justify-content: center;
}

.cursor-pointer {
  cursor: pointer;
}

</style>