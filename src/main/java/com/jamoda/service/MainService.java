package com.jamoda.service;

import com.jamoda.model.Attribute;
import com.jamoda.model.Filter;
import com.jamoda.repository.AttributeValueRepository;
import com.jamoda.repository.CategoryRepository;
import com.jamoda.repository.ClothesRepository;
import com.jamoda.repository.FilterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Service
public class MainService {

    private CategoryRepository categoryRepository;
    private FilterRepository filterRepository;
    private AttributeValueRepository attributeValueRepository;

    public Model getSessionModel(Model model,
                          HttpSession session) {
        @SuppressWarnings("unchecked")
        List<String> attributeList = (List<String>) session.getAttribute("PRODUCTS");
        if (attributeList == null) {
            attributeList = new ArrayList<>();
        }
        @SuppressWarnings("unchecked")
        List<Integer> countList = (List<Integer>) session.getAttribute("COUNT");
        int countProd = 0;
        if (countList != null) {
            for(int co: countList) {
                countProd += co;
            }
        }
        List<Filter> filters = filterRepository.findAllByActive(true);
        for(int i = 0; i < filters.size(); i++) {
            if(!filters.get(i).isSearchAll()) {
                continue;
            }
            Attribute attribute = filters.get(i).getAttribute();
            List<String> values = attributeValueRepository.findDistinctValueByAttribute(attribute);
            filters.get(i).setValues(values);
        }
        //model.addAttribute("cartSession", attributeList);
        //model.addAttribute("cartSessionCount", countProd);
        model.addAttribute("filters", filters);
        model.addAttribute("categories", categoryRepository.findAllByType("main"));
        return model;
    }

    @Autowired
    public void setCategoryRepository(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
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
