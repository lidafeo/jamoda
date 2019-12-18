package com.jamoda.controller;

import com.jamoda.model.Cart;
import com.jamoda.model.Category;
import com.jamoda.repository.AttributeValueRepository;
import com.jamoda.repository.CategoryRepository;
import com.jamoda.repository.FilterRepository;
import com.jamoda.service.CartService;
import com.jamoda.service.CategoryService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.List;

//import static com.jamoda.controller.MainController.getModel;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

//@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest

class CartControllerTest {

    @Test
    void cart() throws Exception{
        Category category = new Category();
        category.setId(1L);
        List<Category> categories = List.of(category);
        CategoryService catServMock = mock(CategoryService.class);
        Mockito.when(catServMock.findMainCategory()).thenReturn(categories);

        Cart cart = new Cart();
        cart.setCount(1);
        Model model = mock(Model.class);
        model.addAttribute("0", 0);

        CartService cartServMock = mock(CartService.class);
        HttpSession session = mock(HttpSession.class);
        session.setAttribute("1", 1);
        Mockito.when(cartServMock.getCart(session)).thenReturn(cart);

        CartController cartController = new CartController();
        cartController.setCartService(cartServMock);
        cartController.setCategoryService(catServMock);

        verify(model, times(1)).addAttribute("0", 0);
        //verify(model, times(1)).addAttribute("1", cart);
        //почему не робит?
        //Assert.assertNotNull("model is null", model);
        Assert.assertEquals("cart wasn't returned","cart", cartController.cart(model, session));
    }
}