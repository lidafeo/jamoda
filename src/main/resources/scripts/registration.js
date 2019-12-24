$(document).ready(function() {
    $("#form-login").submit(function (e) {
        if($("#password1").val() != $("#password2").val()) {
            e.preventDefault();
            $("#err-mess").html("Пароли не совпадают");
        }
    })
});