$(document).ready(function() {
    $("#profile-tab").click(function (e) {
        $("#err-mess").html("");
        $("#mess-mess").html("");
    });

    $(".detail").click(function (e) {
        e.preventDefault();
        let number = $(this).data("number");

        $("#orders").hide();

        $.ajax({
            url: '/detail',
            method: 'post',
            dataType: 'html',
            data: {"number" : number,
                    "_csrf" : $("#orders-table").data("token")},
            success: function(data){
                $('#detail-div').html(data);
            },
            error: function (err) {
                location.reload();
                console.log(err);
            }
        });
    });

    $("#detail-div").on("click", "#back-order", function (e) {
        e.preventDefault();
        $("#orders").show();
        $("#detail-div").html("");
    });

    $("#orders,#detail-div").on("click", ".confirm", function (e) {
        e.preventDefault();
        //let choose = $(this);
        let id = $(this).data("id");
        $.ajax({
            url: '/confirm',
            method: 'post',
            dataType: 'json',
            data: {"id" : $(this).data("id"),
                "_csrf" : $("#orders-table").data("token")},
            success: function(data){
                if(data['message']) {
                    //choose.parent().html("Подтвержден");
                    $(".conf-" + id).html("Подтвержден");
                    //$('#conf').html("Подтвержден");
                }
            },
            error: function (err) {
                location.reload();
                console.log(err);
            }
        });
    });

    $("#detail-div").on("click", ".delivery", function (e) {
        e.preventDefault();
        //let choose = $(this);
        let id = $(this).data("id");
        $.ajax({
            url: '/confirm_delivery',
            method: 'post',
            dataType: 'json',
            data: {"id" : $(this).data("id"),
                "_csrf" : $("#orders-table").data("token")},
            success: function(data){
                if(data['message']) {
                    //choose.parent().html("Подтвержден");
                    $(".deliv-" + id).html("Получен");
                    $(".payment-" + id).html("Оплачен");
                    //$('#conf').html("Подтвержден");
                }
            },
            error: function (err) {
                location.reload();
                console.log(err);
            }
        });
    });
});