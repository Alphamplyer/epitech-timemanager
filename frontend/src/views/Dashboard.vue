<template>
    <div class="d-flex flex-row overflow-auto" style="background-color: #DDDDE6">
        <NavbarVue />

        <v-container class="d-flex flex-column justify-space-between m-4">
            <div class="d-flex flex-row pb-4">
                <Switcher />

                <v-responsive
                    v-if="this.wtOfTheDay"
                    style="height: 46vh; background-color: white"
                    class="d-flex justify-center shadow rounded-lg overflow-y-auto"
                >
                    <WTDayChart :data="this.wtOfTheDay" />
                </v-responsive>
            </div>

            <v-responsive 
                v-if="this.wtOfTheWeek"
                style="height: 46vh; width: 100%; background-color: white;"
                class="d-flex shadow rounded-lg overflow-y-auto"
            >
                <WTWeekChart :data="this.wtOfTheWeek" />
            </v-responsive>
        </v-container>
    </div>
</template>

<script>
import NavbarVue from "../components/Navbar.vue"
import Switcher from "../components/Switcher.vue"
import WTWeekChart from "../components/Charts/WTWeekChart.vue"
import WTDayChart from "../components/Charts/WTDayChart.vue"
import { dateOfSameMoment, dayOfWT, weekOfWT } from '../../lib/date'
import { apiCall } from '../../lib/api'

export default {
    name: "Dashboard",
    components: {
        NavbarVue,
        Switcher,
        WTWeekChart,
        WTDayChart,
    },
    async beforeCreate() {
        const wtCall = await apiCall({
            route: `/api/workingtimes/users/${this.$store.state.user.id}`,
        })

        if (!wtCall.ok) {
            console.log("Could not get the user's working time.")
        } else {
            const result = await wtCall.json()

            this.wtOfTheDay = dayOfWT(dateOfSameMoment(result, 'day'))
            this.wtOfTheWeek = weekOfWT(dateOfSameMoment(result, 'week'))            
            console.log('on set le wtOftheWeek:', this.wtOfTheWeek);
        }
    },
    data() {
        return {
            wtOfTheWeek: undefined,
            wtOfTheDay: undefined
        }
    }
}
</script>
