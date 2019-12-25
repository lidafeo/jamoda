package com.jamoda.repository;


import com.jamoda.model.Customer;
import com.jamoda.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer findByUser(User user);
}
