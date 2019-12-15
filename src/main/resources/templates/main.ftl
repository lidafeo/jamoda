<#import "parts/common.ftl" as c>
<#import "parts/card.ftl" as card>
<@c.page>
    <br>
    <#if choosedCategory ??>
        <h3>${choosedCategory.nameRus}</h3>
    <#else>
        <h3>Все товары</h3>
    </#if>
    <hr>
    <div class="row">
        <div class="col-sm-3">
            <#include "parts/filter.ftl">
        </div>
        <div class="col-sm-9">
            <#include "parts/sort.ftl">
            <div class="card-deck" id="clothes_div">
                <#list clothes as clothesOne>
                    <@card.card clothesOne />
                    <br>
                <#else>
                    Здесь скоро будут товары
                </#list>
            </div>
        </div>
    </div>
    <#include "parts/modal.ftl">
    <#include "parts/inputSize.ftl">
    <a href="/admin">Admin</a>
</@c.page>