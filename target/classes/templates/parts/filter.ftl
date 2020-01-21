<ul class="list-group">
    <div>
            <form action="#" method="post" id="form_filter">
                <li class="list-group-item">
                    <label for="filter_price_min"><h6>Цена</h6></label>
                    <div class="form-group form-row">
                        <label for="price" class="col-sm-1 col-form-label">от</label>
                        <div class="col-sm-4">
                            <input class="form-control" type="number" name="price_min" id="filter_price_min" min="0" max="9000000" value="0" required>
                        </div>
                        <label class="col-sm-1 col-form-label">до</label>
                        <div class="col-sm-5">
                            <input class="form-control" type="number" name="price_max" id="filter_price_max" min="0" max="9000000" value="${max_price}" required>
                        </div>
                        <label class="col-sm-1 col-form-label">руб.</label>
                    </div>
                </li>
                <li class="list-group-item">
                    <label for="filter_size"><h6>Размер</h6></label>
                    <div class="form-group form-row">
                        <div id="filter_size" class="custom-control custom-checkbox form-check">
                            <#list [40, 42, 44, 46, 48, 50, 52] as i>
                                <div>
                                    <input class="custom-control-input" type="checkbox" name="size_clothes" id="size_${i}" value="${i}" autocomplete="off">
                                    <label class="custom-control-label" for="size_${i}">
                                        ${i}
                                    </label>
                                </div>
                            </#list>
                        </div>
                    </div>
                </li>
                <#list filters as filter>
                    <#if filter.values?size gt 0>
                        <li class="list-group-item">
                            <label for="filter${filter?counter}"><h6>${filter.name}</h6></label>
                            <div id="filter${filter?counter}" class="custom-control custom-checkbox form-check">
                                <#list filter.values as value>
                                    <div>
                                        <input class="custom-control-input" type="checkbox" name="${filter.nameEn}" id="radios${filter?counter}_${value?counter}" value="${value}" autocomplete="off">
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
                <input type="hidden" name="_csrf" value="${_csrf.token}" />
            </form>
    </div>
</ul>