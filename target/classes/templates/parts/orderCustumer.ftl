<br>
<#if customer.orders?size != 0>
    <div id="orders">
        <table class="table" id="orders-table" data-token="${_csrf.token}">
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
                    <th scope="row"><a class="detail" href="#" data-number="${order.getNumberString()}">Z-${order.getNumberString()}</a></th>
                    <td>${order.address}</td>
                    <td>${order.sum} руб.</td>
                    <#if order.paid == true>
                        <td>Оплачен</td>
                    <#else>
                        <td class="payment-${order.getNumberString()}">Не оплачен</td>
                    </#if>
                    <#if order.completed == true>
                        <td>Получен</td>
                    <#else>
                        <td class="deliv-${order.getNumberString()}">Доставка</td>
                    </#if>
                </tr>
            </#list>
            </tbody>
        </table>
    </div>
    <div id="detail-div"></div>
<#else>
    <div>Здесь будут отображаться Ваши заказы</div>
</#if>