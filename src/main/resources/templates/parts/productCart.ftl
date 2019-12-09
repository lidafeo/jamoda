<#macro prod clothes count size price>
<div class="card mb-3" style="max-width: 500px;">
    <div class="row no-gutters">
        <div class="col-md-4">
            <img src="/img/${clothes.images?first.name}" class="card-img" max-width="50" alt="{clothes.name}">
        </div>
        <div class="col-md-8">
            <div class="card-body">
                <p class="card-text">${clothes.category.nameRus} <b>${clothes.name}</b></p>
                <p class="card-text">Размер - ${size}</p>
                <p class="card-text">${clothes.price} руб. X <b>${count}</b> шт. = <b>${price} руб.</b></p>
            </div>
        </div>
    </div>
</div>
</#macro>