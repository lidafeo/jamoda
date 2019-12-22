<#import "parts/commonAdmin.ftl" as c>
<#import "parts/modalAdmin.ftl" as modal>
<@c.page_admin>
    <#if error?? || message ??>
        <@modal.modal "Добавление картинки к товару" />
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