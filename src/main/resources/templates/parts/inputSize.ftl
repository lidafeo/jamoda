<div class="modal fade" id="modal-size" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Выберите размер</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form id="modal-form">
                    <input type="hidden" name="article_clothes" id="modal-article">
                    <input type="hidden" name="_csrf" value="${_csrf.token}" />
                    <div class="form-group">
                        <label for="recipient-name" class="col-form-label">Размер:</label>
                        <select class="custom-select mr-sm-2" id="size" name="size">
                            <option value="42">42</option>
                            <option value="44">44</option>
                            <option value="46">46</option>
                            <option value="48">48</option>
                            <option value="50">50</option>
                        </select>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Отменить</button>
                <button type="button" id="add-in-cart" class="btn btn-primary">Добавить в корзину</button>
            </div>
        </div>
    </div>
</div>