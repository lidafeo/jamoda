package com.jamoda.service;

import com.jamoda.model.*;
import com.jamoda.repository.AttributeValueRepository;
import com.jamoda.repository.FilterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class FilterService {

    private FilterRepository filterRepository;
    private AttributeValueRepository attributeValueRepository;

    public Map<Attribute, List<String>> getFilters(Map<String, String> params) {
        Map<Attribute, List<String>> filters = new HashMap<>();
        for (String nameFilter : params.keySet()) {
            Filter filter = filterRepository.findByNameEn(nameFilter);
            if(filter == null) {
                continue;
            }
            Attribute attribute = filter.getAttribute();
            List<String> values = Arrays.asList(params.get(nameFilter).split(","));
            filters.put(attribute, values);
        }
        return  filters;
    }

    public List<Filter> getActiveFilter() {
        List<Filter> filters = filterRepository.findAllByActive(true);
        for(int i = 0; i < filters.size(); i++) {
            if(!filters.get(i).isSearchAll()) {
                continue;
            }
            Attribute attribute = filters.get(i).getAttribute();
            List<String> values = attributeValueRepository.findDistinctValueByAttribute(attribute);
            filters.get(i).setValues(values);
        }
        return filters;
    }

    public List<String> getFilteredClothes(List<AttributeValue> attributeValue,
                                           Map<Attribute, List<String>> filters) {
        Map<Clothes, Integer> map = new HashMap<>();
        //смотрим сколько совпало
        for(AttributeValue val : attributeValue) {
            if(map.get(val.getClothes()) == null) {
                map.put(val.getClothes(), 1);
            }
            else {
                map.put(val.getClothes(), map.get(val.getClothes()) + 1);
            }
        }
        List<String> clothes = new ArrayList<>();
        for (Clothes clo : map.keySet()) {
            if(map.get(clo) == filters.size()) {
                clothes.add(clo.getArticle());
            }
        }
        return clothes;
    }

    public  List<AttributeValue> findArticleClothesWithFilter(Map<Attribute, List<String>> filters, List<Category> categories) {
        return  attributeValueRepository.findArticleClothesWithFilter(filters, categories);
    }

    public Filter saveFilter(Filter filter) {
        return filterRepository.saveAndFlush(filter);
    }

    public Filter findByNameEnOrNameOrAttribute(String nameEn, String name, Attribute attribute) {
        return filterRepository.findByNameEnOrNameOrAttribute(nameEn, name, attribute);
    }

    @Autowired
    public void setFilterRepository(FilterRepository filterRepository) {
        this.filterRepository = filterRepository;
    }
    @Autowired
    public void setAttributeValueRepository(AttributeValueRepository attributeValueRepository) {
        this.attributeValueRepository = attributeValueRepository;
    }
}
