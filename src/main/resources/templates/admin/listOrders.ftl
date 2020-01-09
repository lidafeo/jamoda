<#import "parts/commonAdmin.ftl" as c>
<#import "../parts/pager.ftl" as p>
<@c.page_admin>
    <div>
        <#list page.content as order>
            <div>${order.id}</div>
            <div><#if order.date??>${order.date}<#else>""</#if></div>
            <br>
        <#else>
            <div class="ml-3">Нет данных</div>
        </#list>
    </div>
    <@p.pager "/admin/list_orders?" page />
</@c.page_admin>
