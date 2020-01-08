$(document).ready(function() {
    $("#profile-tab").click(function (e) {
        $("#err-mess").html("");
        $("#mess-mess").html("");
    });

    $(".detail").click(function (e) {
        e.preventDefault();
        let number = $(this).data("number");

        $("#orders-table").hide();

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
        $("#orders-table").show();
        $("#detail-div").html("");
    });
});