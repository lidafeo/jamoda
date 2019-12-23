<#import "parts/commonAdmin.ftl" as c>
<#import "parts/modalAdmin.ftl" as modal>
<@c.page_admin>
    <#if error?? || message ??>
        <@modal.modal "Добавление новой группы атрибутов" />
    </#if>
    <form action="/admin/add_group" method="post">
        <div><label> Название <input type="text" name="name"/> </label></div>
        <input type="submit" value="Добавить"/>
        <input type="hidden" name="_csrf" value="${_csrf.token}">
    </form>
</@c.page_admin>