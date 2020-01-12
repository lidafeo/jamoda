package com.jamoda.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.jaxb.SpringDataJaxb;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.persistence.EntityNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class OrderProductTest {

    @Test
    void Count() {
        OrderProduct op = new OrderProduct();
        op.setCount(2);
        Assertions.assertEquals(op.getCount(), (Integer)2);
        op.setSize(40);
        Assertions.assertEquals(op.getSize(), (Integer)40);
        Clothes clothes = new Clothes();
        clothes.setName("qwerty");
        op.setClothes(clothes);
        Assertions.assertEquals(op.getClothes(), clothes);
        Order order = new Order();
        order.setName("qwerty");
        op.setOrder(order);
        Assertions.assertEquals(op.getOrder(), order);
        op.setPrice(1000);
        Assertions.assertEquals(op.getPrice(), (Integer)1000);
        Assertions.assertEquals(op.checkClothesExists(), true);
//        boolean is;
//        try {
//            op.checkClothesExists();
//            is = false;
//        } catch (EntityNotFoundException thrown) {
//            is = true;
//        }
//        Assertions.assertFalse(is);
    }

}