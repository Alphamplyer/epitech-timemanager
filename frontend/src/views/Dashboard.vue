<template>
    <div class="d-flex flex-row" style="background-color: #DDDDE6">
        <NavbarVue :isWorking="this.isWorking" :workingTime="this.workingTime" />

        <v-container class="d-flex flex-column justify-start">
            <v-container class="d-flex flex-row">
                <Switcher @isWorking="updateIsWorking" @ :isWorking="this.isWorking" />

                <v-container
                    style="height: 45vh; width: 61%; background-color: white"
                    class="shadow rounded-lg"
                >

                </v-container>
            </v-container>

            <v-container class="d-flex flex-row">
                <v-container
                    style="height: 45vh; width: 100%; background-color: white"
                    class="shadow rounded-lg"
                >

                </v-container>
            </v-container>
        </v-container>
    </div>
</template>

<script>
import NavbarVue from "../components/Navbar.vue"
import Switcher from "../components/Switcher.vue"

    export default {
    name: "Dashboard",
    components: {
        NavbarVue,
        Switcher,
    },
    methods: {
        updateIsWorking(working) {
            this.isWorking = working
        },
        updateWorkingTime(workTime) {
            this.workingTime = workTime
        }
    },
    data() {
        return {
            workingTime: '0S',
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
