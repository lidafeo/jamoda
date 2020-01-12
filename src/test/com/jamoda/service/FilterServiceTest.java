package com.jamoda.service;

import com.jamoda.model.*;
import com.jamoda.repository.AttributeValueRepository;
import com.jamoda.repository.FilterRepository;
import com.jamoda.repository.OrderProductRepository;
import com.jamoda.repository.OrderRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.w3c.dom.Attr;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class FilterServiceTest {

    FilterRepository filterRepMock = Mockito.mock(FilterRepository.class);
    AttributeValueRepository attValRepMock = Mockito.mock(AttributeValueRepository.class);

    @Test
    void getFiltersTest() {
        FilterService filterServMock = new FilterService();
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
    void getFiltersNullTest() {
        FilterService filterServMock = new FilterService();
        filterServMock.setFilterRepository(filterRepMock);

        when(filterRepMock.findByNameEn(any())).thenReturn(null);

        Map<String, String> params = new HashMap<>();
        params.put("1", "2");
        params.put("3", "4");

        Map<String, String> o = new HashMap<>();
        Assertions.assertEquals(
                filterServMock.getFilters(params),
                o);
    }

    @Test
    void getActiveFilter() {
        FilterService filterServMock = new FilterService();
        filterServMock.setFilterRepository(filterRepMock);
        filterServMock.setAttributeValueRepository(attValRepMock);

        Filter filter = new Filter();
        Attribute attribute = new Attribute();
        attribute.setName("name");
        filter.setName("name");
        filter.setAttribute(attribute);
        List<Filter> flist = new ArrayList<>();
        flist.add(filter);
        Mockito.when(filterRepMock.findAllByActive(true)).thenReturn(flist);
        List<String> li = new ArrayList<>();
        li.add("str");
        Mockito.when(attValRepMock.findDistinctValueByAttribute(attribute)).
                thenReturn(li);

        Assertions.assertEquals(filterServMock.getActiveFilter(), flist);

        filter.setSearchAll(true);
        Assertions.assertEquals(filterServMock.getActiveFilter(), flist);
    }

    @Test
    void getFilteredClothes() {
        FilterService filterServMock = new FilterService();

        AttributeValue attributeValue = new AttributeValue();
        attributeValue.setActive(true);
        List<AttributeValue> li = new ArrayList<>();
        li.add(attributeValue);
        Attribute attribute = new Attribute();
        attribute.setName("name");
        Map<Attribute, List<String>> params = new HashMap<>();
        List<String> str = new ArrayList<>();
        str.add("article");
        params.put(attribute, str);
        Clothes clothes = new Clothes();
        clothes.setArticle("article");
        attributeValue.setClothes(clothes);

        Assertions.assertEquals(filterServMock.getFilteredClothes(
                li, params), str);

        AttributeValue attributeValue2 = new AttributeValue();
        attributeValue2.setActive(true);
        li.add(attributeValue2);
        attributeValue2.setClothes(clothes);
        List<String> str2 = new ArrayList<>();

        Assertions.assertEquals(filterServMock.getFilteredClothes(
                li, params), str2);
    }

    @Test
    void findArticleClothesWithFilterTest() {
        FilterService filterServMock = new FilterService();
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