<#import "parts/commonAdmin.ftl" as c>
<#import "parts/modalAdmin.ftl" as modal>
<@c.page_admin>
    <#if error?? || message ??>
        <@modal.modal "Добавление нового администратора" />
    </#if>
    <form action="/admin/add_user" method="post">
        <div><label> ФИО : <input type="text" name="name" required/> </label></div>
        <div><label> Логин : <input type="text" name="login" required/> </label></div>
        <div><label> Пароль : <input type="text" name="password" required/> </label></div>
        <input type="submit" value="Добавить"/>
        <input type="hidden" name="_csrf" value="${_csrf.token}">
    </form>
</@c.page_admin>