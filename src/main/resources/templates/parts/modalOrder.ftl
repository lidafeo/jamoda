<div class="modal fade" id="order-modal" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header">
                    <#if error ??>
                        <h5 class="modal-title" id="exampleModalLongTitle">Ошибка при оформлении заказа</h5>
                    <#else>
                        <h5 class="modal-title" id="exampleModalLongTitle">Информация о заказе</h5>
                    </#if>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <#if error ??>
                    Произошла ошибка при оформлении заказа. Возможно вышло время ожидания. Попробуйте снова.
                <#else>
                    Ваш заказ успешно оформлен.
                    Номер заказа - <h5>${order.id}</h5>
                </#if>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Продолжить покупки</button>
                <a href="/cart" class="btn btn-primary">Перейти в корзину</a>
            </div>
        </div>
    </div>
</div>