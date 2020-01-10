package com.jamoda.controller;

import com.jamoda.model.Customer;
import com.jamoda.model.Order;
import com.jamoda.model.User;
import com.jamoda.service.CustomerService;
import com.jamoda.service.MainService;
import com.jamoda.service.OrderService;
import com.jamoda.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.ui.Model;

import javax.persistence.ManyToOne;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

class CabinetControllerTest {

    @Test
    void cabinet() {
        CabinetController cabinetController = new CabinetController();
        MainService mainService = Mockito.mock(MainService.class);
        cabinetController.setMainService(mainService);
        CustomerService customerService = Mockito.mock(CustomerService.class);
        cabinetController.setCustomerService(customerService);

        User user = new User();
        user.setLogin("qwerty");
        Customer customer = new Customer();
        customer.setUser(user);
        Model model = Mockito.mock(Model.class);

        Mockito.when(customerService.findByUser(user)).thenReturn(customer);
       Assertions.assertEquals( "cabinet",
                cabinetController.cabinet(model, user));
    }

    @Test
    void editProfileNullCustomer() {
        CabinetController cabinetController = new CabinetController();
        MainService mainService = Mockito.mock(MainService.class);
        cabinetController.setMainService(mainService);
        CustomerService customerService = Mockito.mock(CustomerService.class);
        cabinetController.setCustomerService(customerService);

        User user = new User();
        user.setLogin("qwerty");
        Customer customer = new Customer();
        customer.setUser(user);
        Model model = Mockito.mock(Model.class);

        Mockito.when(customerService.findByEmail(user.getLogin())).
                thenReturn(null);
        Assertions.assertEquals( "cabinet",
                cabinetController.editProfile(customer, user, model));
    }

    @Test
    void getDetailOrder() {
        CabinetController cabinetController = new CabinetController();
        OrderService orderService = Mockito.mock(OrderService.class);
        cabinetController.setOrderService(orderService);
        CustomerService customerService = Mockito.mock(CustomerService.class);
        cabinetController.setCustomerService(customerService);

        User user = new User();
        user.setLogin("qwerty");
        Customer customer = new Customer();
        customer.setUser(user);
        Order order = new Order();
        order.setName("name");
        order.setCustomer(customer);
        Model model = Mockito.mock(Model.class);

        Mockito.when(customerService.findByUser(user)).thenReturn(customer);
        Mockito.when(orderService.findById(1)).thenReturn(order);

        Assertions.assertEquals( "parts/detail",
                cabinetController.getDetailOrder(1, user, model));
    }

    @Test
    void registerusernotnull() {
        CabinetController cabinetController = new CabinetController();
        UserService userService = Mockito.mock(UserService.class);
        cabinetController.setUserService(userService);
        CustomerService customerService = Mockito.mock(CustomerService.class);
        cabinetController.setCustomerService(customerService);

        User user = new User();
        user.setLogin("qwerty");
        Customer customer = new Customer();
        customer.setUser(user);
        Order order = new Order();
        order.setName("name");
        order.setCustomer(customer);
        Model model = Mockito.mock(Model.class);

        Mockito.when(userService.findByLogin(user.getLogin())).
                thenReturn(user);

        Assertions.assertEquals( "registration",
                cabinetController.register( user, model));
    }

    @Test
    void registerusernull() {
        CabinetController cabinetController = new CabinetController();
        UserService userService = Mockito.mock(UserService.class);
        cabinetController.setUserService(userService);
        CustomerService customerService = Mockito.mock(CustomerService.class);
        cabinetController.setCustomerService(customerService);

        User user = new User();
        user.setLogin("qwerty");
        Customer customer = new Customer();
        customer.setUser(user);
        Order order = new Order();
        order.setName("name");
        order.setCustomer(customer);
        Model model = Mockito.mock(Model.class);

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