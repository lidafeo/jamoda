<#import "parts/common.ftl" as c>
<@c.page>
    <p>Название: <b>${clothes.name}</b></p>
    <p>Артикул: ${clothes.article}</p>
    <p>Цена: ${clothes.price} рублей</p>
    <p>Страна производства: ${clothes.made_in}</p>
    <p>Сезон: ${clothes.season}</p>
</@c.page>