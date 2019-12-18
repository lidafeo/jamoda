package com.jamoda.controller;

import com.jamoda.model.Cart;
import com.jamoda.model.Category;
import com.jamoda.model.Order;
import com.jamoda.service.CartService;
import com.jamoda.service.CategoryService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class OrderControllerTest {

    @Test
    void order() {
    }

    @Test
    void saveOrder() {
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

        OrderController orderController = new OrderController();
        orderController.setCartService(cartServMock);
        orderController.setCategoryService(catServMock);

        verify(model, times(1)).addAttribute("0", 0);
        Assert.assertEquals("order wasn't returned","order", orderController.order(model, session));

//        String summa = new String("summa");
//        Order order = new Order();
//        order.setPayment("online");
//        Assert.assertTrue(order.getPaid());

    }
}