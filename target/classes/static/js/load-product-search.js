$(document).ready(function () {

    if (sortField == 'id') {
        if (sortDir == 'asc') {
            document.getElementById("op0").innerHTML = 'Cũ nhất';
        } else {
            document.getElementById("op0").innerHTML = 'Mới nhất';
        }
    } else {
        if (sortField == 'name_product') {
            if (sortDir == 'asc') {
                document.getElementById("op0").innerHTML = 'A → Z';
            } else {
                document.getElementById("op0").innerHTML = 'Z → A';
            }

        } else {
            if (sortField == 'price') {
                if (sortDir == 'asc') {
                    document.getElementById("op0").innerHTML = 'Giá thấp → cao';
                } else {
                    document.getElementById("op0").innerHTML = 'Giá cao → thấp';
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