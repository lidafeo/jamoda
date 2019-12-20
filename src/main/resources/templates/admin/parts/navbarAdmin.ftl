<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="/admin/">Панель управления</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link" href="/">На сайт<span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/admin/add_user">Добавить администратора <span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/admin/add_clothes">Добавить товар <span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item right-nav">
                <form action="/logout" method="post">
                    <input type="submit" class="form-control mr-sm-2" value="Выйти"/>
                    <input type="hidden" name="_csrf" value="${_csrf.token}">
                </form>
            </li>
        </ul>
    </div>
</nav>