<#import "parts/common.ftl" as c>
<@c.page>
    <div class="row">
        <#if clothes.filename??>
        <div class="col-sm-4">
            <div class="carousel slide" data-ride="carousel">
                <div class="carousel-inner">
                    <div class="carousel-item active">
                        <img src="/img/${clothes.filename}" class="d-block w-100" width="150" alt="${clothes.name}">
                    </div>
                </div>
                <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
                    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                    <span class="sr-only">Предыдущая</span>
                </a>
                <a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
                    <span class="carousel-control-next-icon" aria-hidden="true"></span>
                    <span class="sr-only">Следующая</span>
                </a>
            </div>
        </div>
        </#if>
        <div class="col-sm-8">
            <p>Название: <b>${clothes.name}</b></p>
            <p>Артикул: ${clothes.article}</p>
            <p>Цена: ${clothes.price} рублей</p>
            <p>Страна производства: ${clothes.made_in}</p>
            <p>Сезон: ${clothes.season}</p>
        </div>
    </div>
</@c.page>