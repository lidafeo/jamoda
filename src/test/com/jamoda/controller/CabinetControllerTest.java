package com.jamoda.controller;

import com.jamoda.model.*;
import com.jamoda.service.CategoryService;
import com.jamoda.service.CustomerService;
import com.jamoda.service.OrderService;
import com.jamoda.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.ui.Model;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;

class CabinetControllerTest {

    CustomerService customerService = Mockito.mock(CustomerService.class);
    CategoryService categoryService = Mockito.mock(CategoryService.class);
    OrderService orderService = Mockito.mock(OrderService.class);
    UserService userService = Mockito.mock(UserService.class);
    Model model = Mockito.mock(Model.class);

    @Test
    void cabinet() {
        CabinetController cabinetController = new CabinetController();
        cabinetController.setCustomerService(customerService);
        cabinetController.setCategoryService(categoryService);

        User user = new User();
        user.setLogin("qwerty");
        Customer customer = new Customer();
        customer.setUser(user);
        Category category = new Category();
        category.setNameRus("name");
        List<Category> catlist = new ArrayList<>();
        catlist.add(category);

        Mockito.when(customerService.findByUser(user)).thenReturn(customer);
        Mockito.when(categoryService.findMainCategory()).thenReturn(catlist);
       Assertions.assertEquals( "cabinet",
                cabinetController.cabinet(model, user));
    }

    @Test
    void editProfileNullCustomer() {
        CabinetController cabinetController = new CabinetController();
        cabinetController.setCategoryService(categoryService);
        cabinetController.setCustomerService(customerService);

        User user = new User();
        user.setLogin("qwerty");
        Customer customer = new Customer();
        customer.setUser(user);
        Category category = new Category();
        List<Category> catlist = new ArrayList<>();
        catlist.add(category);
        Mockito.when(customerService.findByEmail(user.getLogin())).
                thenReturn(null);
        Mockito.when(categoryService.findMainCategory()).
                thenReturn(catlist);
        Assertions.assertEquals( "cabinet",
                cabinetController.editProfile(customer, user, model));

        Mockito.when(customerService.findByEmail(user.getLogin())).
                thenReturn(customer);
        Assertions.assertEquals( "cabinet",
                cabinetController.editProfile(customer, user, model));
    }

    @Test
    void getDetailOrder() {
        CabinetController cabinetController = new CabinetController();
        cabinetController.setOrderService(orderService);
        cabinetController.setCustomerService(customerService);

        User user = new User();
        user.setLogin("qwerty");
        user.setRoles(Collections.singleton(Role.ADMIN));
        Customer customer = new Customer();
        customer.setUser(user);
        Order order = new Order();
        order.setName("name");
        order.setCustomer(customer);

        Mockito.when(customerService.findByUser(user)).thenReturn(customer);
        Mockito.when(orderService.findById(1)).thenReturn(order);

        Assertions.assertEquals( "parts/detail",
                cabinetController.getDetailOrder(1, user, model));

        user.setRoles(Collections.singleton(Role.USER));
        Assertions.assertEquals( "parts/detail",
                cabinetController.getDetailOrder(1, user, model));
    }

    @Test
    void setCompletedOrder() {
        CabinetController cabinetController = new CabinetController();
        cabinetController.setOrderService(orderService);

        Order order = new Order();
        order.setName("name");

        Mockito.when(orderService.setCompletedOrder(1)).thenReturn(order);
        Assertions.assertEquals( "json",
                cabinetController.setCompletedOrder(1, model));

        Mockito.when(orderService.setCompletedOrder(1)).thenReturn(null);
        Assertions.assertEquals( "json",
                cabinetController.setCompletedOrder(1, model));
    }

    @Test
    void registerusernotnull() {
        CabinetController cabinetController = new CabinetController();
        cabinetController.setUserService(userService);
        cabinetController.setCustomerService(customerService);

        User user = new User();
        user.setLogin("qwerty");
        Customer customer = new Customer();
        customer.setUser(user);
        Order order = new Order();
        order.setName("name");
        order.setCustomer(customer);

        Mockito.when(userService.findByLogin(user.getLogin())).
                thenReturn(user);

        Assertions.assertEquals( "registration",
                cabinetController.register( user, model));
    }

    @Test
    void registerusernull() {
        CabinetController cabinetController = new CabinetController();
        cabinetController.setUserService(userService);
        cabinetController.setCustomerService(customerService);

        User user = new User();
        user.setLogin("qwerty");
        Customer customer = new Customer();
        customer.setUser(user);
        Order order = new Order();
        order.setName("name");
        order.setCustomer(customer);

        Mockito.when(userService.findByLogin(user.getLogin())).
                thenReturn(null);
        Mockito.when(userService.saveUser(user)).
                thenReturn(user);
        Mockito.when(customerService.saveCustomer(any())).
                thenReturn(customer);

        Assertions.assertEquals( "redirect:/login",
                cabinetController.register( user, model));
    }

}