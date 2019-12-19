package com.jamoda.service;

import com.jamoda.model.Order;
import com.jamoda.model.OrderProduct;
import com.jamoda.repository.OrderProductRepository;
import com.jamoda.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private OrderRepository orderRepository;
    private OrderProductRepository orderProductRepository;

    public Order saveOrder(Order order) {
        return orderRepository.saveAndFlush(order);
    }

    public OrderProduct saveOrderProduct(OrderProduct orderProduct) {
        return orderProductRepository.saveAndFlush(orderProduct);
    }

    @Autowired
    public void setOrderRepository(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }
    @Autowired
    public void setOrderProductRepository(OrderProductRepository orderProductRepository) {
        this.orderProductRepository = orderProductRepository;
    }
}
