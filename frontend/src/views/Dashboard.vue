<template>
    <div class="d-flex flex-row overflow-auto" style="background-color: #DDDDE6">
        <NavbarVue />

        <v-container class="d-flex flex-column justify-space-between">
            <v-container class="d-flex flex-row">
                <Switcher />

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
import WTWeekChart from "../components/Charts/WTWeekChart.vue"
import WTMonthChart from "../components/Charts/WTMonthChart.vue"

    export default {
    name: "Dashboard",
    components: {
        NavbarVue,
        Switcher,
        WTWeekChart,
        WTMonthChart,
    },
}
</script>
