<#import "parts/commonAdmin.ftl" as c>
<#import "parts/modalAdmin.ftl" as modal>
<@c.page_admin>
    <#if error?? || message ??>
        <@modal.modal "Добавление нового атрибута" />
    </#if>
    <form action="/admin/add_attribute" method="post">
        <div><label> Название : <input type="text" name="name" required/> </label></div>
        <div class="form-group"><label> Относится к группе категорий : <select name="groupId" required>
                    <option value="-1">Нет</option>
                    <#list groups as group>
                        <option value="${group.id}">${group.name}</option>
                    </#list>
                </select></label></div>
        <div><label> Тип : <select name="type" required>
                    <option value="number">Число</option>
                    <option value="string">Строка</option>
                </select></label></div>
        <div><label> Обязательный атрибут : <input type="checkbox" name="required" checked> </label></div>
        <input type="submit" value="Добавить"/>
        <input type="hidden" name="_csrf" value="${_csrf.token}">
    </form>
</@c.page_admin>