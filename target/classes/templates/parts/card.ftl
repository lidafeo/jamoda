<#macro card clothes>
<div class="col-sm-4">
    <div class="card my-card" style="width: 15rem;">
        <#if clothes.images?size != 0>
            <img src="/img/${clothes.images?first.name}" class="card-img-top" alt="${clothes.name}">
        </#if>
        <div class="card-body">
            <h6 class="card-title">${clothes.name}</h6>
            <p class="card-text">
                <p><b><i>${clothes.price} руб.</i></b></p>
            </p>
            <a href="/clothes?article=${clothes.article}" class="btn btn-primary">Подробнее</a>
                <button type="button" class="btn btn-primary but-buy" data-toggle="modal" data-target="#modal-size" data-whatever="${clothes.article}">
                    <img src="/img/icons8-shopping-bag-32.png" width="24" alt="Купить">
                </button>
            <p class="card-text"><small class="text-muted">${clothes.category.nameRus}</small></p>
        </div>
    </div>
</div>
</#macro>