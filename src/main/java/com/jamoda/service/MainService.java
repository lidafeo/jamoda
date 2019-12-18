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

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private FilterRepository filterRepository;
    @Autowired
    private AttributeValueRepository attributeValueRepository;

    public Model getModel(Model model,
                          HttpSession session) {
        @SuppressWarnings("unchecked")
        List<String> attributeList = (List<String>) session.getAttribute("PRODUCTS");
        if (attributeList == null) {
            attributeList = new ArrayList<>();
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
        model.addAttribute("cartSession", attributeList);
        model.addAttribute("filters", filters);
        model.addAttribute("categories", categoryRepository.findAllByType("main"));
        return model;
    }
}
