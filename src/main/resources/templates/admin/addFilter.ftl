<#import "parts/commonAdmin.ftl" as c>
<#import "parts/modalAdmin.ftl" as modal>
<@c.page_admin "/addFilter.js">
    <#if error?? || message ??>
        <@modal.modal "Добавление нового фильтра" />
    </#if>
    <div class="mx-auto" style="width: 1000px;">
        <div class="form-center">
            <form action="/admin/add_filter" method="post">
                <div class="form-group row">
                    <h4>Добавление фильтра</h4>
                </div>
                <div class="form-group row">
                    <label for="attribute_id" class="col-sm-3 col-form-label">Фильтруемый атрибут</label>
                    <div class="col-sm-9">
                        <select name="attributeId" class="form-control" id="attribute_id" required>
                            <#list attributes as attribute>
                                <option value="${attribute.id}">${attribute.name}</option>
                            </#list>
                        </select>
                    </div>
                </div>
                <div class="form-group row">
                    <label for="name" class="col-sm-3 col-form-label">Заголовок</label>
                    <div class="col-sm-9">
                    <input type="text" name="name" class="form-control" id="name" required/>
                    </div>
                </div>
                <div class="form-group row">
                    <label for="name_en" class="col-sm-3 col-form-label">Название на английском</label>
                    <div class="col-sm-9">
                    <input type="text" name="nameEn" class="form-control" id="name_en" required/>
                    </div>
                </div>
                <div class="form-group row">
                    <label for="active" class="col-sm-3 col-form-label">Показывать на сайте</label>
                    <div class="col-sm-9">
                        <select name="active" id="active" class="form-control" required>
                            <option value="1">Да</option>
                            <option value="0">Нет</option>
                        </select>
                    </div>
                </div>
                <div class="form-group row">
                    <label for="type" class="col-sm-3 col-form-label">Для фильтрации</label>
                    <div class="col-sm-9">
                        <select name="type" class="form-control" id="type" aria-describedby="helpBlock" required>
                            <option value="0" selected>Нужно выбрать только одно значение фильтра</option>
                            <option value="1">Можно выбрать одно или несколько значений</option>
                            <option value="2">Нужно задать интервал</option>
                        </select>
                        <small id="helpBlock" class="form-text hide-block text-danger">
                            Внимание! Интервал задается только для числовых атрибутов
                        </small>
                    </div>
                </div>
                <div class="form-group row">
                    <label for="searchAll" class="col-sm-3 col-form-label"> Значения фильтра</label>
                    <div class="col-sm-9">
                        <div class="custom-control custom-radio">
                            <input type="radio" id="radio1" name="searchAll" value="1" class="custom-control-input" checked>
                            <label class="custom-control-label" for="radio1">Использовать значения атрибутов существующих товаров как значения фильтра</label>
                        </div>
                        <div class="custom-control custom-radio">
                            <input type="radio" id="radio2" name="searchAll" value="0" class="custom-control-input">
                            <label class="custom-control-label" for="radio2">Создать значения фильтра</label>
                        </div>
                    </div>
                </div>
                <div id="all_values">
                    <div class="form-group row" id="values1">
                        <label for="value" class="col-sm-3 col-form-label">Значение</label>
                        <div class="col-sm-6" id="val">
                            <input type="text" name="values" class="form-control" id="value"/>
                        </div>
                            <div class="col-sm-3">
                                <button id="add" class="btn btn-primary">Добавить значение</button>
                            </div>
                        </div>
                </div>
                <div class="form-group row">
                    <div class="col-sm-10">
                        <input type="submit" class="btn btn-primary" value="Добавить фильтр"/>
                    </div>
                </div>
                <input type="hidden" name="_csrf" value="${_csrf.token}">
            </form>
        </div>
    </div>
</@c.page_admin>