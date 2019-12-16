package com.jamoda.repository;

import com.jamoda.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    Order findById(String id);
}
