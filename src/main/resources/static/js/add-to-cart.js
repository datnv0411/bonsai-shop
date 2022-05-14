$(document).ready(function(){
    $("#addToCart").on("click", function(e){
        addToCart();
    });


    $("#addOneToCart").on("click", function(e){
        addOneToCart();
    });

});

function addOneToCart(){

    const count = document.getElementsByClassName("badge");

    url = contextPath + "cart/add?productId="+productId+"&quantity=1";

    $.ajax({
        method: "POST",
        url: url
    }).done(function(response){
        if(isNaN(response)){
            $("#msgAddToCart").text("Bạn cần đăng nhập để thực hiện thao tác này.");
        } else {
            location.href="cart";
        }
    }).fail(function(){

    })
}

function addToCart(){

    quantity = $("#quantity"+ productId).val();

    const count = document.getElementsByClassName("badge");

    url = contextPath + "cart/add?productId="+productId+"&quantity="+quantity;

    $.ajax({
        method: "POST",
        url: url
    }).done(function(response){
        if(isNaN(response)){
            $("#msgAddToCart").text("Bạn cần đăng nhập để thực hiện thao tác này.");
        } else {
            $("#msgAddToCart").text("Đã thêm sản phẩm vào giỏ hàng.");
            count[0].innerHTML = parseFloat(count[0].innerHTML) + 1;
        }
    }).fail(function(){

    })
}

