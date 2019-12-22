package com.jamoda.service;

import com.jamoda.model.Attribute;
import com.jamoda.model.Category;
import com.jamoda.model.Filter;
import com.jamoda.repository.*;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.ui.Model;
import org.w3c.dom.Attr;

import javax.servlet.http.HttpSession;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class MainServiceTest {

    @Test
    void getSessionModelTest() {

        HttpSession session = Mockito.mock(HttpSession.class);
        session.setAttribute("1", 1);
        Model model = Mockito.mock(Model.class);
        //model.addAttribute("0", 0);

        Attribute at = new Attribute();
        at.setName("example");
        Filter filter1 = new Filter();
        filter1.setAttribute(at);
        filter1.setSearchAll(true);
        Filter filter2 = new Filter();
        filter2.setSearchAll(false);
        List<Filter> filters = List.of(filter1, filter2);
        Category category = new Category();
        category.setId(1L);
        List<Category> categories = List.of(category);

        MainService mainService = new MainService();
        CategoryRepository catRepMock = Mockito.mock(CategoryRepository.class);
        when(catRepMock.findAllByType("main")).thenReturn(categories);
        FilterRepository filtRepMock = Mockito.mock(FilterRepository.class);
        when(filtRepMock.findAllByActive(true)).thenReturn(filters);
        AttributeValueRepository attrValRepMock = Mockito.mock(AttributeValueRepository.class);
        List<String> values = List.of("1");
        when(attrValRepMock.findDistinctValueByAttribute(at)).thenReturn(values);
        filters.get(0).setValues(values);

        mainService.setAttributeValueRepository(attrValRepMock);
        mainService.setCategoryRepository(catRepMock);
        mainService.setFilterRepository(filtRepMock);

        Model model2 = mock(Model.class);
       // model2.addAttribute("0", 0);
        List<String> attributeList = (List<String>) session.getAttribute("PRODUCTS");
        model2.addAttribute("cartSession", attributeList);
        model2.addAttribute("filters", filters);
        model2.addAttribute("categories", catRepMock.findAllByType("main"));

        Model model1 = mock(Model.class);
        model1 = mainService.getSessionModel(model, session);

        Assertions.assertEquals(model1.containsAttribute("cartSession"),
                model2.containsAttribute("cartSession"));
        Assertions.assertEquals(model1.containsAttribute("filters"),
                model2.containsAttribute("filters"));
        Assertions.assertEquals(model1.containsAttribute("categories"),
                model2.containsAttribute("categories"));

    }
}