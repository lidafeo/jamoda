<#import "parts/card.ftl" as card>
    <#list clothes as clothesOne>
        <@card.card clothesOne />
        <br>
    <#else>
        Нет товаров, удовлетворяющих выбранным критериям
    </#list>