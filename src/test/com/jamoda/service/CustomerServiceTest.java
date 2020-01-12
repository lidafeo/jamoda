package com.jamoda.service;

import com.jamoda.model.Attribute;
import com.jamoda.model.Customer;
import com.jamoda.model.User;
import com.jamoda.repository.AttributeRepository;
import com.jamoda.repository.CustomerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class CustomerServiceTest {

    CustomerRepository custRepMock = Mockito.mock(CustomerRepository.class);

    @Test
    void saveCustomer() {
        CustomerService custServ = new CustomerService();
        custServ.setCustomerRepository(custRepMock);

        Customer a = new Customer();
        a.setName("1");

        when(custRepMock.saveAndFlush(a))
                .thenAnswer(i -> i.getArguments()[0]);
        Assertions.assertEquals(custServ.saveCustomer(a),
                custRepMock.saveAndFlush(a));
    }

    @Test
    void findByUser() {
        CustomerService custServ = new CustomerService();
        custServ.setCustomerRepository(custRepMock);

        User user = new User();
        user.setName("qwerty");
        Customer customer = new Customer();
        customer.setName("qwerty");
        Mockito.when(custRepMock.findByUser(user)).thenReturn(customer);

        Assertions.assertEquals(custServ.findByUser(user),
               customer);
    }

    @Test
    void findByEmail() {
        CustomerService custServ = new CustomerService();
        custServ.setCustomerRepository(custRepMock);

        User user = new User();
        user.setName("qwerty");
        Customer customer = new Customer();
        customer.setName("qwerty");
        Mockito.when(custRepMock.findByEmail("qwerty")).thenReturn(customer);

        Assertions.assertEquals(custServ.findByEmail("qwerty"),
                customer);
    }

    @Test
    void updateInfo() {
        CustomerService custServ = new CustomerService();
        custServ.setCustomerRepository(custRepMock);

        Customer customer1 = new Customer();
        customer1.setName("old");
        customer1.setPhone("1234567");
        customer1.setAddress("1234567");
        Customer customer2 = new Customer();
        customer2.setName("new");
        customer2.setPhone("1234567");
        customer2.setAddress("1234567");
        Mockito.when(custRepMock.saveAndFlush(customer1.setNewInfo(customer2))).
                thenReturn(customer2);

        Assertions.assertEquals(custServ.updateInfo(customer1, customer2),
                customer2);
    }
}