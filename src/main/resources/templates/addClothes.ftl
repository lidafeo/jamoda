<#import "parts/commonAdmin.ftl" as c>
<@c.page_admin>
    <h2>Добавление нового товара</h2>
    <#if error??>
        <h6><font color="red">${error?ifExists}</font></h6>
    </#if>
    <#if message??>
        <h6><font color="green">Успешно!</font></h6>
    </#if>
    <form action="/admin/add_clothes" method="post">
        <div class="form-group"><label> Категория : <select name="category" placeholder="Верхняя одежда" required >
            <#list category as categoryOne>
                <option>${categoryOne.nameRus}</option>
            </#list>
        </select></label></div>
        <div class="form-group"><label> Артикул : <input type="text" name="article" placeholder="MP002XW0RBO8" required/> </label></div>
        <div class="form-group"><label> Название : <input type="text" name="name" placeholder="Платье Love Republic" required/> </label></div>
        <div class="form-group"><label> Цена : <input type="number" name="price" min="0" placeholder="1000" required/> руб. </label></div>
        <input type="submit" value="Добавить"/>
        <input type="hidden" name="_csrf" value="${_csrf.token}">
    </form>
</@c.page_admin>