package com.jamoda.service;

import com.jamoda.model.*;
import com.jamoda.repository.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class FilterServiceTest {

    FilterRepository filterRepMock = Mockito.mock(FilterRepository.class);
    AttributeValueRepository attValRepMock = Mockito.mock(AttributeValueRepository.class);
    ClothesRepository clothesRepository = Mockito.mock(ClothesRepository.class);

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
                li, params, null), str);

        AttributeValue attributeValue2 = new AttributeValue();
        attributeValue2.setActive(true);
        li.add(attributeValue2);
        attributeValue2.setClothes(clothes);
        List<String> str2 = new ArrayList<>();

        Assertions.assertEquals(filterServMock.getFilteredClothes(
                li, params, "1"), str2);
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
        AttributeValue attributeValue = new AttributeValue();
        attributeValue.setValue("1");
        List<AttributeValue> atlist = new ArrayList<>();
        atlist.add(attributeValue);

        Mockito.when(attValRepMock.findArticleClothesWithFilter(
                filters, categories)).thenReturn(atlist);

        Assertions.assertEquals(
                filterServMock.findArticleClothesWithFilter(filters,categories),
                attValRepMock.findArticleClothesWithFilter(filters,categories));
    }


    @Test
    void filteredSize() {
        FilterService filterServMock = new FilterService();
        filterServMock.setClothesRepository(clothesRepository);

        Category category = new Category();
        category.setId(1L);
        List<Category> categories = List.of(category);
        Clothes clothes = new Clothes();
        clothes.setArticle("article");
        List<Clothes> clothess = List.of(clothes);

        Mockito.when(clothesRepository.findAllByPresenceAndCategoryIn(
                true, categories)).thenReturn(clothess);
        Mockito.when(clothesRepository.findAllByPresence(
                true)).thenReturn(clothess);
        List<String> stlist = new ArrayList<>();

        Assertions.assertEquals(
                filterServMock.filteredSize("1", null),
                stlist);
        Assertions.assertEquals(
                filterServMock.filteredSize("1", categories),
                stlist);
    }

    @Test
    void findByNameEnOrNameOrAttributeTest() {
        FilterService filterServMock = new FilterService();
        filterServMock.setFilterRepository(filterRepMock);

        Attribute at = new Attribute();
        at.setName("example");
        Filter filter = new Filter();
        filter.setName("name");
        Mockito.when(filterRepMock.findByNameEnOrNameOrAttribute(
                "exampleEn", "example", at)).thenReturn(filter);

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
        Mockito.when(filterRepMock.saveAndFlush(filter)).thenReturn(filter);
        when(filterRepMock.saveAndFlush(filter))
                .thenAnswer(i -> i.getArguments()[0]);
        Assertions.assertEquals(filterServMock.saveFilter(filter),
                filterRepMock.saveAndFlush(filter));
    }

}