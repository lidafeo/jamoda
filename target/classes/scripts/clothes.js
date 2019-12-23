let product = cartJS.getProductInCart($("#article").attr("value"));
if(product != {}) {
    updateSizeAtPage();
    /*
    $("#size option").each(function(index) {
        if(+$(this).data("count") - +product[$(this).attr("value")] <= 0) {
            $(this).attr("disabled", true);
            $(this).addClass("disable-option");
        }
    });
     */
}
