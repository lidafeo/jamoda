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
    <div class="mx-auto" style="width: 950px;">
        <div class="form-center">
            <form action="/login" method="post" id="form-login">
                <div class="form-group row">
                    <div class="col-sm-4"></div>
                    <h1> Регистрация </h1>
                </div>
                <hr>
                <div class="form-group row">
                    <label for="email" class="col-sm-4 col-form-label col-form-label-lg">Электронная почта</label>
                    <div class="col-sm-8">
                        <input type="email" name="email" class="form-control form-control-lg" id="email" placeholder="jamoda@mail.ru" required>
                    </div>
                </div>
                <div class="form-group row">
                    <label for="password1" class="col-sm-4 col-form-label col-form-label-lg">Пароль</label>
                    <div class="col-sm-8">
                        <input type="password" name="password1" class="form-control form-control-lg" id="password1" placeholder="password" required>
                    </div>
                </div>
                <div class="form-group row text-danger" id="err-mess"></div>
                <div class="form-group row">
                    <label for="password2" class="col-sm-4 col-form-label col-form-label-lg">Повторите пароль</label>
                    <div class="col-sm-8">
                        <input type="password" name="password2" class="form-control form-control-lg" id="password2" placeholder="password" required>
                    </div>
                </div>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                <div class="form-group row">
                    <div class="col-sm-2"></div>
                    <div class="col-sm-9">
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