package com.jamoda.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.validation.constraints.Max;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {

    @Test
    void Order() {
        Order order = new Order();
        order.setName("name");
        Assertions.assertEquals(order.getName(), "name");
        order.setPhone("1234567");
        Assertions.assertEquals(order.getPhone(), "1234567");
        order.setEmail("email");
        Assertions.assertEquals(order.getEmail(), "email");
        order.setAddress("address");
        Assertions.assertEquals(order.getAddress(), "address");
        order.setComment("comment");
        Assertions.assertEquals(order.getComment(), "comment");
        order.setSum(1000);
        Assertions.assertEquals(order.getSum(), (Integer) 1000);
        order.setPaid(true);
        Assertions.assertEquals(order.getPaid(), true);
        order.setCompleted(true);
        Assertions.assertEquals(order.getCompleted(), true);
        OrderProduct op = new OrderProduct();
        op.setPrice(1000);
        List<OrderProduct> oplist = new ArrayList<OrderProduct>();
        oplist.add(op);
        order.setProducts(oplist);
        Assertions.assertEquals(order.getProducts(), oplist);
        Customer customer = new Customer();
        customer.setName("name");
        order.setCustomer(customer);
        Assertions.assertEquals(order.getCustomer(), customer);
    }
}