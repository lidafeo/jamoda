<#import "parts/commonAdmin.ftl" as c>
<#import "parts/modalAdmin.ftl" as modal>
<@c.page_admin>
    <#if error?? || message ??>
        <@modal.modal "Добавление значения атрибута к товару" />
    </#if>
    <form action="/admin/add_attribute_value" method="post">
        <div class="form-group"><label> Артикул одежды : <select name="product_article" required>
                    <#list clothes as clothesOne>
                        <option value="${clothesOne.article}">${clothesOne.article}</option>
                    </#list>
                </select></label></div>
        <div><label> Аттрибут : <select name="attribute_id" required>
                    <#list attributes as attribute>
                        <option value="${attribute.id}">${attribute.name}</option>
                    </#list>
                </select></label></div>
        <div><label> Значение : <input type="text" name="value" required/> </label></div>
        <input type="submit" value="Добавить"/>
        <input type="hidden" name="_csrf" value="${_csrf.token}">
    </form>
</@c.page_admin>