<#import "productCart.ftl" as p>
<div><a href="#" id="back-order">Вернуться к списку заказов</a></div>
<#if order??>
    <br><h3>Информация о заказе № Z-${order.id}</h3><br>
    <table class="table" style="max-width: 700px">
        <tbody>
        <tr>
            <th scope="row">Имя покупателя</th>
            <td>${order.name}</td>
        </tr>
        <tr>
            <th scope="row">Телефон покупателя</th>
            <td>${order.phone}</td>
        </tr>
        <tr>
            <th scope="row">Электронная почта покупателя</th>
            <td>${order.email}</td>
        </tr>
        <tr>
            <th scope="row">Адрес доставки</th>
            <td>${order.address}</td>
        </tr>
        <tr>
            <th scope="row">Сумма заказа</th>
            <td>${order.sum} руб.</td>
        </tr>
        <tr>
            <th scope="row">Комментарий к заказу</th>
            <td>${order.comment}</td>
        </tr>
        </tbody>
    </table>

    <div class="row">
        <#if admin?? && admin == true && (!order.confirm?? || order.confirm == false)>
            <div class="col-sm conf-${order.getNumberString()}"><a data-id="${order.getNumberString()}" class="btn btn-primary confirm" href="#">Подтвердить заказ</a></div>
        </#if>
        <#if order.completed == false>
            <div class="col-sm deliv-${order.getNumberString()}"><a data-id="${order.getNumberString()}" class="btn btn-primary delivery" href="#">Подтвердить доставку</a></div>
        </#if>
    </div>

    <br><h2>Информация о товарах заказа</h2><br>
    <div class="card-deck">
        <#list order.products as product>
            <#if product.checkClothesExists()>
                <@p.prod product.clothes product.count product.size product.price />
            <#else>
                <div class="col-sm-6">
                    <div class="card mb-4" style="max-width: 400px;">
                        <div class="row no-gutters">
                            <div class="col-md-12">
                                <div class="card-body">
                                    <p class="card-text">нет информации о товаре</p>
                                    <p class="card-text">Размер - ${product.size}</p>
                                    <p class="card-text"><b>${product.count}</b> шт. <hr><h3>${product.price} руб.</h3></p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </#if>
        <#else>
            нет товаров
        </#list>
    </div>
    <br>
    <div><a href="#" id="back-order">Вернуться к списку заказов</a></div><br>
<#else>
    <div>Нет данных</div>
</#if>