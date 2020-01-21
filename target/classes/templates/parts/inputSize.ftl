<div class="modal fade" id="modal-size" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Выберите размер</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body" id="modal-size-body">
                <form id="modal-form">
                    <input type="hidden" name="article_clothes" id="article">
                    <input type="hidden" name="_csrf" id="_csrf-modal" value="${_csrf.token}" />
                    <div class="form-group">
                        <label for="recipient-name" class="col-form-label">Размер:</label>
                        <select class="custom-select mr-sm-2" id="size" name="size">
                            <option id="opt-40" data-count="0" value="40">40</option>
                            <option id="opt-42" data-count="0" value="42">42</option>
                            <option id="opt-44" data-count="0" value="44">44</option>
                            <option id="opt-46" data-count="0" value="46">46</option>
                            <option id="opt-48" data-count="0" value="48">48</option>
                            <option id="opt-50" data-count="0" value="50">50</option>
                            <option id="opt-52" data-count="0" value="52">52</option>
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