<#import "parts/common.ftl" as c>
<#import "parts/card.ftl" as card>
<@c.page>
    <h2>Все товары</h2>
    <hr>
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