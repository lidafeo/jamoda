<#import "parts/common.ftl" as c>
<@c.page>
    <h2>Список одежды</h2>
    <#list clothes as clothesOne>
        <span>${clothesOne.article}</span>
        <span>${clothesOne.name}</span>
        <i>${clothesOne.price} рублей</i>
        <a href="/clothes/${clothesOne.article}">Подробнее</a>
        <br>
    <#else>
    No Clothes
    </#list>
</@c.page>