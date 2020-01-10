<#import "parts/commonAdmin.ftl" as c>
<#import "../parts/pager.ftl" as p>
<@c.page_admin "/orders.js">
    <div id="orders">
        <table class="table" id="orders-table" data-token="${_csrf.token}">
            <thead class="thead-dark">
            <tr>
                <th scope="col">Номер заказа</th>
                <th scope="col">Дата</th>
                <th scope="col">Адрес доставки</th>
                <th scope="col">Сумма заказа</th>
                <th scope="col">Статус</th>
            </tr>
            </thead>
            <tbody>
            <#list page.content as order>
                <tr>
                    <th scope="row"><a class="detail" href="#" data-number="${order.getNumberString()}">Z-${order.getNumberString()}</a></th>
                    <td><#if order.date??>${order.date}<#else>нет данных</#if></td>
                    <td>${order.address}</td>
                    <td>${order.sum} руб.</td>
                    <#if order.completed == true>
                        <td>Получен</td>
                    <#else>
                        <td>Доставка</td>
                    </#if>
                </tr>
            </#list>
            </tbody>
        </table>
        <@p.pager "/admin/list_orders?" page />
    </div>
    <div id="detail-div"></div>
</@c.page_admin>
