<template>
    <v-container 
        style="height: 46vh; width: 300px; background-color: white"
        class="d-flex flex-column shadow py-8 rounded-lg text-center justify-space-between align-center"
    >
        <p 
            class="font-weight-bold"
            style="font-size: 34px;"
        >
            Working Time
        </p>

        <v-container 
            class="font-weight-bold font-italic"
            style="font-size: 48px"
        >
            {{ `${this.secToDuration(this.difference)}` }}
        </v-container>

        <v-btn 
            large
            style="font-size: 32px"
            class="shadow white--text text-capitalize font-weight-bold"
            :style="{'background-color': this.$store.state.clock.enabled ? '#FF4500' : '#4CAF50'}"
            v-on:click="setWorking()"
        >
            {{ this.$store.state.clock.enabled ? 'Stop' : 'Start' }}
        </v-btn>
    </v-container>
</template>

<script>
import moment from 'moment'
import ref from 'vue'
import { apiCall } from '../../lib/api'
import { 
    secToDuration, 
    addSecondsToDuration, 
    addDurationToDate
} from '../../lib/date'

export default {
    methods: {
        setWorking() {
            if (!this.$store.state.clock.enabled) {
                this.$store.state.clock.enabled = true
                this.$store.state.clock.started_at = moment().format('DD-MM-YYYY HH:mm:ss')
            } else {
                this.$store.state.clock.enabled = false
                this.$store.commit('updateClockTime', addSecondsToDuration(this.$store.state.clock.time, this.difference))
                this.endDate = moment().format('DD-MM-YYYY HH:mm:ss')
                this.difference = 0
                this.addWorkingTimeToDB(addDurationToDate(this.$store.state.clock.started_at, this.difference))


            }
        },
        async addWorkingTimeToDB(endDate) {
            const res = await apiCall({
                route:'/api/workingtimes',
                method: 'POST',
                body: JSON.stringify({
                    startDate: this.$store.state.clock.started_at,
                    endDate,
                })
            })

            console.log('res:', res)
        },
        secToDuration,
    },
    created() {
        setInterval(() => {
            if (this.$store.state.clock.enabled) {
                this.difference = moment(this.$store.state.clock.started_at).diff(moment().format('DD-MM-YYYY HH:mm:ss')) / - 1000 // ms to s
            }
        }, 1000)
    },
    setup() {
        const difference = ref('')

        return {
            difference
        }
    },
    data() {
        return {
            difference: this.$store.state.clock.enabled ? moment(this.$store.state.clock.started_at).diff(moment().format('DD-MM-YYYY HH:mm:ss')) / - 1000 : '00:00:00',
            endDate: this.$store.state.clock.started_at
        }
    }
}
</script>

<style>
.shadow {
    box-shadow: 0px 3px 1px -2px rgba(0, 0, 0, 0.2), 0px 2px 2px 0px rgba(0, 0, 0, 0.14), 0px 1px 5px 0px rgba(0, 0, 0, 0.12);
}
</style>
