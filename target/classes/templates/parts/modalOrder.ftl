<div class="modal fade" id="order-modal" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLongTitle">Информация о заказе</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <div id="message"></div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Закрыть</button>
                <a href="/" class="btn btn-primary">Ок</a>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="stripe-modal" tabindex="-1" role="dialog" aria-labelledby="modalCenterTitle" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="modalLongTitle">Оплата заказа</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <div id="message"></div>
                <div class="form-center">
                    <form action="" method="post" id="form-pay">
                        <div class="form-group row">
                            <div class="col-sm-1">  </div>
                            <div class="col-sm-5">
                                <p class="text-danger" id="mess-pay"></p>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label for="number-card" class="col-sm-4 col-form-label">Номер карты</label>
                            <div class="col-sm-8">
                                <input type="text" id="number-card" name="number-card" size="20" class="form-control" data-stripe="number">
                            </div>
                        </div>
                        <div class="form-group row">
                            <label for="exp_month" class="col-sm-4 col-form-label">Срок действия</label>
                            <div class="col-sm-3">
                                <input type="text" size="2" data-stripe="exp_month" id="exp_month" class="form-control">
                            </div>
                            <div class="col-sm-3">
                                <input type="text" size="2" data-stripe="exp_year" id="exp_year" class="form-control">
                            </div>
                        </div>
                        <div class="form-group row">
                            <label for="cvc" class="col-sm-4 col-form-label">CVC</label>
                            <div class="col-sm-8">
                                <input type="text" size="4" data-stripe="cvc" id="cvc" class="form-control">
                            </div>
                        </div>
                        <input type="hidden" name="_csrf" value="${_csrf.token}">
                    </form>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Отмена</button>
                <button type="button" id="do_pay" class="btn btn-primary">Оплатить</button>
            </div>
        </div>
    </div>
</div>