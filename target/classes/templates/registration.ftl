<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" href="/styles/login.css">
    <title> Регистрация </title>
</head>
<body>
<div class="container">
    <div class="mx-auto" style="max-width: 880px;">
        <div class="register login-block">
            <form action="/register" method="post" id="form-login">
                <div class="form-group row justify-content-end">
                    <a href="/" class="close" aria-label="Close">
                        <span aria-hidden="true" >&times;</span>
                    </a>
                </div>
                <div class="form-group row">
                    <div class="col-sm-4"></div>
                    <h1> Регистрация </h1>
                </div>
                <hr>
                <div class="form-group row">
                    <div class="col-sm-1"></div>
                    <div class="form-group row text-danger" id="err-mess">${error?if_exists}</div>
                </div>
                <div class="form-group row">
                    <label for="email" class="col-sm-4 col-form-label col-form-label-lg">Электронная почта</label>
                    <div class="col-sm-8">
                        <input type="email" name="login" class="form-control form-control-lg" id="email" placeholder="jamoda@mail.ru" required>
                    </div>
                </div>
                <div class="form-group row">
                    <label for="password1" class="col-sm-4 col-form-label col-form-label-lg">Пароль</label>
                    <div class="col-sm-8">
                        <input type="password" name="password" class="form-control form-control-lg" id="password" placeholder="password" required>
                    </div>
                </div>
                <div class="form-group row">
                    <label for="password2" class="col-sm-4 col-form-label col-form-label-lg">Повторите пароль</label>
                    <div class="col-sm-8">
                        <input type="password" name="password1" class="form-control form-control-lg" id="password1" placeholder="password" required>
                    </div>
                </div>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                <br>
                <div class="form-group row">
                    <div class="col-sm-6">
                        <a class="nav-link" href="/cabinet">Уже зарегистрирован, войти</a>
                    </div>
                    <div class="col-sm-6">
                        <button type="submit" name="submit" class="btn btn-primary btn-lg"> Зарегистрироваться </button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
<script type="text/javascript" src="/scripts/registration.js"></script>
</body>
</html>