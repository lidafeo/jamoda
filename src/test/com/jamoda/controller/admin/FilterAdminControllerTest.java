package com.jamoda.controller.admin;

import com.jamoda.model.Attribute;
import com.jamoda.model.Filter;
import com.jamoda.service.AttributeService;
import com.jamoda.service.FilterService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.ui.Model;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.ArgumentMatchers.any;

class FilterAdminControllerTest {

    FilterService filterService = Mockito.mock(FilterService.class);
    AttributeService attributeService = Mockito.mock(AttributeService.class);
    Model model = Mockito.mock(Model.class);

    @Test
    void pageAddFilter() {
        FilterAdminController filterAdminController = new FilterAdminController();
        filterAdminController.setFilterService(filterService);
        filterAdminController.setAttributeService(attributeService);

        Assertions.assertEquals( "admin/addFilter",
                filterAdminController.pageAddFilter(model));
    }

    @Test
    void addFilter() {
        FilterAdminController filterAdminController = new FilterAdminController();
        filterAdminController.setFilterService(filterService);
        filterAdminController.setAttributeService(attributeService);

        Attribute attribute = new Attribute();
        attribute.setName("qwerty");
        List<Attribute> alist = new ArrayList<>();
        alist.add(attribute);
        Filter filter = new Filter();
        filter.setAttribute(attribute);
        filter.setName("йцукен");
        filter.setNameEn("qwerty");

        Mockito.when(attributeService.findAll()).thenReturn(alist);
        Mockito.when(attributeService.findById(1l)).thenReturn(attribute);
        Mockito.when(filterService.findByNameEnOrNameOrAttribute(
                filter.getNameEn(), filter.getName(), filter.getAttribute())).
                thenReturn(filter);
        Mockito.when(filterService.saveFilter(filter)).thenReturn(filter);

        Assertions.assertEquals( "admin/addFilter",
                filterAdminController.addFilter(
                        filter, 1l, model));

        Assertions.assertEquals( "admin/addFilter",
                filterAdminController.addFilter(
                        filter, 0, model));
    }



    @Test
    void addFilter1() {
        FilterAdminController filterAdminController = new FilterAdminController();
        filterAdminController.setFilterService(filterService);
        filterAdminController.setAttributeService(attributeService);

        Attribute attribute = new Attribute();
        attribute.setName("qwerty");
        List<Attribute> alist = new ArrayList<>();
        alist.add(attribute);
        Filter filter = new Filter();
        filter.setAttribute(attribute);
        filter.setName("йцукен");
        filter.setNameEn("qwerty");

        Mockito.when(attributeService.findAll()).thenReturn(alist);
        Mockito.when(attributeService.findById(1l)).thenReturn(null);
        Mockito.when(filterService.findByNameEnOrNameOrAttribute(
                filter.getNameEn(), filter.getName(), filter.getAttribute())).
                thenReturn(filter);
        Mockito.when(filterService.saveFilter(filter)).thenReturn(filter);

        Assertions.assertNotNull(filterAdminController.addFilter(
                filter, 0, model));
        Assertions.assertEquals( "admin/addFilter",
                filterAdminController.addFilter(
                        filter, 0, model));
    }

    @Test
    void addFilter2() {
        FilterAdminController filterAdminController = new FilterAdminController();
        filterAdminController.setFilterService(filterService);
        filterAdminController.setAttributeService(attributeService);

        Attribute attribute = new Attribute();
        attribute.setName("qwerty");
        List<Attribute> alist = new ArrayList<>();
        alist.add(attribute);
        Filter filter = new Filter();
        filter.setAttribute(attribute);
        filter.setName("йцукен");
        filter.setNameEn("qwerty");

        Mockito.when(attributeService.findAll()).thenReturn(alist);
        Mockito.when(attributeService.findById(1l)).thenReturn(attribute);
        Mockito.when(filterService.findByNameEnOrNameOrAttribute(
                filter.getNameEn(), filter.getName(), filter.getAttribute())).
                thenReturn(null);
        Mockito.when(filterService.saveFilter(filter)).thenReturn(filter);

        Assertions.assertNotNull(filterAdminController.addFilter(
                filter, 0, model));
        Assertions.assertEquals( "admin/addFilter",
                filterAdminController.addFilter(
                        filter, 0, model));
    }

    @Test
    void addFilter3() {
        FilterAdminController filterAdminController = new FilterAdminController();
        filterAdminController.setFilterService(filterService);
        filterAdminController.setAttributeService(attributeService);

        Filter filter = new Filter();
        filter.setName("йцукен");
        filter.setNameEn("qwerty");

        Mockito.when(attributeService.findById(1l)).thenReturn(null);

        Assertions.assertEquals( "admin/addFilter",
                filterAdminController.addFilter(
                        filter, 1, model));
    }

    @Test
    void addFilter4() {
        FilterAdminController filterAdminController = new FilterAdminController();
        filterAdminController.setFilterService(filterService);
        filterAdminController.setAttributeService(attributeService);

        Attribute attribute = new Attribute();
        attribute.setName("qwerty");
        Filter filter = new Filter();
        List<String> strlist = new ArrayList<>();
        strlist.add("val1");
        filter.setName("йцукен");
        filter.setValues(strlist);

        Mockito.when(attributeService.findById(1l)).thenReturn(attribute);
        Mockito.when(filterService.findByNameEnOrNameOrAttribute(
                any(), any(), any())).thenReturn(null);

        Assertions.assertEquals( "admin/addFilter",
                filterAdminController.addFilter(
                        filter, 1, model));
    }
}