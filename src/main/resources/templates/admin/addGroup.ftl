<#import "parts/commonAdmin.ftl" as c>
<@c.page_admin>
    <h2>Добавление группы категорий</h2>
    <#if error??>
        <h6><font color="red">${error?ifExists}</font></h6>
    </#if>
    <#if message??>
        <h6><font color="green">Успешно!</font></h6>
    </#if>
    <form action="/admin/add_group" method="post">
        <div><label> Название <input type="text" name="name"/> </label></div>
        <input type="submit" value="Добавить"/>
        <input type="hidden" name="_csrf" value="${_csrf.token}">
    </form>
</@c.page_admin>