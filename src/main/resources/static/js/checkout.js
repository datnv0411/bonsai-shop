$(document).ready(function(){

    $("#shippingOption1").on("click", function(){
        document.getElementById("shipFee").innerHTML = 0;
        updateTotal();
    });

    $("#shippingOption2").on("click", function(){
        document.getElementById("shipFee").innerHTML = 25000;
        updateTotal();
    });

    $("#shippingOption3").on("click", function(){
        document.getElementById("shipFee").innerHTML = 50000;
        updateTotal();
    });

    $('.addressCl input[type="radio"]').click(function(){
        choseAddress($(this));
    });

    $("#datHang").on("click", function(){
        datHang($(this));
    });

    checkAddressChecked();
});

function checkAddressChecked(){
    const collection = document.getElementsByClassName("addressShip");
    if(collection != null){
        collection[0].setAttribute("checked","checked");
    }
}

function returnHome(){
    url = contextPath + "list-product";

    $.ajax({
        method: "GET",
        url: url
        }).done(function(response){

        }).fail(function(){

        });
}

function datHang(link){

    var selected_Id = $('input[name="addressShip"]:checked').attr('id');
    voucherCode = document.getElementById("v-code").value;
    var paymentName = $('input[name="payment-method"]:checked').attr('value');
    var total = document.getElementById("finalPr").innerHTML;

    if(typeof selected_Id === 'undefined'){
        selected_Id = "null";
    }

    url = contextPath + "save-order?addressId="+selected_Id+"&voucherCode="+voucherCode+"&paymentName="+paymentName+"̃&totalPrice="+total;
    $.ajax({
        method: "POST",
        url: url
        }).done(function(response){
            if(response == "localhost:8080/order?page=1&sortField=id&sortDir=des"){
                location.href = "http://" + response;
            } else{
                if(response == "Địa chỉ nhận hàng không được để trống."){
                    document.getElementById("msg").innerHTML = response;
                } else {
                    location.href = response;
                }
            }
        }).fail(function(){

        });
}

function redirectPayment(link){
    url = contextPath + link;
    $.ajax({
            method: "POST",
            url: url
            }).done(function(){

            }).fail(function(){

            });

}

function choseAddress(link){
    addressId = link.attr("id");

    var selectedOption = $("input:radio[name=addressShip]:checked").val()
}