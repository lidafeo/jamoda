<#import "productCart.ftl" as p>
<div><a href="#" id="back-order">Вернуться к списку заказов</a></div>
<#if order??>
    <br><h3>Информация о заказе № Z-${order.id}</h3><br>
    <table class="table" style="max-width: 700px">
        <tbody>
        <tr>
            <th scope="row">Имя покупателя</th>
            <td>${order.name}</td>
        </tr>
        <tr>
            <th scope="row">Телефон покупателя</th>
            <td>${order.phone}</td>
        </tr>
        <tr>
            <th scope="row">Электронная почта покупателя</th>
            <td>${order.email}</td>
        </tr>
        <tr>
            <th scope="row">Адрес доставки</th>
            <td>${order.address}</td>
        </tr>
        <tr>
            <th scope="row">Сумма заказа</th>
            <td>${order.sum} руб.</td>
        </tr>
        <tr>
            <th scope="row">Комментарий к заказу</th>
            <td>${order.comment}</td>
        </tr>
        </tbody>
    </table>

    <br><h2>Информация о товарах заказа</h2><br>
    <div class="card-deck">
        <#list order.products as product>
            <@p.prod product.clothes product.count product.size product.price />
        </#list>
    </div>
    <br>
    <div><a href="#" id="back-order">Вернуться к списку заказов</a></div><br>
<#else>
    <div>Нет данных</div>
</#if>