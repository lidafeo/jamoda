<nav class="navbar main-navbar navbar-expand-lg navbar-dark bg-dark my-navbar">
    <a class="navbar-brand" href="/">Jamoda</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link" href="/">Популярные товары <span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item dropdown" id="but-dropdown">
                <div class="dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="false" aria-expanded="true">
                        Каталог
                    </a>
                </div>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/about">О нас <span class="sr-only">(current)</span></a>
            </li>
        </ul>
        <ul class="navbar-nav flex-row ml-md-auto d-none d-md-flex">
            <form class="form-inline my-2 my-lg-0" method="get" action="/search">
                <input class="form-control mr-sm-2" type="search" name="q" placeholder="Блисс" aria-label="Search">
                <button class="btn btn-outline-success my-2 my-sm-0 mr-lg-4 my-lg-2" type="submit">Поиск</button>
            </form>
            <#if customer??>
                <#if customer.login??>
                    <li class="nav-item">
                        <a class="btn btn-outline-light mr-lg-4 my-lg-2" href="/cabinet">${customer.login} </a>
                    </li>
                <#elseif customer.email??>
                    <li class="nav-item">
                        <a class="btn btn-outline-light mr-lg-4 my-lg-2" href="/cabinet">${customer.email} </a>
                    </li>
                </#if>
                <form action="/logout" class="form-inline mr-lg-4 my-lg-2" method="post">
                    <input type="submit" class="btn btn-outline-light" value="Выйти" />
                    <input type="hidden" name="_csrf" value="${_csrf.token}">
                </form>
            <#else>
                <li class="nav-item">
                    <a class="btn btn-outline-light mr-lg-4 my-lg-2" href="/cabinet">Войти </a>
                </li>
            </#if>
            <li class="nav-item">
                <a class="nav-link mr-lg-5" href="/cart" id="cart"><img src="/img/icons8-shopping-bag-32.png" width="34" alt="Корзина"> <span class="badge badge-light" id="count-in-cart"></span></a>
            </li>
        </ul>
    </div>
</nav>
<nav class="navbar navbar-expand-lg navbar-dark catalog-menu border-top bg-secondary border-secondary dropdown-item" id="catalog" >
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <#if categories?size gt 0>
                <#list categories as category>
                    <li class="nav-item">
                        <a class="nav-link" href="/filter?category=${category.nameEn}">${category.nameRus} <span class="sr-only">(current)</span></a>
                    </li>
                </#list>
            </#if>
        </ul>
    </div>
</nav>