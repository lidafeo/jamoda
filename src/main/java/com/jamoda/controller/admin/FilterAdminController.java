package com.jamoda.controller.admin;

import com.jamoda.model.Attribute;
import com.jamoda.model.Filter;
import com.jamoda.service.AttributeService;
import com.jamoda.service.FilterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.LinkedList;
import java.util.List;

@Controller
public class FilterAdminController {

    private FilterService filterService;
    private AttributeService attributeService;

    @GetMapping("/admin/add_filter")
    public String pageAddFilter(Model model) {
        model.addAttribute("attributes", attributeService.findAll());
        return "admin/addFilter";
    }

    @PostMapping("/admin/add_filter")
    public String addFilter (Filter filter,
                             @RequestParam(name="attributeId") long attributeId,
                             Model model) {
        model.addAttribute("attributes", attributeService.findAll());

        if( attributeId != 0) {
            Attribute attribute = attributeService.findById(attributeId);
            if(attribute == null) {
                return getErrorFilter("Такого атрибута не существует!", model);
            }
            filter.setAttribute(attribute);
        }
        else {
            return getErrorFilter("Выберите атрибут!", model);
        }

        Filter filterFromDb = filterService.findByNameEnOrNameOrAttribute(filter.getNameEn(), filter.getName(), filter.getAttribute());
        if(filterFromDb != null) {
            return getErrorFilter("Такой фильтр уже существует!", model);
        }

        if (!filter.isSearchAll()) {
            List<String> values = new LinkedList<>();
            for(String value: filter.getValues()) {
                if(value != null && value.trim() != "") {
                    values.add(value);
                }
            }
            filter.setValues(values);
        }

        filterService.saveFilter(filter);
        model.addAttribute("message", "Фильтр успешно добавлен");
        return "admin/addFilter";
    }

    public String getErrorFilter(String err, Model model) {
        model.addAttribute("error", err);
        return "admin/addFilter";
    }

    @Autowired
    public void setFilterService(FilterService filterService) {
        this.filterService = filterService;
    }
    @Autowired
    public void setAttributeService(AttributeService attributeService) {
        this.attributeService = attributeService;
    }
}
