package com.jamoda.controller;

import com.jamoda.model.Cart;
import com.jamoda.model.Category;
import com.jamoda.model.Order;
import com.jamoda.service.CartService;
import com.jamoda.service.CategoryService;
import com.jamoda.service.OrderService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;
import java.util.List;

import static org.mockito.Mockito.*;

class OrderControllerTest {

    CategoryService catServMock = mock(CategoryService.class);
    CartService cartServMock = mock(CartService.class);
    OrderService orderServMock = mock(OrderService.class);

    @Test
    void order() {
        Model model = mock(Model.class);
        HttpSession session = mock(HttpSession.class);

        OrderController orderController = new OrderController();
        orderController.setCartService(cartServMock);
        orderController.setCategoryService(catServMock);

        Assert.assertEquals("order wasn't returned",
                "order", orderController.order(model, session));
    }

    @Test
    void saveOrder() throws Exception{
        Category category = new Category();
        category.setId(1L);
        List<Category> categories = List.of(category);
        Mockito.when(catServMock.findMainCategory()).thenReturn(categories);

        Order order = new Order();
        order.setPayment("online");
        Mockito.when(orderServMock.saveOrder(order)).thenReturn(order);

        Cart cart = new Cart();
        cart.setCount(1);
        cart.setPrice(10);
        Model model = mock(Model.class);
        model.addAttribute("0", 0);

        HttpSession session = mock(HttpSession.class);
        session.setAttribute("1", 1);
        Mockito.when(cartServMock.getCart(session)).thenReturn(cart);

        OrderController orderController = new OrderController();
        orderController.setCartService(cartServMock);
        orderController.setCategoryService(catServMock);

//        orderController.saveOrder("summa", order, model, session);
        verify(model, times(1)).addAttribute("0", 0);
       // Assert.assertEquals("order sum must be cart sum","10", order.getSum());
        Assert.assertEquals("order wasn't returned",
                "order", orderController.saveOrder("summa", order, model, session));
        Assert.assertEquals("orderPaid must be true",
                "true", order.getPaid());
    }

    @Test
    void saveOrder2() {
        Category category = new Category();
        category.setId(1L);
        List<Category> categories = List.of(category);
        Mockito.when(catServMock.findMainCategory()).thenReturn(categories);

        Cart cart = new Cart();
        cart.setCount(1);
        Model model = mock(Model.class);
        model.addAttribute("0", 0);

        Order order = new Order();
//        order.;
//
        HttpSession session = mock(HttpSession.class);
        session.setAttribute("1", 1);
        Mockito.when(cartServMock.getCart(session)).thenReturn(cart);

        OrderController orderController = new OrderController();
        orderController.setCartService(cartServMock);
        orderController.setCategoryService(catServMock);

        verify(model, times(1)).addAttribute("0", 0);
        Assert.assertEquals("order wasn't returned",
                "order", orderController.order(model, session));
    }
}