<script>
import { Bar } from "vue-chartjs";

export default {
    extends: Bar,
    mounted() {
    this.renderChart(
      {
        labels: [
          "August",
          "September",
          "October",
          "November"
        ],
        datasets: [
          {
            label: "Working Time",
            data: [140, 154, 139, 28],
            backgroundColor: "lightgreen",
            borderColor: "lime",
            pointBackgroundColor: "black"
          }
        ]
      },
      {
        responsive: true,
        maintainAspectRatio: false,
        title: {
          display: false,
          text: "Working Time Over Month"
        }
      }
    );
  }
};
</script>