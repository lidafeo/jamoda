<#import "parts/commonAdmin.ftl" as c>
<#import "parts/modalAdmin.ftl" as modal>
<@c.page_admin>
<#if error?? || message ??>
    <@modal.modal "Поступление нового товара на склад" />
</#if>
<div class="mx-auto" style="width: 800px;">
    <div class="form-center">
        <form action="/admin/add_to_warehouse" method="post">
            <div class="form-group row">
                <h4>Поступление нового товара на склад</h4>
            </div>
            <div class="form-group row">
                <label for="article" class="col-sm-2 col-form-label">Артикул</label>
                <div class="col-sm-10">
                    <input type="text" name="article" id="article" class="form-control" placeholder="MP002XW0RBO8" required/>
                </div>
            </div>
            <div class="form-group row">
                <label for="size" class="col-sm-2 col-form-label">Размер: </label>
                <div class="col-sm-10">
                    <select class="custom-select" id="size" name="size">
                        <option value="40">40</option>
                        <option value="42">42</option>
                        <option value="44">44</option>
                        <option value="46">46</option>
                        <option value="48">48</option>
                        <option value="50">50</option>
                        <option value="52">52</option>
                    </select>
                </div>
            </div>
            <div class="form-group row">
                <label for="count" class="col-sm-2 col-form-label">Количество</label>
                <div class="col-sm-10">
                    <input type="number" name="count" class="form-control" id="price" min="0" placeholder="200" required/>
                </div>
            </div>
            <input type="hidden" name="_csrf" value="${_csrf.token}" />
            <div class="form-group row">
                <div class="col-sm-10">
                    <input type="submit" class="btn btn-primary" value="Добавить товар на склад"/>
                </div>
            </div>
        </form>
    </div>
</div>
</@c.page_admin>