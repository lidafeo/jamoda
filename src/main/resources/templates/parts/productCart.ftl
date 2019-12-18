<#macro prod clothes count size price>
<div class="col-sm-6">
    <div class="card mb-4" style="max-width: 400px;">
        <div class="row no-gutters">
            <#if clothes.images?size != 0>
                <div class="col-md-5">
                    <img src="/img/${clothes.images?first.name}" width="100px" class="card-img"  alt="{clothes.name}">
                </div>
                <div class="col-md-7">
            <#else>
                 <div class="col-md-12">
            </#if>
                <div class="card-body">
                    <p class="card-text">${clothes.category.nameRus} <b>${clothes.name}</b></p>
                    <p class="card-text">Размер - ${size}</p>
                    <p class="card-text">${clothes.price} руб. X <b>${count}</b> шт. <hr><h3>${price} руб.</h3></p>
                </div>
            </div>
        </div>
    </div>
</div>
</#macro>