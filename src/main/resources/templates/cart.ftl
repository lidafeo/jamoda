<#import "parts/common.ftl" as c>
<#import "parts/productCart.ftl" as card>
<@c.page>
    <h4>ОФОРМЛЕНИЕ ЗАКАЗА</h4>
    <hr>
    <#if cart.products?size gt 0 >
        <h5>${cart.count} товара на сумму ${cart.price} руб.</h5>
        <#assign map = cart.products>
        <#assign keys = map?keys>
        <#list keys as key>
            <@card.prod map[key].clothes map[key].count map[key].size map[key].price/>
        <#else>
            <p>Корзина пуста</p>
        </#list>
        <a href="/" class="btn btn-primary btn-lg">Перейти к оформлению</a>
        <br>
    <#else>
        <p>Корзина пуста</p>
    </#if>
</@c.page>