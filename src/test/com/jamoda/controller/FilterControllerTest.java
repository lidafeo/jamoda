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

    @Test
    void mainFilter() {
        FilterController filterController = new FilterController();
        CategoryService categoryService = Mockito.mock(CategoryService.class);
        filterController.setCategoryService(categoryService);
        ClothesService clothesService = Mockito.mock(ClothesService.class);
        filterController.setClothesService(clothesService);
        FilterService filterService = Mockito.mock(FilterService.class);
        filterController.setFilterService(filterService);
        MainService mainService = Mockito.mock(MainService.class);
        filterController.setMainService(mainService);

        Model model = mock(Model.class);
        Pageable pageable = PageRequest.of(0, 8);
        Mockito.when(mainService.getSessionModel(model)).thenReturn(model);
        Category category = new Category();
        category.setNameRus("name");
        Mockito.when(categoryService.findByNameEn(any())).thenReturn(category);
        Clothes clothes = new Clothes();
        clothes.setArticle("article");
        //Mockito.when(clothesService.getClothesPopular(pageable)).thenReturn(clothes);

        Map<String, String> str = new HashMap<>();
        str.put("key", "value");
        User user = new User();
        user.setName("name");
        Assertions.assertEquals( "main",
                filterController.mainFilter(str, model, pageable, user));
    }

    @Test
    void mainFilternotnullcat() {
        FilterController filterController = new FilterController();
        CategoryService categoryService = Mockito.mock(CategoryService.class);
        filterController.setCategoryService(categoryService);
        ClothesService clothesService = Mockito.mock(ClothesService.class);
        filterController.setClothesService(clothesService);
        FilterService filterService = Mockito.mock(FilterService.class);
        filterController.setFilterService(filterService);
        MainService mainService = Mockito.mock(MainService.class);
        filterController.setMainService(mainService);

        Model model = mock(Model.class);
        Pageable pageable = PageRequest.of(0, 8);
        Mockito.when(mainService.getSessionModel(model)).thenReturn(model);
        Category category = new Category();
        category.setNameRus("name");
        Mockito.when(categoryService.findByNameEn(any())).thenReturn(category);
        Clothes clothes = new Clothes();
        clothes.setArticle("article");
        //Mockito.when(clothesService.getClothesPopular(pageable)).thenReturn(clothes);

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
        CategoryService categoryService = Mockito.mock(CategoryService.class);
        filterController.setCategoryService(categoryService);
        FilterService filterService = Mockito.mock(FilterService.class);
        filterController.setFilterService(filterService);
        ClothesService clothesService = Mockito.mock(ClothesService.class);
        filterController.setClothesService(clothesService);

        Model model = mock(Model.class);
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

        Mockito.when(clothesService.findAllByArticleInOrderByPresenceDescVisitDesc(
                articles, pageable, priceMin, priceMax)).thenReturn(page);
        Mockito.when(clothesService.findAllByArticleInOrderByPresenceDescPriceAsc(
                articles, pageable, priceMin, priceMax)).thenReturn(page);
        Mockito.when(clothesService.findAllByArticleInOrderByPresenceDescPriceDesc(
                articles, pageable, priceMin, priceMax)).thenReturn(page);

        //Page<Clothes> page = new GeoPage<Clothes>(pageable, 1l);

        Assertions.assertEquals( "filterClothes",
                filterController.applyFilter(params, model, pageable, user));

    }

    @Test
    void applyFilterelse() {
        FilterController filterController = new FilterController();
        CategoryService categoryService = Mockito.mock(CategoryService.class);
        filterController.setCategoryService(categoryService);
        FilterService filterService = Mockito.mock(FilterService.class);
        filterController.setFilterService(filterService);
        ClothesService clothesService = Mockito.mock(ClothesService.class);
        filterController.setClothesService(clothesService);

        Model model = mock(Model.class);
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

        Mockito.when(clothesService.findAllByArticleInOrderByPresenceDescVisitDesc(
                articles, pageable, priceMin, priceMax)).thenReturn(page);

        Assertions.assertEquals( "filterClothes",
                filterController.applyFilter(params, model, pageable, user));

    }

    @Test
    void applyFilterelse2() {
        FilterController filterController = new FilterController();
        CategoryService categoryService = Mockito.mock(CategoryService.class);
        filterController.setCategoryService(categoryService);
        FilterService filterService = Mockito.mock(FilterService.class);
        filterController.setFilterService(filterService);
        ClothesService clothesService = Mockito.mock(ClothesService.class);
        filterController.setClothesService(clothesService);

        Model model = mock(Model.class);
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

        List<String> articles = new ArrayList<>();
        articles.add("str");
        int priceMin = 1;
        int priceMax = 10;

        Mockito.when(clothesService.findAllByArticleInOrderByPresenceDescVisitDesc(
                articles, pageable, priceMin, priceMax)).thenReturn(page);

        Assertions.assertEquals( "filterClothes",
                filterController.applyFilter(params, model, pageable, user));

    }

    @Test
    void sortClothes() {
        FilterController filterController = new FilterController();
        ClothesService clothesService = Mockito.mock(ClothesService.class);
        filterController.setClothesService(clothesService);

        Clothes clothes = new Clothes();
        clothes.setArticle("article");
        List<Clothes> cllist = new ArrayList<>();
        cllist.add(clothes);
        Pageable pageable = PageRequest.of(0, 8);
        Page<Clothes> page = new PageImpl<Clothes>(cllist, pageable, 1l);
        List<String> articles = new ArrayList<>();
        articles.add("str");
        int priceMin = 1;
        int priceMax = 10;
        Mockito.when(clothesService.findAllByArticleInOrderByPresenceDescVisitDesc(
                articles, pageable, priceMin, priceMax)).thenReturn(page);
        Mockito.when(clothesService.findAllByArticleInOrderByPresenceDescPriceAsc(
                articles, pageable, priceMin, priceMax)).thenReturn(page);
        Mockito.when(clothesService.findAllByArticleInOrderByPresenceDescPriceDesc(
                articles, pageable, priceMin, priceMax)).thenReturn(page);

        Assertions.assertEquals( page,
                filterController.sortClothes(
                        articles, 1, pageable, priceMin, priceMax));
        Assertions.assertEquals( page,
                filterController.sortClothes(
                        articles, 2, pageable, priceMin, priceMax));
        Assertions.assertEquals( page,
                filterController.sortClothes(
                        articles, 3, pageable, priceMin, priceMax));
        Assertions.assertEquals( page,
                filterController.sortClothes(
                        articles, 4, pageable, priceMin, priceMax));
    }

    @Test
    void sortFullClothes() {
        FilterController filterController = new FilterController();
        ClothesService clothesService = Mockito.mock(ClothesService.class);
        filterController.setClothesService(clothesService);

        Clothes clothes = new Clothes();
        clothes.setArticle("article");
        List<Clothes> cllist = new ArrayList<>();
        cllist.add(clothes);
        Pageable pageable = PageRequest.of(0, 8);
        Page<Clothes> page = new PageImpl<Clothes>(cllist, pageable, 1l);
        Category category = new Category();
        category.setNameRus("name");
        int priceMin = 1;
        int priceMax = 10;
        Mockito.when(clothesService.getClothesPopular(
                pageable, priceMin, priceMax)).thenReturn(page);
        Mockito.when(clothesService.findAllByOrderByPresenceDescPriceAsc(
                pageable, priceMin, priceMax)).thenReturn(page);
        Mockito.when(clothesService.findAllByOrderByPresenceDescPriceDesc(
                pageable, priceMin, priceMax)).thenReturn(page);

        Assertions.assertEquals( page,
                filterController.sortFullClothes(
                         1, pageable, priceMin, priceMax));
        Assertions.assertEquals( page,
                filterController.sortFullClothes(
                         2, pageable, priceMin, priceMax));
        Assertions.assertEquals( page,
                filterController.sortFullClothes(
                         3, pageable, priceMin, priceMax));
        Assertions.assertEquals( page,
                filterController.sortFullClothes(
                         4, pageable, priceMin, priceMax));
    }

    @Test
    void sortWithCategory() {
        FilterController filterController = new FilterController();
        ClothesService clothesService = Mockito.mock(ClothesService.class);
        filterController.setClothesService(clothesService);

        Clothes clothes = new Clothes();
        clothes.setArticle("article");
        List<Clothes> cllist = new ArrayList<>();
        cllist.add(clothes);
        Category category = new Category();
        category.setNameRus("name");
        List<Category> categories = new ArrayList<>();
        categories.add(category);

        Pageable pageable = PageRequest.of(0, 8);
        Page<Clothes> page = new PageImpl<Clothes>(cllist, pageable, 1l);
        int priceMin = 1;
        int priceMax = 10;
        Mockito.when(clothesService.findAllByCategoryInOrderByPresenceDescVisitDesc(
               categories,  pageable, priceMin, priceMax)).thenReturn(page);
        Mockito.when(clothesService.findAllByCategoryInOrderByPresenceDescPriceAsc(
                categories, pageable, priceMin, priceMax)).thenReturn(page);
        Mockito.when(clothesService.findAllByCategoryInOrderByPresenceDescPriceDesc(
                categories, pageable, priceMin, priceMax)).thenReturn(page);
        Mockito.when(clothesService.getClothesPopular(
                pageable, priceMin, priceMax)).thenReturn(page);

        Assertions.assertEquals( page,
                filterController.sortWithCategory(
                        categories, 1, pageable, priceMin, priceMax));
        Assertions.assertEquals( page,
                filterController.sortWithCategory(
                        categories, 2, pageable, priceMin, priceMax));
        Assertions.assertEquals( page,
                filterController.sortWithCategory(
                      categories,3, pageable, priceMin, priceMax));
        Assertions.assertEquals( page,
                filterController.sortWithCategory(
                     categories, 4, pageable, priceMin, priceMax));
    }

    @Test
    void getClothesWithoutFilters() {
    }
}