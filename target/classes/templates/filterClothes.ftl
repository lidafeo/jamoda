<#import "parts/card.ftl" as card>
<#import "parts/pager.ftl" as p>
<div id="filter-clothes" class="card-deck">
    <#list page.content as clothesOne>
        <@card.card clothesOne />
        <br>
    <#else>
        Нет товаров, удовлетворяющих выбранным критериям
    </#list>
</div>
<@p.pager url page />
<br>
