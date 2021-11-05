<template>
    <v-container 
        style="height: 45vh; width: 300px; background-color: white"
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
            :style="{'background-color': this.isWorking ? '#FF4500' : '#4CAF50'}"
            v-on:click="setWorking()"
        >
            {{ this.isWorking ? 'Stop' : 'Start' }}
        </v-btn>
    </v-container>
</template>

<script>
import moment from 'moment'
import ref from 'vue'
import { secToDuration } from '../../lib/date'

export default {
    methods: {
        setWorking() {
            if (!this.isWorking) {
                this.startDate = moment().format('DD-MM-YYYY HH:mm:ss')
            } else {
                this.endDate = moment().format('DD-MM-YYYY HH:mm:ss')
                this.difference = 0
            }
            this.$emit('isWorking', !this.isWorking)
        },
        secToDuration,
    },
    props: ['isWorking'],
    created() {
        setInterval(() => {
            if (this.isWorking) {
                this.difference = moment(this.startDate).diff(moment().format('DD-MM-YYYY HH:mm:ss')) / -1000 // ms to s
                this.$emit('workingTime', 1)
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
            startDate: moment(),
            endDate: this.startDate,
            difference: 0
        }
    }
}
</script>

<style>
.shadow {
    box-shadow: 0px 3px 1px -2px rgba(0, 0, 0, 0.2), 0px 2px 2px 0px rgba(0, 0, 0, 0.14), 0px 1px 5px 0px rgba(0, 0, 0, 0.12);
}
</style>
