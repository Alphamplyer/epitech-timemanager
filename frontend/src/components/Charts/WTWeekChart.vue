<script>
import { Bar } from "vue-chartjs";

export default {
    extends: Bar,
    props: ['data'],
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
            data: this.data,
            backgroundColor: [
              'rgb(5, 102, 141, 0.4)',
              'rgb(2, 128, 144, 0.4)',
              'rgb(0, 168, 150, 0.4)',
              'rgb(2, 195, 154, 0.4)',
              'rgb(200, 243, 129, 0.4)',
              'rgb(101, 135, 212, 0.4)',
              'rgb(140, 145, 237, 0.4)'
            ],
            borderColor: [
              'rgb(5, 102, 141, 1)',
              'rgb(2, 128, 144, 1)',
              'rgb(0, 168, 150, 1)',
              'rgb(2, 195, 154, 1)',
              'rgb(240, 243, 189, 1)',
              'rgb(101, 135, 212 1)',
              'rgb(140, 145, 237, 1)'
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