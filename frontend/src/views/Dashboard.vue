<template>
    <div class="d-flex flex-row justify-start" style="background-color: #DDDDE6">
        <NavbarVue/>
        <!-- <div id="dashboard">
            <div id="workingTime">
                workingTime
            </div>
            <div id="dailyWorkingTime">
                <DailyChartVue :chartdata="chartData" :options="chartOptions"/>
            </div>
            <div id="weeklyWorkingTime">
                weeklyWorkingTime
            </div>
        </div> -->
    </div>
</template>

<script>
import NavbarVue from "../components/Navbar.vue";
// import DailyChartVue from "../components/DailyChart.vue";
    export default {
    name: "Dashboard",
    components: { NavbarVue },
    data: () => ({
    chartdata: {
      labels: ['January', 'February'],
      datasets: [
        {
          label: 'Data One',
          backgroundColor: '#f87979',
          data: [40, 20]
        }
      ]
    },
    options: {
      responsive: true,
      maintainAspectRatio: false
    }
  }),   
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
