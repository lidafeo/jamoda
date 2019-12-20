<#import "parts/commonAdmin.ftl" as c>
<@c.page_admin>
    <h2>Добавление нового пользователя</h2>
    <#if error??>
        <h6><font color="red">${error?ifExists}</font></h6>
    </#if>
    <#if message??>
        <h6><font color="green">Успешно!</font></h6>
    </#if>
    <form action="/admin/add_user" method="post">
        <div><label> ФИО : <input type="text" name="name" required/> </label></div>
        <div><label> Логин : <input type="text" name="login" required/> </label></div>
        <div><label> Пароль : <input type="text" name="password" required/> </label></div>
        <input type="submit" value="Добавить"/>
        <input type="hidden" name="_csrf" value="${_csrf.token}">
    </form>
</@c.page_admin>