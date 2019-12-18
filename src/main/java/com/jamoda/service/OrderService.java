package com.jamoda.service;

import com.jamoda.model.Order;
import com.jamoda.model.OrderProduct;
import com.jamoda.repository.OrderProductRepository;
import com.jamoda.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderProductRepository orderProductRepository;

    public Order saveOrder(Order order) {
        return orderRepository.saveAndFlush(order);
    }

    public OrderProduct saveOrderProduct(OrderProduct orderProduct) {
        return orderProductRepository.saveAndFlush(orderProduct);
    }
}
