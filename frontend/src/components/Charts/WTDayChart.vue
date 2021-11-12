<script>
import { Doughnut } from "vue-chartjs";

export default {
    extends: Doughnut,
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
            data: [5, 2],
            backgroundColor: [
              'rgb(240, 243, 189, 0.6)',
              'rgb(5, 102, 141, 0.6)',
            ],
            borderColor: [
              'rgb(240, 243, 189)',
              'rgb(5, 102, 141, 0.6)'
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