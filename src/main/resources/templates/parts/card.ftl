<#macro card clothes>
<div class="col-sm-4">
    <div class="card" style="width: 15rem;">
        <#if clothes.filename??>
            <img src="/img/${clothes.filename}" class="card-img-top" alt="${clothes.name}">
        </#if>
        <div class="card-body">
            <h5 class="card-title">${clothes.name}</h5>
            <p class="card-text">
                <p>Бренд: ${clothes.brand}</p>
                <p><i>${clothes.price} рублей</i></p>
            </p>
            <a href="/clothes/${clothes.article}" class="btn btn-primary">Подробнее</a>
            <p class="card-text"><small class="text-muted">${clothes.category}</small></p>
        </div>
    </div>
</div>
</#macro>