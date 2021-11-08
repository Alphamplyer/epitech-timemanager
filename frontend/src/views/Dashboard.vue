<template>
    <div class="d-flex flex-row" style="background-color: #DDDDE6">
        <NavbarVue :isWorking="this.isWorking" :workingTime="this.workingTime" />

        <v-container class="d-flex flex-column">
            <v-container class="d-flex flex-row p-4">
                <Switcher @isWorking="updateIsWorking" @workingTime="updateWorkingTime" :isWorking="this.isWorking" />

                <v-container
                    style="height: 45vh; width: 61%; background-color: white"
                    class="d-flex justify-center shadow rounded-lg"
                >
                    <WTMonthChart />
                </v-container>
            </v-container>

            <v-container class="d-flex flex-row">
                <v-container
                    style="height: 45vh; background-color: white"
                    class="d-flex justify-center shadow rounded-lg"
                >
                    <WTWeekChart />
                </v-container>
            </v-container>
        </v-container>
    </div>
</template>

<script>
import NavbarVue from "../components/Navbar.vue"
import Switcher from "../components/Switcher.vue"
import WTWeekChart from "../components/WTWeekChart.vue"
import WTMonthChart from "../components/WTMonthChart.vue"
import { apiCall } from '../../lib/api.js'

    export default {
    name: "Dashboard",
    components: {
        NavbarVue,
        Switcher,
        WTWeekChart,
        WTMonthChart,
    },
    async mounted() {
        const res = await apiCall(`/api/users?username=${localStorage.getItem('username')}`)

        if (!res.ok) {
            console.log('Erreur lors du GET du user')
        } else {
            const result = await res.json()

            console.log(result)
        }
    },
    methods: {
        updateIsWorking(working) {
            this.isWorking = working
        },
        updateWorkingTime(workTime) {
            this.workingTime += workTime
        }
    },
    data() {
        return {
            workingTime: 0,
            isWorking: false,
        }
    },
}
</script>

<style scoped>

#dashboard {
    display: grid;
    grid-template-columns: repeat(3, 1fr);
    grid-template-rows: repeat(2, 1fr);
    grid-column-gap: 10px;
    grid-row-gap: 10px;
    height: 100%;
}

#workingTime {
    grid-column: 1 / 2;
    grid-row: 1;
    background-color: red;
    border-radius: 12px;
}

#dailyWorkingTime {
    grid-column: 2 / 4;
    grid-row: 1;
    background-color: blue;
    border-radius: 12px;
}

#weeklyWorkingTime {
    grid-column: 1 / 4;
    grid-row: 2;
    background-color: green;
    border-radius: 12px;
}

</style>
