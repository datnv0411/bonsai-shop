$(document).ready(function(){
    setSizeCart();
});

function setSizeCart(){

    const count = document.getElementsByClassName("badge");

    url = "/count";

    $.ajax({
        method: "POST",
        url: url
    }).done(function(response){
        if(isNaN(parseFloat(response))){
            count[0].innerHTML = 0;
        } else {
            count[0].innerHTML = response;
        }
    }).fail(function(){

    })
}
