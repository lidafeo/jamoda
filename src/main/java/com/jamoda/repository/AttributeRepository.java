package com.jamoda.repository;

import com.jamoda.model.Attribute;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AttributeRepository extends JpaRepository<Attribute, Integer> {
    Attribute findByName(String name);
    Attribute findById (long id);
    List<Attribute> findAll();
}
