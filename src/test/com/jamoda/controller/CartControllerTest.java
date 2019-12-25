package com.jamoda.controller;

import com.jamoda.model.Cart;
import com.jamoda.service.CategoryService;
import org.junit.experimental.categories.Categories;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.ui.Model;

import static org.junit.jupiter.api.Assertions.*;

class CartControllerTest {

    @Test
    void cart() {
        CartController cartController = new CartController();
        CategoryService categoryService = Mockito.mock(CategoryService.class);
        cartController.setCategoryService(categoryService);

        Model model = Mockito.mock(Model.class);

        Assertions.assertNotNull(cartController.cart(model));
        Assertions.assertEquals( "cart",
                cartController.cart(model));
    }
}