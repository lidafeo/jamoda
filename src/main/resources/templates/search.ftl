<#import "parts/common.ftl" as c>
<#import "parts/pager.ftl" as p>
<#import "parts/card.ftl" as card>
<@c.page>
    <div class="container">
        <br><h3>Поиск по запросу «${q}»</h3>
        <hr>
        <#if error??>
            <div>${error}</div>
        <#else>
            <div id="card-deck-id">
                <div id="clothes_div">
                    <div class="card-deck">
                        <#list clothes.content as clothesOne>
                            <@card.card clothesOne />
                            <br>
                        <#else>
                            <div class="ml-3">По Вашему запросу ничего не найдено</div>
                        </#list>
                    </div>
                    <@p.pager ("/search?q=" + q + "&") clothes />
                </div>
            </div>
        </#if>
    </div>
</@c.page>