<#import "parts/common.ftl" as c>
<#import "parts/card.ftl" as card>
<#import "parts/pager.ftl" as p>
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
        <div class="col-sm-9" id="card-deck-id">
            <#include "parts/sort.ftl">
            <@p.pager url page />
                <div class="card-deck" id="clothes_div">
                    <#list page.content as clothesOne>
                        <@card.card clothesOne />
                        <br>
                    <#else>
                        Здесь скоро будут товары
                    </#list>
                </div>
            <@p.pager url page />
        </div>
    </div>
    <#include "parts/modal.ftl">
    <#include "parts/inputSize.ftl">
</@c.page>