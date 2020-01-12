package com.jamoda.controller.admin;

import com.jamoda.model.Order;
import com.jamoda.service.OrderService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

class OrderAdminControllerTest {

    OrderService orderService = Mockito.mock(OrderService.class);
    Model model = Mockito.mock(Model.class);

    @Test
    void pageListOrders() {
        OrderAdminController orderAdminController = new OrderAdminController();
        orderAdminController.setOrderService(orderService);

        Pageable pageable = PageRequest.of(0, 12);
        Order order = new Order();
        order.setName("name");
        List<Order> list = new ArrayList<>();
        list.add(order);
        Page<Order> page = new PageImpl<Order>(list, pageable, 1l);
        Mockito.when(orderService.getOrders(pageable)).thenReturn(page);

        Assertions.assertEquals( "admin/listOrders",
                orderAdminController.pageListOrders( model, pageable));

    }

    @Test
    void confirmOrder() {
        OrderAdminController orderAdminController = new OrderAdminController();
        orderAdminController.setOrderService(orderService);

        Pageable pageable = PageRequest.of(0, 12);
        Order order = new Order();
        order.setName("name");
        Mockito.when(orderService.confirmOrder(1)).thenReturn(order);

        Assertions.assertEquals( "json",
                orderAdminController.confirmOrder( model, 1));

        Mockito.when(orderService.confirmOrder(1)).thenReturn(null);
        Assertions.assertEquals( "json",
                orderAdminController.confirmOrder( model, 1));
    }
}