<div class="mx-auto" style="width: 900px;">
    <div class="form-center">
        <form action="/cabinet" method="post" id="profile-form">
            <div class="form-group row">
                <div class="col-sm-1"></div>
                <div class="form-group row text-danger" id="err-mess">${error?if_exists}</div>
                <div class="form-group row text-success" id="mess-mess">${message?if_exists}</div>
            </div>
            <div class="form-group row">
                <h4>1. Личные данные</h4>
            </div>
            <div class="form-group row">
                <label for="email" class="col-sm-3 col-form-label">Электронная почта</label>
                <div class="col-sm-9">
                    <input type="text" readonly name="email" class="form-control-plaintext" id="email" value="${customer.email}" autocomplete="off"/>
                </div>
            </div>
            <div class="form-group row">
                <label for="name" class="col-sm-3 col-form-label">Имя</label>
                <div class="col-sm-9">
                    <#if customer.name??>
                        <input type="text" name="name" class="form-control" id="name" value="${customer.name}" autocomplete="off"/>
                    <#else>
                        <input type="text" name="name" class="form-control" id="name" autocomplete="off"/>
                    </#if>
                </div>
            </div>
            <div class="form-group row">
                <label for="phone" class="col-sm-3 col-form-label">Номер телефона</label>
                <div class="col-sm-9">
                    <#if customer.phone??>
                        <input type="text" name="phone" class="form-control" id="phone" value="${customer.phone}"/>
                    <#else>
                        <input type="text" name="phone" class="form-control" id="phone"/>
                    </#if>
                </div>
            </div>
            <hr>
            <div class="form-group row">
                <h4>2. Адрес для доставки</h4>
            </div>
            <div class="form-group row">
                <label for="address" class="col-sm-3 col-form-label">Адрес доставки</label>
                <div class="col-sm-9">
                    <#if customer.address??>
                        <textarea name="address" id="address" class="form-control" value="${customer.address}"></textarea>
                    <#else>
                        <textarea name="address" id="address" class="form-control"></textarea>
                    </#if>
                </div>
            </div>
            <hr>
            <div class="form-group row">
                <div class="col-sm-10">
                    <input type="submit" class="btn btn-primary btn-lg" id="update-profile" value="Сохранить"/>
                </div>
            </div>
            <input type="hidden" name="_csrf" value="${_csrf.token}">
        </form>
    </div>
</div>