<#import "parts/common.ftl" as c>
<@c.page>
    <div class="row">
        <#if clothes.images?size != 0>
        <div class="col-sm-5">
            <div id="carousel" class="carousel slide" data-ride="carousel">
                <div class="carousel-inner">
                    <#list clothes.images as image>
                        <#if image?index == 0>
                            <div class="carousel-item active">
                        <#else>
                        <div class="carousel-item">
                        </#if>
                            <img src="/img/${image.name}" class="d-block w-100" width="150" alt="${clothes.name}">
                        </div>
                    </#list>
                </div>
                <a class="carousel-control-prev" href="#carousel" role="button" data-slide="prev">
                    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                    <span class="sr-only">Предыдущая</span>
                </a>
                <a class="carousel-control-next" href="#carousel" role="button" data-slide="next">
                    <span class="carousel-control-next-icon" aria-hidden="true"></span>
                    <span class="sr-only">Следующая</span>
                </a>
            </div>
        </div>
        </#if>
        <div class="col-sm-7">
            <div>
                <p>Название: <b>${clothes.name}</b></p>
                <p>Артикул: ${clothes.article}</p>
                <p>Цена: ${clothes.price} рублей</p>
            </div>
            <#list clothes.attributeGroups as group>
                <div>
                    <h6>${group.name}</h6>
                    <#list group.attributes as attribute>
                        <p>${attribute.name} : ${clothes.attributeValues["${attribute.id}"].value}</p>
                    </#list>
                </div>
            </#list>
        </div>
    </div>
</@c.page>