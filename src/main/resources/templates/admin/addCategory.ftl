<#import "parts/commonAdmin.ftl" as c>
<#import "parts/modalAdmin.ftl" as modal>
<@c.page_admin>
    <#if error?? || message ??>
        <@modal.modal "Добавление новой категории" />
    </#if>
    <form action="/admin/add_category" method="post">
        <div><label> Название на русском : <input type="text" name="nameRus"/> </label></div>
        <div><label> Название на английском : <input type="text" name="nameEn"/> </label></div>
        <div class="form-group"><label> Родительская категория : <select name="parentId" required>
            <option value="-1">Нет</option>
            <#list categories as category>
                <option value="${category.id}">${category.nameRus}</option>
            </#list>
            </select></label></div>
        <div><label> Показывать на навигационной панели : <select name="type" required>
            <option value="main">Да</option>
             <option value="no">Нет</option>
        </select></label></div>
        <input type="submit" value="Добавить"/>
        <input type="hidden" name="_csrf" value="${_csrf.token}">
    </form>
</@c.page_admin>