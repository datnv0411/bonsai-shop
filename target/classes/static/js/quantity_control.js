$(document).ready(function(){
    $(".minusButton").on("click", function(evt){
        evt.preventDefault();
        productId = $(this).attr("pid");
        qtyInput = $("#quantity" + productId);

        newQty = parseInt(qtyInput.val()) - 1;
        if(newQty > 0) qtyInput.val(newQty);
    });

    $(".plusButton").on("click", function(evt){
            evt.preventDefault();
            productId = $(this).attr("pid");
            qtyInput = $("#quantity" + productId);
            maxPr = document.getElementById("quantity"+productId).max;

            newQty = parseInt(qtyInput.val()) + 1;
            if(newQty <= maxPr) qtyInput.val(newQty);
    });
});