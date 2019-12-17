<#import "parts/commonAdmin.ftl" as c>
<@c.page_admin>
    <h2>Добавление картинки</h2>
    <#if error??>
        <h6><font color="red">${error?ifExists}</font></h6>
    </#if>
    <#if message??>
        <h6><font color="green">Успешно!</font></h6>
    </#if>
    <form action="/admin/add_file" method="post" enctype="multipart/form-data">
        <div class="form-group"><label> Артикул : <select name="article" class="chzn-select" required>
                    <#list products as product>
                        <option value="${product.article}">${product.article}</option>
                    </#list>
                </select></label></div>
        <div><input type="file" name="file" /></div>
        <input type="submit" value="Загрузить"/>
        <input type="hidden" name="_csrf" value="${_csrf.token}">
    </form>
</@c.page_admin>