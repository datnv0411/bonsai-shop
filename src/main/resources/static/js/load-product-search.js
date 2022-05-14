$(document).ready(function () {

    if (sortField == 'id') {
        if (sortDir == 'asc') {
            document.getElementById("op0").innerHTML = 'Cũ nhất';
            $("#op2").hide();

        } else {
            document.getElementById("op0").innerHTML = 'Mới nhất';
            $("#op1").hide();
        }
    } else {
        if (sortField == 'name_product') {
            if (sortDir == 'asc') {
                document.getElementById("op0").innerHTML = 'A → Z';
                $("#op3").hide();
            } else {
                document.getElementById("op0").innerHTML = 'Z → A';
                $("#op4").hide();
            }

        } else {
            if (sortField == 'price') {
                if (sortDir == 'asc') {
                    document.getElementById("op0").innerHTML = 'Giá thấp → cao';
                    $("#op6").hide();
                } else {
                    document.getElementById("op0").innerHTML = 'Giá cao → thấp';
                    $("#op5").hide();
                }
            }
        }
    }});

function getProductSearch(){
//    var val = $("#sortingProduct").val();
//
//    url = val;
//    $.get(url, function(responseJson){
//
//    })
//    $.ajax({
//        method: "GET",
//        url : url
//    }).done(function(responseJson){
//        alert(val);
//    }).fail(function(){
//        alert("error");
//    }).always(function(){
//        alert("always executed.")
//    })
}