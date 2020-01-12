package com.jamoda.service;

import com.jamoda.model.*;
import com.jamoda.repository.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import com.jamoda.model.*;
import com.jamoda.repository.AttributeValueRepository;
import com.jamoda.repository.FilterRepository;
import com.jamoda.repository.OrderProductRepository;
import com.jamoda.repository.OrderRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.*;

class OrderServiceTest {

    OrderRepository orderRepMock = Mockito.mock(OrderRepository.class);
    OrderProductRepository orderProductRepMock = Mockito.mock(OrderProductRepository.class);
    CustomerRepository customerRepository = Mockito.mock(CustomerRepository.class);
    ClothesRepository clRepMock = Mockito.mock(ClothesRepository.class);
    WarehouseRepository whRepMock = Mockito.mock(WarehouseRepository.class);

    @Test
    void saveTest() {
        OrderService orderService = new OrderService();
        orderService.setOrderRepository(orderRepMock);
        orderService.setOrderProductRepository(orderProductRepMock);
        orderService.setCustomerRepository(customerRepository);

        Customer customer = new Customer();
        customer.setEmail("123");
        Order order = new Order();

        Mockito.when(customerRepository.findByEmail(order.getEmail())).thenReturn(customer);
        Assertions.assertEquals(orderRepMock.saveAndFlush(order),
                orderService.saveOrder(order));

        OrderProduct orderProduct = new OrderProduct();
        Assertions.assertEquals(orderProductRepMock.saveAndFlush(orderProduct),
                orderService.saveOrderProduct(orderProduct));
    }

    @Test
    void getOrderProduct() {
        OrderService orderService = new OrderService();
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
    }

    @Test
    void confirmOrder() {
        OrderService orderService = new OrderService();
        orderService.setOrderRepository(orderRepMock);

        Customer customer = new Customer();
        customer.setEmail("123");
        Order order = new Order();
        order.setName("name");
        Mockito.when(orderRepMock.findById(1)).thenReturn(order);
        Mockito.when(orderRepMock.saveAndFlush(order)).thenReturn(order);

        Assertions.assertEquals(orderService.confirmOrder(1),
                orderRepMock.saveAndFlush(order));
    }

    @Test
    void setCompletedOrder() {
        OrderService orderService = new OrderService();
        orderService.setOrderRepository(orderRepMock);

        Customer customer = new Customer();
        customer.setEmail("123");
        Order order = new Order();
        order.setName("name");
        Mockito.when(orderRepMock.findById(1)).thenReturn(order);
        Mockito.when(orderRepMock.saveAndFlush(order)).thenReturn(order);

        Assertions.assertEquals(orderService.setCompletedOrder(1),
                orderRepMock.saveAndFlush(order));
    }

    @Test
    void findbyId() {
        OrderService orderServ = new OrderService();
        orderServ.setOrderRepository(orderRepMock);

        Order order = new Order();
        order.setName("qwerty");
        Mockito.when(orderRepMock.findById(1)).thenReturn(order);

        Assertions.assertEquals(orderServ.findById(1),
                order);
    }

    @Test
    void getOrders() {
        OrderService orderService = new OrderService();
        orderService.setOrderRepository(orderRepMock);

        Pageable pageable = PageRequest.of(0, 8);
        Order order = new Order();
        order.setName("name");
        List<Order> olist = new ArrayList<>();
        olist.add(order);
        Page<Order> page = new PageImpl<Order>(olist, pageable, 1l);
        Mockito.when(orderRepMock.findAllByOrderByDateDesc(pageable)).
                thenReturn(page);

        Assertions.assertEquals(orderService.getOrders(pageable),
                orderRepMock.findAllByOrderByDateDesc(pageable));
    }

    @Test
    void checkProductInWarehouse() {
        OrderService orderService = new OrderService();
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
        orderService.setClothesRepository(clRepMock);
        orderService.setOrderProductRepository(orderProductRepMock);
        orderService.setWarehouseRepository(whRepMock);

        Clothes clothes = new Clothes();
        clothes.setArticle("123");
        Warehouse whh = new Warehouse();
        List<Warehouse> whl = new ArrayList<>();
        whl.add(whh);
        clothes.setWarehouses(whl);

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
        Mockito.when(whRepMock.findByClothesAndSize(op.getClothes(), op.getSize())).
                thenReturn(wh);
        Mockito.when(whRepMock.saveAndFlush(wh)).
                thenReturn(wh);
        Mockito.when(orderProductRepMock.saveAndFlush(op)).
                thenReturn(op);

        orderService.saveCart(cart, order);

        Assertions.assertEquals(orderProductRepMock.saveAndFlush(op), op);
    }
}