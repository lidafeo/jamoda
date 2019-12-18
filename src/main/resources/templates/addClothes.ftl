<#import "parts/commonAdmin.ftl" as c>
<@c.page_admin "/addClothes.js">
    <#if error??>
        <h6><font color="red">${error?ifExists}</font></h6>
    </#if>
    <#if message??>
        <h6><font color="green">Успешно!</font></h6>
    </#if>
    <div class="mx-auto" style="width: 900px;">
                <div class="form-center">
                    <form action="/admin/add_clothes" method="post" id="form">
                        <div class="form-group row">
                            <h4>Добавление нового товара</h4>
                        </div>
                        <div class="form-group row">
                            <label for="category_id" class="col-sm-2 col-form-label">Категория</label>
                            <div class="col-sm-10">
                                <select name="category_id" id="category_id" class="form-control" required >
                                       <#list category as categoryOne>
                                      <option value="${categoryOne.id}">${categoryOne.nameRus}</option>
                                     </#list>
                                </select>
                            </div>
                        </div>
                        <div class="form-group row">
                              <label for="article" class="col-sm-2 col-form-label">Артикул</label>
                                    <div class="col-sm-10">
                                         <input type="text" name="article" id="article" class="form-control" placeholder="MP002XW0RBO8" required/>
                                     </div>
                        </div>
                        <div class="form-group row">
                               <label for="name" class="col-sm-2 col-form-label">Название</label>
                                   <div class="col-sm-10">
                                         <input type="text" name="name" class="form-control" id="name" placeholder="Платье Love Republic" required/>
                                    </div>
                        </div>
                        <div class="form-group row">
                             <label for="price" class="col-sm-2 col-form-label">Цена</label>
                               <div class="col-sm-9">
                                <input type="number" name="price" class="form-control" id="price" min="0" placeholder="1000" required/>
                                </div>
                                <div class="col-sm-1">
                                руб.
                                </div>
                        </div>
                        <div class="form-group row>
                            <label for="group_id" class="col-sm-2 col-form-label">Группа категорий</label>
                                  <div class="col-sm-10">
                                       <select name="group_id" id="group_id" class="form-control" required >
                                            <option value="-1">нет</option>
                                            <#list groups as group>
                                                <option value="${group.id}">${group.name}</option>
                                            </#list>
                                       </select>
                                  </div>
                        </div>
                        <div class="attributes">
                            <div class="form-group row" id="atr1">
                                  <label for="attribute" class="col-sm-2 col-form-label">Атрибут</label>
                                      <div class="col-sm-4">
                                         <select name="attribute" id="attribute" class="form-control">
                                             <#list attributes as attribute>
                                                 <option value="${attribute.id}">${attribute.name}</option>
                                             </#list>
                                         </select>
                                      </div>
                                      <div class="col-sm-4" id="atr-div">
                                            <input type="text" name="value" class="form-control" id="value"/>
                                       </div>
                            </div>
                        </div>
                        <input type="hidden" name="_csrf" value="${_csrf.token}" />
                        <div class="form-group row">
                            <div class="col-sm-10">
                                <input type="submit" class="btn btn-primary" id="add_attr" value="Добавить атрибут"/>
                            </div>
                        </div>
                        <div class="form-group row">
                              <div class="col-sm-10">
                                    <input type="submit" class="btn btn-primary" value="Добавить товар"/>
                                </div>
                        </div>
                    </form>
                </div>
            </div>
</@c.page_admin>