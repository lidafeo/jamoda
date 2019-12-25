package com.jamoda.service;

import com.jamoda.model.Customer;
import com.jamoda.model.User;
import com.jamoda.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    private CustomerRepository customerRepository;

    public Customer saveCustomer(Customer customer) {
        return customerRepository.saveAndFlush(customer);
    }

    public Customer findByUser(User user) {
        return customerRepository.findByUser(user);
    }

    @Autowired
    public void setCustomerRepository(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }
}
