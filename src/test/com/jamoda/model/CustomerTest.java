package com.jamoda.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {

    @Test
    void Customer() {
        User user = new User();
        user.setName("name");
        Customer customer = new Customer(user, "email");
        customer.setName("name1");
        Assertions.assertEquals(customer.getName(), "name1");
        customer.setPhone("1234567");
        Assertions.assertEquals(customer.getPhone(), "1234567");
        customer.setAddress("address");
        Assertions.assertEquals(customer.getAddress(), "address");
        customer.setUser(user);
        Assertions.assertEquals(customer.getUser(), user);
        Order order = new Order();
        order.setSum(1000);
        List<Order> olist = new ArrayList<Order>();
        olist.add(order);
        customer.setOrders(olist);
        Assertions.assertEquals(customer.getOrders(), olist);
        customer.setEmail("email");
        Assertions.assertEquals(customer.getEmail(), "email");
    }
}