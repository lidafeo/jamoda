package com.jamoda.controller;

import com.jamoda.model.Customer;
import com.jamoda.model.User;
import com.jamoda.service.CustomerService;
import com.jamoda.service.MainService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.ui.Model;

import static org.junit.jupiter.api.Assertions.*;

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
        Assertions.assertNotNull(cabinetController.cabinet(model, user));
        Assertions.assertEquals( "cabinet",
                cabinetController.cabinet(model, user));
    }
}