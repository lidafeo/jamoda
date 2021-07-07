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

    CategoryService categoryService = Mockito.mock(CategoryService.class);
    Model model = Mockito.mock(Model.class);

    @Test
    void cart() {
        CartController cartController = new CartController();
        cartController.setCategoryService(categoryService);

        User user = new User();
        user.setLogin("qwerty");

        Assertions.assertEquals( "cart",
                cartController.cart(model,user));
    }
}