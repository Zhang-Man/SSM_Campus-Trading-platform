




var ctx = document.getElementById('myChart3-light').getContext('2d');
var myChart = new Chart(ctx, {
    type: 'bar',
    data: {
        labels: ["A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q"],
        datasets: [{
            label: '# of Votes',
            data: [58, 80, 44, 76, 54, 50, 45, 90, 57, 48, 54, 49, 63, 77, 67, 83, 95],
            backgroundColor: [
                'rgba(21, 114, 232, 0.64)',
                'rgba(21, 114, 232, 0.64)',
                'rgba(21, 114, 232, 0.64)',
                'rgba(21, 114, 232, 0.64)',
                'rgba(21, 114, 232, 0.64)',
                'rgba(21, 114, 232, 0.64)',
                'rgba(21, 114, 232, 0.64)',
                'rgba(21, 114, 232, 0.64)',
                'rgba(21, 114, 232, 0.64)',
                'rgba(21, 114, 232, 0.64)',
                'rgba(21, 114, 232, 0.64)',
                'rgba(21, 114, 232, 0.64)',
                'rgba(21, 114, 232, 0.64)',
                'rgba(21, 114, 232, 0.64)',
                'rgba(21, 114, 232, 0.64)',
                'rgba(21, 114, 232, 0.64)',
                'rgba(21, 114, 232, 0.64)'

            ],
            //borderColor: [
            //    'rgba(255,99,132,1)',
            //    'rgba(54, 162, 235, 1)',
            //    'rgba(255, 206, 86, 1)',
            //    'rgba(75, 192, 192, 1)',
            //    'rgba(153, 102, 255, 1)',
            //    'rgba(255, 159, 64, 1)'
            //],
            borderWidth: 0
        }]
    },
    options: {
        maintainAspectRatio: false,
        legend: {
            display: false
        },
        scales: {
            xAxes: [{
                display: false
            }],
            yAxes: [{
                display: false
            }]
        }

    }
});




