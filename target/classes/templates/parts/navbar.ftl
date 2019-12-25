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
            <li class="nav-item" id="cart-li">
                <a class="nav-link" href="/cart" id="cart"><img src="/img/icons8-shopping-bag-32.png" width="34" alt="Корзина"> <span class="badge badge-light" id="count-in-cart"></span></a>
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