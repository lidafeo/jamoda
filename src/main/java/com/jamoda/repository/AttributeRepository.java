package com.jamoda.repository;

import com.jamoda.model.Attribute;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AttributeRepository extends CrudRepository<Attribute, Integer> {
    Attribute findByName(String name);
    Attribute findById (long id);
    List<Attribute> findAll();
}
