<#import "parts/commonAdmin.ftl" as c>
<#import "../parts/pager.ftl" as p>
<@c.page_admin "/orders.js">
    <div id="orders">
        <table class="table" id="orders-table" data-token="${_csrf.token}">
            <thead class="thead-dark">
            <tr>
                <th scope="col">Номер заказа</th>
                <th scope="col">Дата</th>
                <th scope="col">Имя покупателя</th>
                <th scope="col">Телефон</th>
                <th scope="col">Статус</th>
                <th scope="col">Отметка</th>
            </tr>
            </thead>
            <tbody>
            <#list page.content as order>
                <tr>
                    <th scope="row"><a class="detail" href="#" data-number="${order.getNumberString()}">Z-${order.getNumberString()}</a></th>
                    <td><#if order.date??>${order.date}<#else>нет данных</#if></td>
                    <td>${order.name}</td>
                    <td>${order.phone}</td>
                    <#if order.completed == true>
                        <td>Получен</td>
                    <#else>
                        <td class="deliv-${order.getNumberString()}">Доставка</td>
                    </#if>
                    <#if order.confirm?? && order.confirm == true>
                        <td>Подтвержден</td>
                    <#else>
                        <td class="conf-${order.getNumberString()}"><a data-id="${order.getNumberString()}" class="confirm" href="#"><span class="badge badge-pill badge-primary">подтвердить</span></a></td>
                    </#if>
                </tr>
            </#list>
            </tbody>
        </table>
        <@p.pager "/admin/list_orders?" page />
    </div>
    <div id="detail-div"></div>
</@c.page_admin>
