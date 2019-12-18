<#import "parts/common.ftl" as c>
<#import "parts/productCart.ftl" as card>
<@c.page>
    <div class="container main-clothes">
        <h1>Корзина</h1>
        <hr>
        <#if cart.products?size gt 0 >
            <h4>${cart.count} товар(а) на сумму ${cart.price} руб.</h4>
            <br>
            <#assign map = cart.products>
            <#assign keys = map?keys>
            <div class="card-deck">
                <#list keys as key>
                    <@card.prod map[key].clothes map[key].count map[key].size map[key].price/>
                <#else>
                    <p>Корзина пуста</p>
                </#list>
            </div>
            <div class="row">
                <div class="col-md-5">
                    <a href="/order" class="btn btn-primary btn-lg">Перейти к оформлению</a>
                </div>
                <div class="col-md-4">
                    <a href="/cart/clean" class="btn btn-primary btn-lg">Очистить корзину</a>
                </div>
            </div>
            <br>
        <#else>
            <p>Корзина пуста</p>
        </#if>
    </div>
</@c.page>