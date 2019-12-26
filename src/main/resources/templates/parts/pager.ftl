<#macro pager url page>
    <hr>
    <#if page.getTotalPages() != 0>
        <div class="row">
            <div class="col-sm-1"></div>
            <div class="col-sm-5">
                <#if page.getTotalPages() gt 7>
                    <#assign
                    totalPages = page.getTotalPages()
                    pageNumber = page.getNumber() + 1

                    head = (pageNumber > 4)?then([1, -1], [1, 2, 3])
                    tail = (pageNumber < totalPages - 3)?then([-1, totalPages], [totalPages - 2, totalPages - 1, totalPages])
                    bodyBefore = (pageNumber > 4 && pageNumber < totalPages - 1)?then([pageNumber - 2, pageNumber - 1], [])
                    bodyAfter = (pageNumber > 2 && pageNumber < totalPages - 3)?then([pageNumber + 1, pageNumber + 2], [])

                    body = head + bodyBefore + (pageNumber > 3 && pageNumber < totalPages - 2)?then([pageNumber], []) + bodyAfter + tail
                    >
                <#else>
                    <#assign body = 1..page.getTotalPages()>
                </#if>
                <div class="mt-3">
                    <ul class="pagination pagination-page">
                        <li class="page-item disabled">
                            <a class="page-link" href="#" tabindex="-1">Страницы</a>
                        </li>
                        <#list body as p>
                            <#if (p - 1) == page.getNumber()>
                                <li class="page-item active">
                                    <a class="page-link" href="#" data-number="${p - 1}" tabindex="-1">${p}</a>
                                </li>
                            <#elseif p == -1>
                                <li class="page-item disabled">
                                    <a class="page-link" href="#" tabindex="-1">...</a>
                                </li>
                            <#else>
                                <li class="page-item">
                                    <a class="page-link pages-a" data-number="${p - 1}" href="${url}page=${p - 1}&size=${page.getSize()}" tabindex="-1">${p}</a>
                                </li>
                            </#if>
                        </#list>
                    </ul>
                </div>
            </div>
            <div class="col-sm-5">
                <div class="mt-3">
                    <ul class="pagination pagination-size">
                        <li class="page-item disabled">
                            <a class="page-link" href="#" tabindex="-1">Элементов на странице</a>
                        </li>
                        <#list [6, 12, 21] as c>
                            <#if c == page.getSize()>
                                <li class="page-item active">
                                    <a class="page-link" href="#" data-number="${c}" tabindex="-1">${c}</a>
                                </li>
                            <#else>
                                <li class="page-item">
                                    <a class="page-link size-a" data-number="${c}" href="${url}page=${page.getNumber()}&size=${c}" tabindex="-1">${c}</a>
                                </li>
                            </#if>
                        </#list>
                    </ul>
                </div>
            </div>
            <div class="col-sm-1"></div>
        </div>
    </#if>
</#macro>