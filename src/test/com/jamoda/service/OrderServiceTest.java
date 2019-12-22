package com.jamoda.service;

import com.jamoda.model.Order;
import com.jamoda.model.OrderProduct;
import com.jamoda.repository.OrderProductRepository;
import com.jamoda.repository.OrderRepository;
import com.jamoda.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class OrderServiceTest {

    @Test
    void saveTest() {
        OrderService orderService = new OrderService();
        OrderRepository orderRepMock = Mockito.mock(OrderRepository.class);
        OrderProductRepository orderProductRepMock = Mockito.mock(OrderProductRepository.class);
        orderService.setOrderRepository(orderRepMock);
        orderService.setOrderProductRepository(orderProductRepMock);

        Order order = new Order();
        Assertions.assertEquals(orderRepMock.saveAndFlush(order),
                orderService.saveOrder(order));

        OrderProduct orderProduct = new OrderProduct();
        Assertions.assertEquals(orderProductRepMock.saveAndFlush(orderProduct),
                orderService.saveOrderProduct(orderProduct));
    }
}