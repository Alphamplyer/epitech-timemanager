<template>
    <div id="switcher" v-bind:style="this.style">
        <h1 id="title">
            {{ this.title }}
        </h1>

        <h3>{{ workingDate }}</h3>

        <button
            id="workingButton"
            v-on:click="this.setWorking()"
            :style="{ 'background-color': this.isWorking ? 'red' : 'green' }" />
    </div>
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
    },
    created() {
        setInterval(() => {
            this.workingDate = moment().format('DD-MM-YYYY HH:mm:ss')
        })
    },
    props: ['title', 'style'],
    setup() {
        const isWorking = ref(false)
        const workingDate = ref(moment().format('DD-MM-YYYY HH:mm:ss'))

        return {
            isWorking,
            workingDate,
        }
    },
}
</script>

<style>
#switcher {
    display: flex;
    flex-direction: column;
    flex-flow: column;
    width: 50%;
    height: 40vh;
    justify-content: center;
    align-items: center;
    margin: 6px;
    margin-inline: 12px;
    border: 4mm;
    border-width: 0.5em;
    color: #dddde6;
    border-radius: 48px;
    border-color: #2c3e50;
    background-color: #2c3e50;
    padding: 6px;
    justify-content: space-between;
}

#title {
    justify-content: start;
    background-color: #40576d;
    margin-top: 4px;
    padding: 8px;
    padding-inline: 20px;
    border-radius: 32px;
    color: orange;
    font-size: 24px;
}

#workingButton {
    width: 60px;
    height: 60px;
    border-radius: 9999px;
    margin-bottom: 4px;
}
</style>
