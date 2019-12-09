<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="/">Jamoda</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item">
                    <a class="nav-link" href="/">Home <span class="sr-only">(current)</span></a>
                </li>
                <#list categories as category>
                    <li class="nav-item">
                        <a class="nav-link" href="/">${category.nameRus} <span class="sr-only">(current)</span></a>
                    </li>
                </#list>
                <li class="nav-item">
                    <a class="nav-link" href="/about">О нас <span class="sr-only">(current)</span></a>
                </li>
                <li class="nav-item" id="cart">
                    <a class="nav-link" href="/cart"><img src="/img/cart.ico" width="34" alt="Корзина"> <b> Корзина
                        <#if cartSession?size gt 0>(${cartSession?size})</#if></b></a>
                </li>
            </ul>
        </div>
</nav>