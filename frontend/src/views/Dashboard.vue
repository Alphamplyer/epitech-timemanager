<template>
    <div class="d-flex flex-row overflow-auto" style="background-color: #DDDDE6">
        <NavbarVue :isWorking="this.isWorking" :workingTime="this.workingTime" />

        <v-container class="d-flex flex-column justify-space-between">
            <v-container class="d-flex flex-row">
                <Switcher @isWorking="updateIsWorking" @workingTime="updateWorkingTime" :isWorking="this.isWorking" />

                <v-container
                    style="height: 46vh; background-color: white"
                    class="d-flex justify-center shadow rounded-lg overflow-y-auto"
                >
                    <v-container>
                        <WTMonthChart />
                    </v-container>
                </v-container>
            </v-container>

            <v-container 
                style="height: 46vh; background-color: white;"
                class="d-flex shadow rounded-lg overflow-y-auto"
            >
                <v-container>
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
// import { apiCall } from '../../lib/api.js'

    export default {
    name: "Dashboard",
    components: {
        NavbarVue,
        Switcher,
        WTWeekChart,
        WTMonthChart,
    },
    async mounted() {
    //     const res = await apiCall(`/api/users?username=${localStorage.getItem('username')}`)

    //     if (!res.ok) {
    //         console.log('Erreur lors du GET du user')
    //     } else {
    //         const result = await res.json()

    //         console.log(result)
    //     }
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
