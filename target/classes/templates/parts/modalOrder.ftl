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
                    <h5>Номер заказа - Z-${order.id}</h5>
                    С Вами скоро свяжется оператор.
                </#if>
            </div>
            <div class="modal-footer">
                <#if error ??>
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Закрыть</button>
                    <a href="/" class="btn btn-primary">Перейти к выбору товаров</a>
                <#else>
                    <a href="/" class="btn btn-primary">Ок</a>
                </#if>
            </div>
        </div>
    </div>
</div>