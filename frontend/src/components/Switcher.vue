<template>
    <v-container 
        style="height: 45vh; width: 300px; background-color: white"
        class="d-flex flex-column shadow rounded-lg text-center justify-space-between align-center"
    >
        <p 
            class="font-weight-bold"
            style="font-size: 28px;"
        >
            Working Time
        </p>

        <v-container>
            {{ this.isWorking ? `Working since ${this.startDate}` : `Last working session ended ${this.endDate}` }}
            <br/>
            {{ this.difference }}
        </v-container>

        <v-btn 
            fab
            large
            :style="{'background-color': this.isWorking ? 'green' : 'red'}"
            v-on:click="setWorking()"
        >
            <v-icon>
                mdi-briefcase
            </v-icon>
        </v-btn>
    </v-container>
</template>

<script>
import moment from 'moment'
import ref from 'vue'

export default {
    methods: {
        setWorking() {
            if (!this.isWorking) {
                this.startDate = moment().format('DD-MM-YYYY HH:mm:ss')
            } else {
                this.endDate = moment().format('DD-MM-YYYY HH:mm:ss')
            }
            this.$emit('isWorking', !this.isWorking)
        },
    },
    props: ['isWorking'],
    created() {
        setInterval(() => {
            if (this.isWorking) {
                console.log('startDate:', this.startDate)
                this.difference = moment.duration(moment(this.startDate).diff(moment().format('DD-MM-YYYY HH:mm:ss')))
                console.log('diff:', this.difference)
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
            endDate: 'none',
            difference: '0S'
        }
    }
}
</script>

<style>
.shadow {
    box-shadow: 0px 3px 1px -2px rgba(0, 0, 0, 0.2), 0px 2px 2px 0px rgba(0, 0, 0, 0.14), 0px 1px 5px 0px rgba(0, 0, 0, 0.12);
}
</style>
