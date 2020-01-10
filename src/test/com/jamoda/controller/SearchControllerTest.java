package com.jamoda.controller;

import com.jamoda.model.Category;
import com.jamoda.model.Clothes;
import com.jamoda.repository.ClothesRepository;
import com.jamoda.service.CategoryService;
import com.jamoda.service.ClothesService;
import com.jamoda.service.MainService;
import com.jamoda.service.SearchService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;

class SearchControllerTest {

    @Test
    void ordernull() {
        SearchController searchController = new SearchController();
        CategoryService categoryService = Mockito.mock(CategoryService.class);
        searchController.setCategoryService(categoryService);
        ClothesService clothesService = Mockito.mock(ClothesService.class);
        searchController.setClothesService(clothesService);
        SearchService searchService = Mockito.mock(SearchService.class);
        searchController.setSearchService(searchService);

        Category category = new Category();
        category.setId(1);
        List<Category> catlist = new ArrayList<>();
        catlist.add(category);
        Mockito.when(categoryService.findMainCategory()).thenReturn(catlist);
        Model model = mock(Model.class);
        Pageable pageable = PageRequest.of(0, 8);

        Assertions.assertEquals( "search",
                searchController.order("", model, pageable));
    }

    @Test
    void ordernotnull() {
        SearchController searchController = new SearchController();
        CategoryService categoryService = Mockito.mock(CategoryService.class);
        searchController.setCategoryService(categoryService);
        ClothesService clothesService = Mockito.mock(ClothesService.class);
        searchController.setClothesService(clothesService);
        SearchService searchService = Mockito.mock(SearchService.class);
        searchController.setSearchService(searchService);
        ClothesRepository clothesRepository = Mockito.mock(ClothesRepository.class);
        Category category = new Category();
        category.setId(1);
        List<Category> catlist = new ArrayList<>();
        catlist.add(category);
        Mockito.when(categoryService.findMainCategory()).thenReturn(catlist);
        Clothes clothes = new Clothes();
        clothes.setArticle("article");
        Mockito.when(clothesService.findByArticle(any())).thenReturn(clothes);
        Model model = mock(Model.class);
        Pageable pageable = PageRequest.of(0, 8);
        String[] str = {"q"};

        Assertions.assertEquals( "redirect:/clothes?article=article",
                searchController.order("q", model, pageable));
    }

    @Test
    void order() {
        SearchController searchController = new SearchController();
        CategoryService categoryService = Mockito.mock(CategoryService.class);
        searchController.setCategoryService(categoryService);
        ClothesService clothesService = Mockito.mock(ClothesService.class);
        searchController.setClothesService(clothesService);
        SearchService searchService = Mockito.mock(SearchService.class);
        searchController.setSearchService(searchService);
        ClothesRepository clothesRepository = Mockito.mock(ClothesRepository.class);
        Category category = new Category();
        category.setId(1);
        List<Category> catlist = new ArrayList<>();
        catlist.add(category);
        Mockito.when(categoryService.findMainCategory()).thenReturn(catlist);
        Clothes clothes = new Clothes();
        clothes.setArticle("article");
        Mockito.when(clothesService.findByArticle(any())).thenReturn(null);
        Model model = mock(Model.class);
        Pageable pageable = PageRequest.of(0, 8);
        String[] str = {"q"};

        List<Clothes> cllist = new ArrayList<>();
        cllist.add(clothes);
        Page<Clothes> page = new PageImpl<>(cllist, pageable, 1l);
        Mockito.when(searchService.findClothesByQ("q", pageable)).thenReturn(page);

        Assertions.assertEquals( "search",
                searchController.order("q", model, pageable));
    }

}