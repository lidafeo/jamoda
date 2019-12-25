package com.jamoda.controller;

import com.jamoda.controller.admin.ImageAdminController;
import com.jamoda.model.Clothes;
import com.jamoda.service.ClothesService;
import com.jamoda.service.MainService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.ui.Model;

import java.util.Map;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class ClothesControllerTest {

    @Test
    void getClothes() {
        ClothesController clothesController = new ClothesController();
        MainService mainService = Mockito.mock(MainService.class);
        clothesController.setMainService(mainService);
        ClothesService clothesService = Mockito.mock(ClothesService.class);
        clothesController.setClothesService(clothesService);

        Model model = mock(Model.class);
        Clothes clothes = new Clothes();
        clothes.setName("cl");
        Map<String, Integer> sizes = new TreeMap<>();
        sizes.put("40", 0);
        Mockito.when(clothesService.findByArticle("qwerty")).thenReturn(clothes);
        Mockito.when(clothesService.getSizes(clothes.getWarehouses())).
                thenReturn(sizes);
        Mockito.when(clothesService.getCountProductInWarehouse(clothes.getWarehouses())).
                thenReturn(10);


        Assertions.assertNotNull(clothesController.getClothes(
                "qwerty", model));
        Assertions.assertEquals( "clothes",
                clothesController.getClothes("qwerty", model));
    }
}