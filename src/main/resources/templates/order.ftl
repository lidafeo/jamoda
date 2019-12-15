<#import "parts/common.ftl" as c>
<#import "parts/productCart.ftl" as card>
<@c.page>
    <div class="container main-clothes">
        <#if cart.products?size gt 0 >
            <h1>ОФОРМЛЕНИЕ ЗАКАЗА</h1>
            <hr>
            <div class="mx-auto" style="width: 900px;">
                <div class="form-center">
                    <form action="/order" method="post">
                        <div class="form-group row">
                            <h4>1. Данные получателя заказа</h4>
                        </div>
                        <div class="form-group row">
                            <label for="name" class="col-sm-3 col-form-label">Имя</label>
                            <div class="col-sm-9">
                                <input type="text" name="name" class="form-control" id="name" required/>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label for="phone" class="col-sm-3 col-form-label">Телефон</label>
                            <div class="col-sm-9">
                                <input type="text" name="phone" class="form-control" id="phone" required/>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label for="email" class="col-sm-3 col-form-label">Электронная почта</label>
                            <div class="col-sm-9">
                                <input type="email" name="email" class="form-control" id="email" required/>
                            </div>
                        </div>
                        <div class="form-group row">
                            <h4>2. Адрес доставки</h4>
                        </div>
                        <div class="form-group row">
                            <label for="address" class="col-sm-3 col-form-label">Адрес доставки</label>
                            <div class="col-sm-9">
                                <textarea name="address" id="address" class="form-control" required></textarea>
                            </div>
                        </div>
                        <div class="form-group row">
                            <h4>3. Способ оплаты</h4>
                        </div>
                        <div class="form-group row">
                            <label for="active" class="col-sm-3 col-form-label">Способ оплаты</label>
                            <div class="col-sm-9">
                                <select name="active" id="active" class="form-control" required>
                                    <option value="0">Оплата при получении</option>
                                    <option value="1">Онлайн-оплата картой</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group row">
                            <h4>4. Комментарий к заказу</h4>
                        </div>
                        <div class="form-group row">
                            <div class="col-sm-12">
                                <textarea name="comment" id="comment" class="form-control" required></textarea>
                            </div>
                        </div>
                        <hr>
                        <div class="form-group row">
                            <h4>${cart.count} товар(а) на сумму ${cart.price} руб.</h4>
                        </div>
                        <div class="form-group row">
                            <label for="sum" class="col-sm-3 col-form-label col-form-label-lg">Итого:</label>
                            <div class="col-sm-2">
                                <input type="text" readonly class="form-control-plaintext form-control-lg" id="sum" name="sum" value="${cart.price}">
                            </div>
                            <div class="col-sm-1">
                                <label  class="col-form-label col-form-label-lg">руб.</label>
                            </div>
                        </div>
                        <div class="form-group row">
                            <div class="col-sm-10">
                                <input type="submit" class="btn btn-primary" value="Оформить заказ"/>
                            </div>
                        </div>
                        <input type="hidden" name="_csrf" value="${_csrf.token}">
                    </form>
                </div>
            </div>
        </#if>
    </div>
</@c.page>