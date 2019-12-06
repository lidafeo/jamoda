<#import "parts/commonAdmin.ftl" as c>
<@c.page_admin>
    <h2>Добавление категории</h2>
    <#if error??>
        <h6><font color="red">${error?ifExists}</font></h6>
    </#if>
    <#if message??>
        <h6><font color="green">Успешно!</font></h6>
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