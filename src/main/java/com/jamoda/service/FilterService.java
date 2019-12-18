package com.jamoda.service;

import com.jamoda.model.Attribute;
import com.jamoda.model.AttributeValue;
import com.jamoda.model.Category;
import com.jamoda.model.Filter;
import com.jamoda.repository.AttributeValueRepository;
import com.jamoda.repository.FilterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FilterService {

    @Autowired
    FilterRepository filterRepository;
    @Autowired
    AttributeValueRepository attributeValueRepository;

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

    public  List<AttributeValue> findArticleClothesWithFilter(Map<Attribute, List<String>> filters,
                                                              List<Category> categories){
        return  attributeValueRepository.findArticleClothesWithFilter(filters, categories);
    }
}
