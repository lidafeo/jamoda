<#import "parts/commonAdmin.ftl" as c>
<@c.page_admin>
    <h2>Добавление картинки</h2>
    <#if message??>
        ${message?ifExists}
    </#if>
    <form action="/admin/add_file" method="post" enctype="multipart/form-data">
        <div><label> Артикул : <input type="text" name="article"/> </label></div>
        <div><input type="file" name="file" /></div>
        <input type="submit" value="Загрузить"/>
        <input type="hidden" name="_csrf" value="${_csrf.token}">
    </form>
</@c.page_admin>