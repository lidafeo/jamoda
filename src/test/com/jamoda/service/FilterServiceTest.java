package com.jamoda.service;

import com.jamoda.model.*;
import com.jamoda.repository.AttributeValueRepository;
import com.jamoda.repository.FilterRepository;
import com.jamoda.repository.OrderProductRepository;
import com.jamoda.repository.OrderRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class FilterServiceTest {

    @Test
    void getFiltersTest() {
        FilterService filterServMock = new FilterService();
        FilterRepository filterRepMock = Mockito.mock(FilterRepository.class);
        filterServMock.setFilterRepository(filterRepMock);

        Attribute at = new Attribute();
        at.setName("example");
        Filter filter = new Filter();
        filter.setAttribute(at);
        when(filterRepMock.findByNameEn(any())).thenReturn(filter);

        Map<String, String> params = new HashMap<>();
        params.put("1", "2");
        params.put("3", "4");
        Map<Attribute, List<String>> filters = new HashMap<>();
        List<String> li = new ArrayList<>();
        li.add("4");
        filters.put(at, li);

        Assertions.assertEquals(
                filterServMock.getFilters(params),
                filters);
    }

    @Test
    void findArticleClothesWithFilterTest() {
        FilterService filterServMock = new FilterService();
        AttributeValueRepository attValRepMock = Mockito.mock(AttributeValueRepository.class);
        filterServMock.setAttributeValueRepository(attValRepMock);

        Category category = new Category();
        category.setId(1L);
        List<Category> categories = List.of(category);
        Attribute at = new Attribute();
        at.setName("example");
        Map<Attribute, List<String>> filters = new HashMap<>();
        List<String> li = new ArrayList<>();
        li.add("Example");
        filters.put(at, li);

        Assertions.assertEquals(
                filterServMock.findArticleClothesWithFilter(filters,categories),
                attValRepMock.findArticleClothesWithFilter(filters,categories));
    }

    @Test
    void findByNameEnOrNameOrAttributeTest() {
        FilterService filterServMock = new FilterService();
        FilterRepository filterRepMock = Mockito.mock(FilterRepository.class);
        filterServMock.setFilterRepository(filterRepMock);

        Attribute at = new Attribute();
        at.setName("example");

        Assertions.assertEquals(
                filterServMock.findByNameEnOrNameOrAttribute("exampleEn", "example", at),
                filterRepMock.findByNameEnOrNameOrAttribute("exampleEn", "example", at));
    }


    @Test
    void saveFilterTest() {
        FilterService filterServMock = new FilterService();
        FilterRepository filterRepMock = Mockito.mock(FilterRepository.class);
        filterServMock.setFilterRepository(filterRepMock);

        Attribute at = new Attribute();
        at.setName("example");
        Filter filter = new Filter();
        filter.setAttribute(at);

        when(filterRepMock.saveAndFlush(filter))
                .thenAnswer(i -> i.getArguments()[0]);
        Assertions.assertEquals(filterServMock.saveFilter(filter),
                filterRepMock.saveAndFlush(filter));
    }

}