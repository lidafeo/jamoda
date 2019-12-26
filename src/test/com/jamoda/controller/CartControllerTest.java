package com.jamoda.controller;

import com.jamoda.model.Cart;
import com.jamoda.model.User;
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

        User user = new User();
        user.setLogin("qwerty");
        Model model = Mockito.mock(Model.class);

        Assertions.assertNotNull(cartController.cart(model, user));
        Assertions.assertEquals( "cart",
                cartController.cart(model,user));
    }
}