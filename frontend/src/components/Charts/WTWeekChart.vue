<script>
import { Line } from "vue-chartjs";

export default {
    extends: Line,
    mounted() {
    this.renderChart(
      {
        labels: [
          "Monday",
          "Tuesday",
          "Wednesday",
          "Thursday",
          "Friday",
          "Saturday",
          "Sunday"
        ],
        datasets: [
          {
            label: "Working Time",
            data: [2, 10, 5, 9, 6, 0, 2],
            backgroundColor: "#FAEBD7",
            borderColor: "orangered",
            pointBackgroundColor: "red"
          }
        ]
      },
      {
        responsive: true,
        maintainAspectRatio: false,
        title: {
          display: true,
          text: "Working Time Over Week"
        }
      }
    );
  }
};
</script>