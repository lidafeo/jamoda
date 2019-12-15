<ul class="list-group">
    <div>
            <form action="#" method="post" id="form_filter">
                <#list filters as filter>
                    <#if filter.values?size gt 0>
                        <li class="list-group-item">
                            <label for="filter${filter?counter}"><h6>${filter.name}</h6></label>
                            <div id="filter${filter?counter}" class="custom-control custom-checkbox form-check">
                                <#list filter.values as value>
                                    <div>
                                        <input class="custom-control-input" type="checkbox" name="${filter.nameEn}" id="radios${filter?counter}_${value?counter}" value="${value}">
                                        <label class="custom-control-label" for="radios${filter?counter}_${value?counter}">
                                            ${value}
                                        </label>
                                    </div>
                                </#list>
                            </div>
                            <#if filter?counter == filters?size>
                                <br>
                                <div class="form-group row">
                                    <div class="col-sm-10">
                                        <input type="submit" id="apply_filter" class="btn btn-primary" value="Поиск"/>
                                    </div>
                                </div>
                            </#if>
                        </li>
                    </#if>
                <#else>
                    Фильтры недоступны
                </#list>
                <input type="hidden" name="_csrf" value="${_csrf.token}">
            </form>
    </div>
</ul>