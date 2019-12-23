package com.jamoda.controller;

import com.jamoda.model.Cart;
import com.jamoda.model.Category;
import com.jamoda.model.Order;
import com.jamoda.service.CategoryService;
import com.jamoda.service.ClothesService;
import com.jamoda.service.OrderService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpSession;
import java.util.List;

import static org.mockito.Mockito.*;

class OrderControllerTest {

    CategoryService catServMock = mock(CategoryService.class);
    ClothesService clothesServMock = mock(ClothesService.class);
    OrderService orderServMock = mock(OrderService.class);

    @Test
    void order() {
        Model model = Mockito.mock(Model.class);
        OrderController orderController = new OrderController();

        Model model1 = Mockito.mock(Model.class);
        model1.addAttribute("count", 1);
        model1.addAttribute("price", 2);

        Assert.assertEquals("parts/order",
                orderController.order(1, 1, model));

        Assert.assertEquals( model.containsAttribute("1"),
                model1.containsAttribute("1"));
        Assert.assertEquals( model.containsAttribute("2"),
                model1.containsAttribute("2"));
    }

    @Test
    void saveOrder() throws Exception{
        Order order = new Order();
        order.setPayment("online");
        Mockito.when(orderServMock.saveOrder(order)).thenReturn(order);

        Model model = mock(Model.class);
        model.addAttribute("0", 0);

        OrderController orderController = new OrderController();
        orderController.setOrderService(orderServMock);

        Assert.assertEquals("json",
                orderController.saveOrder(
                        "{\"cart\":" +
                                "[{\"article\":\"0001\"," +
                                "\"size\":\"44\"," +
                                "\"count\":1," +
                                "\"price\":1000}]," +
                                "\"price\":1000," +
                                "\"count\":1}",
                        order, model));
    }

    @Test
    void saveOrder1() throws Exception{
        Order order = new Order();
        order.setPayment("courier");
        Mockito.when(orderServMock.saveOrder(order)).thenReturn(order);

        Model model = mock(Model.class);
        model.addAttribute("0", 0);

        Mockito.when(orderServMock.checkProductInWarehouse(any())).
                thenReturn(false);

        OrderController orderController = new OrderController();
        orderController.setOrderService(orderServMock);

        Mockito.when(orderServMock.saveOrder(order)).
                thenReturn(order);

        Assert.assertEquals("json",
                orderController.saveOrder(
                        "{\"cart\":" +
                                "[{\"article\":\"0001\"," +
                                "\"size\":\"44\"," +
                                "\"count\":0," +
                                "\"price\":1000}]," +
                                "\"price\":0," +
                                "\"count\":1}",
                        order, model));
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
//        Mockito.when(cartServMock.getCart(session)).thenReturn(cart);

        OrderController orderController = new OrderController();
//        orderController.setCartService(cartServMock);
        orderController.setCategoryService(catServMock);

        verify(model, times(1)).addAttribute("0", 0);
//        Assert.assertEquals("order wasn't returned",
//                "order", orderController.order(model, session));
    }
}