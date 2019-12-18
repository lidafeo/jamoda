<!-- Modal -->
<div class="modal fade" id="modal-admin" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLongTitle">Создание нового товара</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <#if message ??>
                    <h6><font color="green">Товар успешно добавлен!</font></h6>
                </#if>
                <#if error ??>
                    <h6><font color="red">${error?ifExists}</font></h6>
                </#if>
            </div>
            <div class="modal-footer">
                <a href="/admin" class="btn btn-secondary">Перейти в панель администратора</a>
                <button type="button" class="btn btn-primary" data-dismiss="modal">Закрыть</button>
            </div>
        </div>
    </div>
</div>