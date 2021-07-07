package com.jamoda.repository;

import com.jamoda.model.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    Order findById(String id);
    Order findById(int id);
    Page<Order> findAllByOrderByDateDesc(Pageable pageable);
}
