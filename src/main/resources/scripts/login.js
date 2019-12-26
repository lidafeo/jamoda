$(document).ready(function() {
    if (window.location.href.indexOf('err') != -1) {
        $("#err-mess").html("Введен неверный логин или пароль");
    }
});