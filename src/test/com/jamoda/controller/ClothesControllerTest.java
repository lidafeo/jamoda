package com.jamoda.controller;

import com.jamoda.model.Category;
import com.jamoda.model.Clothes;
import com.jamoda.model.User;
import com.jamoda.service.CategoryService;
import com.jamoda.service.ClothesService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static org.mockito.Mockito.mock;

class ClothesControllerTest {

    CategoryService categoryService = Mockito.mock(CategoryService.class);
    ClothesService clothesService = Mockito.mock(ClothesService.class);
    Model model = Mockito.mock(Model.class);

    @Test
    void getClothes() {
        ClothesController clothesController = new ClothesController();
        clothesController.setCategoryService(categoryService);
        clothesController.setClothesService(clothesService);

        User user = new User();
        user.setLogin("qwerty");
        Category category = new Category();
        category.setNameRus("name");
        Clothes clothes = new Clothes();
        clothes.setName("cl");
        clothes.setCategory(category);
        Map<String, Integer> sizes = new TreeMap<>();
        sizes.put("40", 0);
        Mockito.when(clothesService.findByArticle("qwerty")).thenReturn(clothes);
        Mockito.when(clothesService.getSizes(clothes.getWarehouses())).
                thenReturn(sizes);
        Mockito.when(clothesService.getCountProductInWarehouse(clothes.getWarehouses())).
                thenReturn(10);

        List<Category> catlist = new ArrayList<>();
        catlist.add(category);
        Mockito.when(categoryService.findMainCategory()).thenReturn(catlist);

        Assertions.assertEquals( "clothes",
                clothesController.getClothes("qwerty", model, user));

        Mockito.when(clothesService.findByArticle("qwerty")).thenReturn(null);
        Assertions.assertEquals( "clothes",
                clothesController.getClothes("qwerty", model, user));
    }
}