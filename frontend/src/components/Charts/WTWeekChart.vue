<script>
import { Bar } from "vue-chartjs";

export default {
    extends: Bar,
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
            backgroundColor: [
              'rgb(5, 102, 141, 0.6)',
              'rgb(2, 128, 144, 0.6)',
              'rgb(0, 168, 150, 0.6)',
              'rgb(2, 195, 154, 0.6)',
              'rgb(240, 243, 189, 0.6)',
              'rgb(201, 235, 212, 0.6)',
              'rgb(240, 245, 237, 0.6)'
            ],
            borderColor: [
              'rgb(5, 102, 141, 1)',
              'rgb(2, 128, 144, 1)',
              'rgb(0, 168, 150, 1)',
              'rgb(2, 195, 154, 1)',
              'rgb(240, 243, 189, 1)',
              'rgb(201, 235, 212, 1)',
              'rgb(240, 245, 237, 1)'
            ],
            borderWidth: 1
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