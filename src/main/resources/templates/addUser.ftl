<#import "parts/commonAdmin.ftl" as c>
<@c.page_admin>
    <h2>Добавление нового пользователя</h2>
    <#if message??>
        ${message?ifExists}
    </#if>
    <form action="/admin/add_user" method="post">
        <div><label> User Name : <input type="text" name="name"/> </label></div>
        <div><label> User Login : <input type="text" name="login"/> </label></div>
        <div><label> User Password : <input type="text" name="password"/> </label></div>
        <input type="submit" value="Добавить"/>
        <input type="hidden" name="_csrf" value="${_csrf.token}">
    </form>
</@c.page_admin>