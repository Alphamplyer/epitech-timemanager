<template>
    <div class="d-flex flex-row justify-start" style="background-color: #DDDDE6">
        <NavbarVue/>
        <!-- <div id="dashboard">
            <div id="workingTime">
                workingTime
            </div>
            <div id="dailyWorkingTime">
                dailyWorkingTime
            </div>
            <div id="weeklyWorkingTime">
                weeklyWorkingTime
            </div>
        </div> -->
    </div>
</template>

<script>
import NavbarVue from "../components/Navbar.vue";
    export default {
    name: "Dashboard",
    components: { NavbarVue },
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
