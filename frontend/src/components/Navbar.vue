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
            <v-icon color="black">mdi-account</v-icon>{{ this.account.username }}
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
                `Working Time ${this.workingTime}`
              }}
            </v-container>

            <v-container
              style="font-size: 24px"
              :class="this.$store.state.clock.enabled ? 'green--text' : 'red--text'"
            >
              {{ this.$store.state.clock.enabled ? 'Working' : 'Not Working' }}
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
          v-if="this.account.role !== 'EMPLOYEE'"
        >
          <v-icon>mdi-tie</v-icon> All Teams
        </v-container>

        <v-container
          class="cursor-pointer"
          v-on:click="toUsers()"
          v-if="this.account.role === 'GLOBAL_MANAGER'"
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
import { addSecondsToDuration } from '../../lib/date'

export default {
    methods: {
      toDashboard() {
          this.$router.push(`/user/${this.account.id}/dashboard`).catch(()=>{})
      },
      toProfile() {
          this.$router.push(`/user/${this.account.id}/profile`).catch(()=>{})
      },
      toTeams() {
          this.$router.push(`/user/${this.account.id}/teams`).catch(()=>{})
      },
      toUsers() {
          this.$router.push(`/user/${this.account.id}/users`).catch(()=>{})
      },
      logOut() {
          localStorage.removeItem('vuex')
          this.$router.push('/')
      },
    },
    created() {
        setInterval(() => {
            this.now = moment().format('HH:mm:ss')
            if (this.$store.state.clock.enabled) {
                this.workingTime = addSecondsToDuration(this.workingTime, 1)
            }
        }, 1000)
    },
    setup() {
        const now = ref(moment().format('HH:mm:ss'))

        return {
            now,
        }
    },    
    data() {
      return {
        account: this.$store.state.user,
        now: moment().format('HH:mm:ss'),
        workingTime: this.$store.state.clock.enabled ?
         addSecondsToDuration(this.$store.state.clock.time, moment(this.$store.state.clock.started_at).diff(moment().format('DD-MM-YYYY HH:mm:ss')) / - 1000)
         : this.$store.state.clock.time
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