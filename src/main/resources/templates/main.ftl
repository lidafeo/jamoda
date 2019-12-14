<#import "parts/common.ftl" as c>
<#import "parts/card.ftl" as card>
<@c.page>
    <div class="card-deck" id="clothes_div">
        <#list clothes as clothesOne>
            <@card.card clothesOne />
            <br>
        <#else>
        Здесь скоро будут товары
        </#list>
    </div>
    <a href="/admin">Admin</a>
</@c.page>