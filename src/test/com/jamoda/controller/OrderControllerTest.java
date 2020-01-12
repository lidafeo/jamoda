package com.jamoda.controller;

import com.jamoda.model.Cart;
import com.jamoda.model.Customer;
import com.jamoda.model.Order;
import com.jamoda.model.User;
import com.jamoda.service.CustomerService;
import com.jamoda.service.OrderService;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.ui.Model;

import static org.mockito.Mockito.*;

class OrderControllerTest {

    OrderService orderServMock = mock(OrderService.class);
    CustomerService customerService = Mockito.mock(CustomerService.class);
    Model model = Mockito.mock(Model.class);

    @Test
    void order() {
        Model model = Mockito.mock(Model.class);
        OrderController orderController = new OrderController();
        orderController.setCustomerService(customerService);

        User user = new User();
        user.setLogin("qwerty");
        Customer customer = new Customer();
        customer.setUser(user);

        Model model1 = Mockito.mock(Model.class);
        model1.addAttribute("count", 1);
        model1.addAttribute("price", 2);
        Mockito.when(customerService.findByUser(user)).thenReturn(customer);

        Assert.assertEquals("parts/order",
                orderController.order(1, 1, model, user));

        Assert.assertEquals( model.containsAttribute("1"),
                model1.containsAttribute("1"));
        Assert.assertEquals( model.containsAttribute("2"),
                model1.containsAttribute("2"));
    }

    @Test
    void ordernulluser() {
        Model model = Mockito.mock(Model.class);
        OrderController orderController = new OrderController();
        orderController.setCustomerService(customerService);

        User user = new User();
        user.setLogin("qwerty");
        Customer customer = new Customer();
        customer.setUser(user);

        Model model1 = Mockito.mock(Model.class);
        model1.addAttribute("count", 1);
        model1.addAttribute("price", 2);
        Mockito.when(customerService.findByUser(user)).thenReturn(customer);

        Assert.assertEquals("parts/order",
                orderController.order(1, 1, model, null));
    }


    @Test
    void saveOrder() throws Exception{
        Order order = new Order();
        Mockito.when(orderServMock.saveOrder(order)).thenReturn(order);
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
    void saveOrder0() throws Exception{
        Order order = new Order();
        order.setPayment("online");
        Mockito.when(orderServMock.saveOrder(order)).thenReturn(order);
        model.addAttribute("0", 0);
        OrderController orderController = new OrderController();
        orderController.setOrderService(orderServMock);
        Mockito.when(orderServMock.checkProductInWarehouse(any(Cart.class))).
                thenReturn(true);

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
    void saveOrder0countprice() throws Exception{
        Order order = new Order();
        Mockito.when(orderServMock.saveOrder(order)).thenReturn(order);
        model.addAttribute("0", 0);
        OrderController orderController = new OrderController();
        orderController.setOrderService(orderServMock);

        Assert.assertEquals("json",
                orderController.saveOrder(
                        "{\"cart\":" +
                                "[{\"article\":\"0001\"," +
                                "\"size\":\"44\"," +
                                "\"count\":0," +
                                "\"price\":0}]," +
                                "\"price\":0," +
                                "\"count\":1}",
                        order, model));
    }
}