//package com.jamoda.controller;
//
//import com.jamoda.model.Category;
//import com.jamoda.model.Clothes;
//import com.jamoda.service.CategoryService;
//import com.jamoda.service.ClothesService;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class FilterControllerTest {
//
//    @Test
//    void mainFilter() {
//    }
//
//    @Test
//    void applyFilter() {
//    }
//
//    @Test
//    void sortClothes() {
//    }
//
//    @Test
//    void sortFullClothes() {
//    }
//
//    @Test
//    void sortWithCategory() {
//    }
//
//    @Test
//    void getClothesWithoutFilters() {
//        FilterController filterController = new FilterController();
//        CategoryService categoryService = Mockito.mock(CategoryService.class);
//        filterController.setCategoryService(categoryService);
//        ClothesService clothesService = Mockito.mock(ClothesService.class);
//        filterController.setClothesService(clothesService);
//        Category category = new Category();
//        category.setId(1);
//        Clothes clothes = new Clothes();
//        clothes.setName("qwerty");
//        Page<Clothes> pcl = new Page<Clothes>();
//
//        Pageable pageable = PageRequest.of(1, 6);
//        Mockito.when(clothesService.getClothesPopular(pageable)).
//                thenReturn(clothes);
//        Assertions.assertEquals(
//                filterController.getClothesWithoutFilters(0, null, pageable),
//                category);
//    }
//}