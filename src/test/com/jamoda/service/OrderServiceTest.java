package com.jamoda.service;

import com.jamoda.model.*;
import com.jamoda.repository.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.LinkedList;
import java.util.List;
import com.jamoda.model.*;
import com.jamoda.repository.AttributeValueRepository;
import com.jamoda.repository.FilterRepository;
import com.jamoda.repository.OrderProductRepository;
import com.jamoda.repository.OrderRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.*;

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

    @Test
    void getOrderProduct() {
        OrderService orderService = new OrderService();
        ClothesRepository clRepMock = Mockito.mock(ClothesRepository.class);
        orderService.setClothesRepository(clRepMock);
        Clothes clothes = new Clothes();
        clothes.setArticle("123");
        Mockito.when(clRepMock.findByArticle(any())).thenReturn(clothes);
        Order order = new Order();
        order.setSum(1000);
        order.setName("qwerty");
        List<OrderProduct> op = new LinkedList<>();
        op.add(new OrderProduct(1, 48, 1000, order, clothes));

        ProductInCart p = new ProductInCart(
                clothes.getArticle(), 48, 1);
        p.setPrice(1000);
        ProductInCart[] pp = {p};
        Cart cart = new Cart();
        cart.setCart(pp);
        List<OrderProduct> op1 = orderService.getOrderProduct(cart, order);
        Assertions.assertEquals((op.get(0)).getClothes(),
                (op1.get(0)).getClothes());
        Assertions.assertEquals((op.get(0)).getCount(),
                (op1.get(0)).getCount());
        Assertions.assertEquals((op.get(0)).getSize(),
                (op1.get(0)).getSize());
        Assertions.assertEquals((op.get(0)).getPrice(),
                (op1.get(0)).getPrice());
    }

    @Test
    void checkProductInWarehouse() {
        OrderService orderService = new OrderService();
        WarehouseRepository whRepMock = Mockito.mock(WarehouseRepository.class);
        orderService.setWarehouseRepository(whRepMock);

        Warehouse wh = new Warehouse();
        wh.setCount(2);
        Mockito.when(whRepMock.findAllByArticleAndSize(anyString(), anyInt())).
                thenReturn(wh);
        ProductInCart p = new ProductInCart(
                "123", 48, 1);
        ProductInCart[] pp = {p};
        Cart cart = new Cart();
        cart.setCart(pp);

        ProductInCart p1 = new ProductInCart(
                "123", 48, 3);
        ProductInCart[] pp1 = {p1};
        Cart cart1 = new Cart();
        cart1.setCart(pp1);

        Assertions.assertTrue(orderService.checkProductInWarehouse(cart));
        Assertions.assertFalse(orderService.checkProductInWarehouse(cart1));
    }

    @Test
    void saveCart() {
        OrderService orderService = new OrderService();
        ClothesRepository clRepMock = Mockito.mock(ClothesRepository.class);
        orderService.setClothesRepository(clRepMock);
        OrderProductRepository opRepMock = Mockito.mock(OrderProductRepository.class);
        orderService.setOrderProductRepository(opRepMock);
        WarehouseRepository whRepMock = Mockito.mock(WarehouseRepository.class);
        orderService.setWarehouseRepository(whRepMock);
        Clothes clothes = new Clothes();
        clothes.setArticle("123");
        Mockito.when(clRepMock.findByArticle(any())).thenReturn(clothes);
        Order order = new Order();
        order.setSum(1000);
        order.setName("qwerty");
        ProductInCart p = new ProductInCart(
                clothes.getArticle(), 48, 1);
        p.setPrice(1000);
        ProductInCart[] pp = {p};
        Cart cart = new Cart();
        cart.setCart(pp);
        cart.setCount(1);
        OrderProduct op = new OrderProduct(
                1, 48, 1000, order, clothes);
        int id = (int)op.getId();
        Warehouse wh = new Warehouse();
        wh.setCount(10);
        Mockito.when(whRepMock.findByClothesAndSize(clothes, 48)).thenReturn(wh);
        orderService.saveCart(cart, order);
        Assertions.assertNotNull(opRepMock.findById(id));
    }


}