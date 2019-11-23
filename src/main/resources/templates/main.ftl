<#import "parts/common.ftl" as c>
<#import "parts/card.ftl" as card>
<@c.page>
    <h2>Список одежды</h2>
    <div class="card-deck">
        <#list clothes as clothesOne>
            <@card.card clothesOne />
            <br>
        <#else>
        No Clothes
        </#list>
    </div>
    <a href="/admin">Admin</a>
</@c.page>