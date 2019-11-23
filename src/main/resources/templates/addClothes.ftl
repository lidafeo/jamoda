<#import "parts/commonAdmin.ftl" as c>
<@c.page_admin>
    <h2>Добавление нового товара</h2>
    <#if message??>
        ${message?ifExists}
    </#if>
    <form action="/admin/add_clothes" method="post">
        <div class="form-group"><label> Категория : <select name="category" placeholder="Верхняя одежда" required >
            <#list category as categoryOne>
                <option>${categoryOne}</option>
            </#list>
        </select></label></div>
        <div class="form-group"><label> Артикул : <input type="text" name="article" placeholder="MP002XW0RBO8" required/> </label></div>
        <div class="form-group"><label> Название : <input type="text" name="name" placeholder="Платье Love Republic" required/> </label></div>
        <div class="form-group"><label> Бренд : <input name="brand" list="brand_list" placeholder="Love Republic" required/>
            <datalist id="brand_list">
                <#list brand as brandOne>
                    <option>${brandOne}</option>
                </#list>
            </datalist>
        </label></div>
        <div class="form-group"><label> Страна производства: <input type="text" name="made_in" placeholder="Китай" required/> </label></div>
        <div class="form-group"><label> Цена : <input type="number" name="price" min="0" placeholder="1000" required/> руб. </label></div>
        <div class="form-group"><label> Пол : <select name="gender" required>
            <#list gender as genderOne>
                <option>${genderOne}</option>
            </#list>
        </select></label></div>
        <div class="form-group"><label> Сезон : <select name="season" required>
                    <#list season as seasonOne>
                        <option>${seasonOne}</option>
                    </#list>
                </select></label></div>
        <div class="form-group"><label> Состав : <textarea rows="10" cols="45" name="composition" placeholder="Хлопок: 100%" required></textarea> </label></div>
        <input type="submit" value="Добавить"/>
        <input type="hidden" name="_csrf" value="${_csrf.token}">
    </form>
</@c.page_admin>