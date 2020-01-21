<#import "parts/commonAdmin.ftl" as c>
<#import "parts/modalAdmin.ftl" as modal>
<@c.page_admin "/addClothes.js">
<#if error?? || message ??>
    <@modal.modal "Добавление нового товара" />
</#if>
    <div class="mx-auto" style="width: 750px;">
                <div>
                    <form action="/admin/add_clothes" method="post" id="form" enctype="multipart/form-data">
                        <div class="form-group row">
                            <h3>Добавление нового товара</h3>
                        </div>
                        <div class="form-group row">
                            <label for="category_id" class="col-sm-2 col-form-label">Категория</label>
                            <div class="col-sm-10">
                                <select name="category_id" id="category_id" class="form-control" required >
                                       <#list category as categoryOne>
                                      <option value="${categoryOne.getIdString()}">${categoryOne.nameRus}</option>
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
                        <div class="form-group row">
                            <label for="group_id" class="col-sm-2 col-form-label">Группа атрибутов</label>
                                  <div class="col-sm-10">
                                       <select name="group_id" id="group_id" class="form-control" required>
                                            <option value="-1">нет</option>
                                            <#list groups as group>
                                                <option value="${group.getIdString()}">${group.name}</option>
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
                                                 <option value="${attribute.getIdString()}">${attribute.name}</option>
                                             </#list>
                                         </select>
                                      </div>
                                      <div class="col-sm-4" id="atr-div">
                                            <input type="text" name="value" class="form-control" id="value"/>
                                       </div>
                            </div>
                        </div>
                         <div class="form-group row">
                            <div class="col-sm-10">
                                <input type="submit" class="btn btn-primary" id="add_attr" value="Добавить атрибут к товару"/>
                            </div>
                         </div>
                        <div class="form-group row">
                            <label for="files" class="col-sm-2 col-form-label">Фото</label>
                                 <div class="col-sm-10">
                                    <input type="file" class="custom-file-input" id="files" name="files" multiple/>
                                    <label class="custom-file-label" for="files">Выберите файл</label>
                                 </div>
                        </div>
                        <input type="hidden" name="_csrf" value="${_csrf.token}" />
                        <div class="form-group row">
                              <div class="col-sm-10">
                                    <input type="submit" class="btn btn-primary" value="Добавить товар"/>
                                </div>
                        </div>
                    </form>
                </div>
            </div>
</@c.page_admin>