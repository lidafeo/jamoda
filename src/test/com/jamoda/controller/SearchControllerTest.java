package com.jamoda.controller;

import com.jamoda.model.Category;
import com.jamoda.model.Clothes;
import com.jamoda.model.User;
import com.jamoda.repository.ClothesRepository;
import com.jamoda.service.CategoryService;
import com.jamoda.service.ClothesService;
//import com.jamoda.service.MainService;
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

    CategoryService categoryService = Mockito.mock(CategoryService.class);
    ClothesService clothesService = Mockito.mock(ClothesService.class);
    SearchService searchService = Mockito.mock(SearchService.class);
    Model model = Mockito.mock(Model.class);


    @Test
    void ordernull() {
        SearchController searchController = new SearchController();
        searchController.setCategoryService(categoryService);
        searchController.setClothesService(clothesService);
        searchController.setSearchService(searchService);

        User user = new User();
        user.setName("name");
        Category category = new Category();
        category.setId(1);
        List<Category> catlist = new ArrayList<>();
        catlist.add(category);
        Mockito.when(categoryService.findMainCategory()).thenReturn(catlist);
        Pageable pageable = PageRequest.of(0, 8);

        Assertions.assertEquals( "search",
                searchController.order("", model, pageable, user));
    }

    @Test
    void ordernotnull() {
        SearchController searchController = new SearchController();
        searchController.setCategoryService(categoryService);
        searchController.setClothesService(clothesService);
        searchController.setSearchService(searchService);
        Category category = new Category();
        category.setId(1);
        List<Category> catlist = new ArrayList<>();
        catlist.add(category);
        Mockito.when(categoryService.findMainCategory()).thenReturn(catlist);
        Clothes clothes = new Clothes();
        clothes.setArticle("article");
        Mockito.when(clothesService.findByArticle(any())).thenReturn(clothes);
        Pageable pageable = PageRequest.of(0, 8);
        String[] str = {"q"};
        User user = new User();
        user.setName("name");

        Assertions.assertEquals( "redirect:/clothes?article=article",
                searchController.order("q", model, pageable, user));
    }

    @Test
    void order() {
        SearchController searchController = new SearchController();
        searchController.setCategoryService(categoryService);
        searchController.setClothesService(clothesService);
        searchController.setSearchService(searchService);
        Category category = new Category();
        category.setId(1);
        List<Category> catlist = new ArrayList<>();
        catlist.add(category);
        Mockito.when(categoryService.findMainCategory()).thenReturn(catlist);
        Clothes clothes = new Clothes();
        clothes.setArticle("article");
        Mockito.when(clothesService.findByArticle(any())).thenReturn(null);
        Pageable pageable = PageRequest.of(0, 8);
        String[] str = {"q"};

        User user = new User();
        user.setName("name");
        List<Clothes> cllist = new ArrayList<>();
        cllist.add(clothes);
        Page<Clothes> page = new PageImpl<>(cllist, pageable, 1l);
        Mockito.when(searchService.findClothesByQ("q", pageable)).thenReturn(page);

        Assertions.assertEquals( "search",
                searchController.order("q", model, pageable, user));
    }

}