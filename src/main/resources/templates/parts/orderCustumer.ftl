<br>
<#if customer.orders?size != 0>
    <table class="table">
        <thead class="thead-dark">
        <tr>
            <th scope="col">Номер заказа</th>
            <th scope="col">Адрес доставки</th>
            <th scope="col">Сумма заказа</th>
            <th scope="col">Оплата</th>
            <th scope="col">Статус</th>
        </tr>
        </thead>
        <tbody>
        <#list customer.orders as order>
            <tr>
                <th scope="row">Z-${order.id}</th>
                <td>${order.address}</td>
                <td>${order.sum} руб.</td>
                <#if order.paid == true>
                    <td>Оплачен</td>
                <#else>
                    <td>Не оплачен</td>
                </#if>
                <#if order.completed == true>
                    <td>Получен</td>
                <#else>
                    <td>Доставка</td>
                </#if>
            </tr>
        </#list>
        </tbody>
    </table>
<#else>
    <div>Здесь будут отображаться Ваши заказы</div>
</#if>