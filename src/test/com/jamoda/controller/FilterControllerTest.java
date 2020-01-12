package com.jamoda.controller;

import com.jamoda.model.*;
import com.jamoda.service.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.data.domain.*;
import org.springframework.data.geo.GeoPage;
import org.springframework.data.util.Streamable;
import org.springframework.ui.Model;
import org.w3c.dom.Attr;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;

class FilterControllerTest {

    CategoryService categoryService = Mockito.mock(CategoryService.class);
    ClothesService clothesService = Mockito.mock(ClothesService.class);
    FilterService filterService = Mockito.mock(FilterService.class);
    SortService sortService = Mockito.mock(SortService.class);
    Model model = Mockito.mock(Model.class);

    @Test
    void mainFilter() {
        FilterController filterController = new FilterController();
        filterController.setCategoryService(categoryService);
        filterController.setClothesService(clothesService);
        filterController.setFilterService(filterService);

        Pageable pageable = PageRequest.of(0, 8);
        Category category = new Category();
        category.setNameRus("name");
        Mockito.when(categoryService.findByNameEn(any())).thenReturn(category);
        Clothes clothes = new Clothes();
        clothes.setArticle("article");

        Map<String, String> str = new HashMap<>();
        str.put("key", "value");
        User user = new User();
        user.setName("name");
        Assertions.assertEquals( "main",
                filterController.mainFilter(str, model, pageable, user));

        Mockito.when(categoryService.findByNameEn(any())).thenReturn(null);
        Assertions.assertEquals( "main",
                filterController.mainFilter(str, model, pageable, user));
    }

    @Test
    void mainFilternotnullcat() {
        FilterController filterController = new FilterController();
        filterController.setCategoryService(categoryService);
        filterController.setClothesService(clothesService);
        filterController.setFilterService(filterService);
        filterController.setSortService(sortService);

        Pageable pageable = PageRequest.of(0, 8);
        Category category = new Category();
        category.setNameRus("name");
        Mockito.when(categoryService.findByNameEn(any())).thenReturn(category);
        Clothes clothes = new Clothes();
        clothes.setArticle("article");
        List<Clothes> cllist = new ArrayList<>();
        cllist.add(clothes);
        Page<Clothes> page = new PageImpl<Clothes>(cllist, pageable, 1l);

        Mockito.when(sortService.getClothesWithoutFilters(
                1,category, pageable, -1, -1)).
                thenReturn(page);
        Mockito.when(clothesService.getClothesPopular(pageable)).
                thenReturn(page);

        Map<String, String> str = new HashMap<>();
        str.put("category", "value");
        User user = new User();
        user.setName("name");
        Assertions.assertEquals( "main",
                filterController.mainFilter(str, model, pageable, user));
    }

    @Test
    void applyFilter() {
        FilterController filterController = new FilterController();
        filterController.setCategoryService(categoryService);
        filterController.setFilterService(filterService);
        filterController.setClothesService(clothesService);
        filterController.setSortService(sortService);

        Pageable pageable = PageRequest.of(0, 8);
        User user = new User();
        user.setName("name");
        Map<String, String> params = new HashMap<>();
        params.put("category", "value");
        Map<Attribute, List<String>> filters = new HashMap<>();
        Attribute attribute = new Attribute();
        attribute.setName("name");
        List<String> strlist = new ArrayList<>();
        strlist.add("str");
        filters.put(attribute, strlist);
        Clothes clothes = new Clothes();
        clothes.setArticle("article");
        List<Clothes> cllist = new ArrayList<>();
        cllist.add(clothes);
        Mockito.when(filterService.getFilters(params)).thenReturn(filters);
        Page<Clothes> page = new PageImpl<Clothes>(cllist, pageable, 1l);

        List<String> articles = new ArrayList<>();
        articles.add("str");
        int priceMin = 1;
        int priceMax = 10;
        Category category = new Category();
        category.setNameRus("name");

        Mockito.when(sortService.getClothesWithoutFilters(
                1, category, pageable, priceMin, priceMax)).thenReturn(page);

        Mockito.when(clothesService.findAllByArticleInOrderByPresenceDescVisitDesc(
                articles, pageable, priceMin, priceMax)).thenReturn(page);
        Mockito.when(clothesService.findAllByArticleInOrderByPresenceDescPriceAsc(
                articles, pageable, priceMin, priceMax)).thenReturn(page);
        Mockito.when(clothesService.findAllByArticleInOrderByPresenceDescPriceDesc(
                articles, pageable, priceMin, priceMax)).thenReturn(page);

        Assertions.assertEquals( "filterClothes",
                filterController.applyFilter(params, model, pageable, user));

    }

    @Test
    void applyFilterelse() {
        FilterController filterController = new FilterController();
        filterController.setCategoryService(categoryService);
        filterController.setFilterService(filterService);
        filterController.setClothesService(clothesService);
        filterController.setSortService(sortService);

        Pageable pageable = PageRequest.of(0, 8);
        User user = new User();
        user.setName("name");
        Map<String, String> params = new HashMap<>();
        params.put("sorting", "0");
        params.put("category", null);
        params.put("price_min", "1");
        params.put("price_max", "10");
        Map<Attribute, List<String>> filters = new HashMap<>();
        Attribute attribute = new Attribute();
        attribute.setName("name");
        List<String> strlist = new ArrayList<>();
        strlist.add("str");
        Clothes clothes = new Clothes();
        clothes.setArticle("article");
        List<Clothes> cllist = new ArrayList<>();
        cllist.add(clothes);
        Mockito.when(filterService.getFilters(params)).thenReturn(filters);
        Page<Clothes> page = new PageImpl<Clothes>(cllist, pageable, 1l);

        List<String> articles = new ArrayList<>();
        articles.add("str");
        int priceMin = 1;
        int priceMax = 10;
        Category category = new Category();
        category.setNameRus("name");

        Mockito.when(clothesService.findAllByArticleInOrderByPresenceDescVisitDesc(
                articles, pageable, priceMin, priceMax)).thenReturn(page);
        Mockito.when(sortService.getClothesWithoutFilters(
                1, category, pageable, priceMin, priceMax)).thenReturn(page);

        Assertions.assertEquals( "filterClothes",
                filterController.applyFilter(params, model, pageable, user));

    }

    @Test
    void applyFilterelse2() {
        FilterController filterController = new FilterController();
        filterController.setCategoryService(categoryService);
        filterController.setFilterService(filterService);
        filterController.setClothesService(clothesService);
        filterController.setSortService(sortService);

        Pageable pageable = PageRequest.of(0, 8);
        User user = new User();
        user.setName("name");
        Map<String, String> params = new HashMap<>();
        params.put("sorting", "0");
        params.put("category", null);
        params.put("price_min", "1");
        params.put("price_max", "10");
        Map<Attribute, List<String>> filters = new HashMap<>();
        Attribute attribute = new Attribute();
        attribute.setName("name");
        List<String> strlist = new ArrayList<>();
        strlist.add("str");
        Clothes clothes = new Clothes();
        clothes.setArticle("article");
        List<Clothes> cllist = new ArrayList<>();
        cllist.add(clothes);
        filters.put(attribute, strlist);

        AttributeValue attributeValue = new AttributeValue();
        attributeValue.setValue("val1");
        attributeValue.setClothes(clothes);
        List<AttributeValue> atlist = new ArrayList<>();
        atlist.add(attributeValue);
        Mockito.when(filterService.getFilters(params)).thenReturn(filters);
        Mockito.when(filterService.findArticleClothesWithFilter(
                any(), any())).thenReturn(atlist);
        Page<Clothes> page = new PageImpl<Clothes>(cllist, pageable, 1l);
        Category category = new Category();
        category.setNameRus("name");

        List<String> articles = new ArrayList<>();
        articles.add("str");
        int priceMin = 1;
        int priceMax = 10;

        Mockito.when(clothesService.findAllByArticleInOrderByPresenceDescVisitDesc(
                articles, pageable, priceMin, priceMax)).thenReturn(page);
        Mockito.when(sortService.getClothesWithoutFilters(
                1, category, pageable, priceMin, priceMax)).thenReturn(page);

        Assertions.assertEquals( "filterClothes",
                filterController.applyFilter(params, model, pageable, user));

    }
}