$(document).ready(function(){
    var xValues = ["Jan 2022", "Feb 2022", "Mar 2022", "Apr 2022", "May 2022", "Jun 2022", "Jul 2022", "Aug 2022", "Sep 2022", "Oct 2022", "Nov 2022", "Dec 2022"];
    var yValues = paymentOrder;
    chart1(xValues, yValues);

    var xValues1 = ["Chờ", "Đang giao", "Đã giao", "Bị hủy"];
    var yValues1 = [v1, v2, v3, v4];
    chart2(xValues1, yValues1);

    $("#loc").on("click", function(evt){
        evt.preventDefault();
        locTheoNgay($(this));
        locSaleTheoNgay($(this));
        locSaleProduct($(this));
    });

});

function locSaleProduct(link){
        const from = $( "#from" ).val();
        const to = $( "#to" ).val();

        url = contextPath + "admin/sale-product?startTime=" + from + "&endTime=" + to;
        $.ajax({
            method: "GET",
            url: url
            }).done(function(response){
                var temp = "";
                for(var i= 0; i < response.length; i++) {
                   temp += response[i] + "<br><br>";
                }
                document.getElementById("sale-product").innerHTML = temp;
                console.log('response sale product: ' + temp);
            }).fail(function(){

            });
}

function chart2(xValues1, yValues1){
    var barColors1 = [
          "red",
          "orange",
          "green",
          "blue"
        ];

        new Chart("myChart1", {
          type: "pie",
          data: {
            labels: xValues1,
            datasets: [{
              backgroundColor: barColors1,
              data: yValues1
            }]
          },
          options: {
            title: {
              display: true,
              text: "ĐƠN HÀNG"
            }
          }
        });
}

function chart1(xValues, yValues){

    new Chart("myChart", {
      type: "line",
      data: {
        labels: xValues,
        datasets: [{
          fill: false,
          lineTension: 0,
          backgroundColor: "rgba(0,0,255,1.0)",
          borderColor: "rgba(0,0,255,0.1)",
          data: yValues
        }]
      },
      options: {
        legend: {display: false},
        scales: {
          yAxes: [{ticks: {min: 0, max:5000000}}],
        }
      }
    });
}

function locTheoNgay(){
    const from = $( "#from" ).val();
    const to = $( "#to" ).val();
    let newX = [from, "Total" ,to];
    var totalSalary = 0;

    url = contextPath + "admin/report?startTime=" + from + "&endTime=" + to;
    $.ajax({
        method: "GET",
        url: url
        }).done(function(response){
            for (var i = 0; i < response.length; i++) {
                totalSalary += response[i] ;
            }
            chart1(newX, [0,totalSalary,0]);
        }).fail(function(){

        });
}

function locSaleTheoNgay(){
    const from = $( "#from" ).val();
    const to = $( "#to" ).val();

    url = contextPath + "admin/sale-report?startTime=" + from + "&endTime=" + to;
        $.ajax({
            method: "GET",
            url: url
            }).done(function(response){
                document.getElementById("ord").innerHTML = response[0];
                document.getElementById("ordCho").innerHTML = response[1];
                document.getElementById("ordDang").innerHTML = response[2];
                document.getElementById("ordDa").innerHTML = response[3];
                document.getElementById("ordHuy").innerHTML = response[4];
                console.log('yValue after response: ' + response);
            }).fail(function(){

            });
}
