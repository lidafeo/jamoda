<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" href="/styles/login.css">
    <title>Вход в личный кабинет </title>
</head>
<body>
    <div class="container">
        <div class="mx-auto" style="max-width: 740px;">
            <div class="form-center">
                <form action="/login" method="post">
                    <div class="form-group row">
                        <div class="col-sm-1"></div>
                        <h2>Вход в личный кабинет</h2>
                    </div>
                    <hr>
                    <div class="form-group row">
                        <div class="col-sm-1"></div>
                        <div id="err-mess" class="text-danger"></div>
                    </div>
                    <div class="form-group row">
                        <label for="login" class="col-sm-3 col-form-label col-form-label-lg">Логин</label>
                        <div class="col-sm-9">
                            <input type="text" name="username" class="form-control form-control-lg" id="login" placeholder="Login" required>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="inputPassword3" class="col-sm-3 col-form-label col-form-label-lg">Пароль</label>
                        <div class="col-sm-9">
                            <input type="password" name="password" class="form-control form-control-lg" id="inputPassword3" placeholder="Password" required>
                        </div>
                    </div>
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                    <br>
                    <div class="form-group row">
                        <div class="col-sm-1"></div>
                        <div class="col-sm-4">
                            <button type="submit" name="submit" id="go_in" class="btn btn-primary btn-lg">  Войти  </button>
                        </div>
                        <div class="col-sm-6">
                            <a href="/register" class="btn btn-primary btn-lg">Зарегистрироваться</a>
                        </div>
                        <div class="col-sm-1"></div>
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
    <script type="text/javascript" src="/scripts/login.js"></script>
</body>
</html>