$(document).ready(function() {
    $("#form-login").submit(function (e) {
        if($("#password").val() != $("#password1").val()) {
            e.preventDefault();
            $("#err-mess").html("Пароли не совпадают");
        }
    })
});