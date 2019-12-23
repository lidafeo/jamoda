<#macro card clothes>
<#if clothes.presence>
    <div class="col-sm-4" id="card-${clothes.article}">
<#else>
    <div class="col-sm-4 presence" id="card-${clothes.article}">
</#if>
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
            <#if clothes.presence>
                <button type="button" class="btn btn-primary but-buy" data-whatever="${clothes.article}" data-sizes="${clothes.getStringSizes()}" data-remains="${clothes.getCountsGap()}"
                        <#if clothes.images?size != 0>
                            data-image="${clothes.images?first.name}"
                        </#if>
                         data-price="${clothes.price}" data-category="${clothes.category.nameRus}" data-name="${clothes.name}">
                        <img src="/img/icons8-shopping-bag-32.png" width="24" alt="Купить">
                </button>
            </#if>
            <p class="card-text"><small class="text-muted">${clothes.category.nameRus}</small></p>
            <#if clothes.presence>
                <p class="card-text" id="sizes"><small class="text-muted">Размеры: ${clothes.getSizesGap()}</small></p>
            <#else>
                <p class="card-text" id="sizes"><small class="text-danger">Нет в наличии</small></p>
            </#if>
        </div>
    </div>
</div>
</#macro>