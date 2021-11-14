<script>
import { Doughnut } from "vue-chartjs";

export default {
    extends: Doughnut,
    props: ['data'],
    mounted() {
    this.renderChart(
      {
        labels: [
          "Working Time",
          "Remaining Time",
        ],
        datasets: [
          {
            label: "Working Time",
            data: this.data,
            backgroundColor: [
              'rgb(2, 195, 154, 0.4)',
              'rgb(5, 102, 141, 0.4)',
            ],
            borderColor: [
              'rgb(2, 195, 154)',
              'rgb(5, 102, 141, 1)'
            ],
            borderWidth: 1,
            hoverBorderColor: [
              'rgb(250, 235, 215)',
              'rgb(100, 149, 237)'
            ],
            pointBackgroundColor: "black",
          }
        ]
      },
      {
        responsive: true,
        maintainAspectRatio: false,
        title: {
          display: true,
          text: "Working Time Of The Day"
        }
      }
    );
  }
};
</script>