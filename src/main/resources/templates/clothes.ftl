<#import "parts/common.ftl" as c>
<@c.page "/clothes.js">
    <div class="row main-clothes">
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
        <div class="col-sm-1">
        </div>
        <div class="col-sm-6">
            <h1> ${clothes.name} </h1>
            <p> ${clothes.category.nameRus} </p>
            <h4>${clothes.price} руб.</h4>
            <form method="post">
                <div class="form-group row">
                    <label for="article" class="col-sm-2 col-form-label">Артикул:</label>
                    <div class="col-sm-10">
                        <input type="text" readonly class="form-control-plaintext" id="article" name="article_clothes" value="${clothes.article}">
                    </div>
                </div>
                <#if commonCount != 0>
                    <div id="size-div">
                        <div class="form-group row">
                            <label for="size" class="col-sm-2 col-form-label">Размер: </label>
                            <div class="col-sm-3">
                                <select class="custom-select mr-sm-3" id="size" name="size">
                                    <#list sizes?keys as key>
                                        <#if sizes[key] == 0>
                                            <option value="${key}" class="disable-option" data-count="0" disabled>${key}</option>
                                        <#else>
                                            <option value="${key}" data-count="${sizes[key]}">${key}</option>
                                        </#if>
                                    </#list>
                                </select>
                            </div>
                        </div>
                        <input type="hidden" name="_csrf" value="${_csrf.token}">
                        <div class="form-group row">
                            <div class="col-sm-10">

                                <button type="button" class="btn btn-primary btn-lg" id="add-in-cart" data-whatever="${clothes.article}" data-sizes="${clothes.getStringSizes()}"
                                        <#if clothes.images?size != 0>
                                            data-image="${clothes.images?first.name}"
                                        </#if>
                                        data-price="${clothes.price}" data-category="${clothes.category.nameRus}" data-name="${clothes.name}" data-but="clothes">
                                    Добавить в корзину</button>
                            </div>
                        </div>
                    </div>
                <#else>
                    <div id="size-div">
                        <div class="alert alert-danger col-sm-10" role="alert">К сожалению товара нет в наличии!</div>
                    </div>
                </#if>

            </form>
            <div>
                <#list clothes.attributeGroups as group>
                    <div class="card" style="width: 30rem;">
                        <div class="card-header">
                            <h6>${group.name}</h6>
                        </div>
                        <ul class="list-group list-group-flush">
                            <li class="list-group-item">
                                <table class="table border-top-0">
                                    <#list group.attributes as attribute>
                                        <#if clothes.attributeValues["${attribute.id}"] ??>
                                            <tr>
                                                <th scope="row">${attribute.name}</th>
                                                <td>${clothes.attributeValues["${attribute.id}"].value}</td>
                                            </tr>
                                        </#if>
                                     </#list>
                                </table>
                            </li>
                        </ul>
                    </div>
                    <br>
                </#list>
            </div>
        </div>
    </div>
    <#include "parts/modal.ftl">
</@c.page>