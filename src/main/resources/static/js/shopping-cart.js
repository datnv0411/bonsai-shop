$(document).ready(function(){

    $(".minusButton").on("click", function(evt){
        evt.preventDefault();
        decreaseQuantity($(this));
    });

    $(".plusButton").on("click", function(evt){
        evt.preventDefault();
        increaseQuantity($(this));
    });

    $(".remove-pr").on("click", function(evt){
        evt.preventDefault();
        removeFromCart($(this));
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
    document.getElementById("salePr").innerHTML = total * 5 / 100;
    document.getElementById("thue").innerHTML = total * 10 / 100;

    totalPr = document.getElementById("totalPr").innerHTML;
    $("#finalPr").text(parseFloat(totalPr) + parseFloat(total * 10 / 100) + parseFloat(total * 5 / 100));
}

function decreaseQuantity(link){
    productId = link.attr("pid");
    qtyInput = $("#quantity" + productId);

    newQty = parseInt(qtyInput.val()) - 1;
    if(newQty > 0) {
        qtyInput.val(newQty);
        updateQuantity(productId, newQty);
    }
}

function increaseQuantity(link){
    productId = link.attr("pid");
    qtyInput = $("#quantity" + productId);
    maxPr = document.getElementById("quantity"+productId).max;

    newQty = parseInt(qtyInput.val()) + 1;
    if(newQty <= maxPr + newQty){
        qtyInput.val(newQty);
        updateQuantity(productId, newQty);
    }
}

function updateQuantity(productId, quantity){
    url = contextPath + "cart/update?productId="+productId+"&quantity="+quantity;

    $.ajax({
        method: "POST",
        url: url
        }).done(function(newSubtotal){
            updateSubtotal(newSubtotal, productId);
            updateTotal();
        }).fail(function(){

    });
}

function updateSubtotal(newSubtotal, productId){
    $("#subtotal"+productId).text(newSubtotal);
}

function removeFromCart(link){
    url = link.attr("href");

    const count = document.getElementsByClassName("badge");

    $.ajax({
        method: "GET",
        url: url
        }).done(function(response){
            rowNumber = link.attr("rowNumber");
            removeProduct(rowNumber);
            updateTotal();
            if(parseFloat(count[0].innerHTML) > 0){
                count[0].innerHTML = parseFloat(count[0].innerHTML) - 1;
            } else {
                count[0].innerHTML = 0;
            }

        }).fail(function(){

        });
}

function removeProduct(rowNumber){
    rowId = "row" + rowNumber;
    $("#"+rowId).remove();
}

