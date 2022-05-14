$(document).ready(function(){
    $("#applyVoucher").on("click", function(evt){
        evt.preventDefault();
        applyVoucher($(this));
    });

    updateTotal();
});

function updateTotal(){

    total = 0;
    gg = 0;

    const collection = document.getElementsByClassName("total-pr");
    for (let i = 0; i < collection.length; i++) {
      total = total + parseFloat(collection[i].innerHTML);
    }

    document.getElementById("totalPr").innerHTML = total;
    document.getElementById("salePr").innerHTML = total * 5 /100;
    document.getElementById("thue").innerHTML = total * 10 / 100;

    totalPr = document.getElementById("totalPr").innerHTML;
    dc = document.getElementById("discountPr").innerHTML;
    ship = document.getElementById("shipFee").innerHTML;
    $("#finalPr").text(parseFloat(totalPr) + parseFloat(total * 10 / 100) + parseFloat(total * 5 / 100) - parseFloat(dc) + parseFloat(ship));
}

function applyVoucher(link){
    voucherCode = document.getElementById("v-code").value;
    subTotal = document.getElementById("totalPr").innerHTML;

    url = contextPath + "voucher?voucherCode="+voucherCode+"&subTotal="+subTotal;

    $.ajax({
        method: "POST",
        url: url
        }).done(function(response){
            $("#discountPr").text(response);
            $("#v-code").text = voucherCode;
            $("#msgVoucher").text("Đã áp dụng voucher.")
            updateTotal();
        }).fail(function(){
            $("#msgVoucher").text("Voucher không khả dụng.")
        });
}