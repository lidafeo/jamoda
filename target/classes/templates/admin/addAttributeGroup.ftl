<#import "parts/commonAdmin.ftl" as c>
<#import "parts/modalAdmin.ftl" as modal>
<@c.page_admin>
    <#if error?? || message ??>
        <@modal.modal "Добавление группы атрибутов к товару для отображения на странице товара" />
    </#if>
    <h2>Добавление группы атрибутов к товару для отображения на странице товара</h2>
    <form action="/admin/add_attribute_group" method="post">
        <div class="form-group"><label> Артикул одежды : <select name="product_article" class="chzn-select" required>
                    <#list products as clothesOne>
                        <option value="${clothesOne.article}">${clothesOne.article}</option>
                    </#list>
                </select></label></div>
        <div><label> Группа : <select name="group_id" class="chzn-select" required>
                    <#list groups as group>
                        <option value="${group.id}">${group.name}</option>
                    </#list>
                </select></label></div>
        <input type="submit" value="Добавить"/>
        <input type="hidden" name="_csrf" value="${_csrf.token}">
    </form>
</@c.page_admin>