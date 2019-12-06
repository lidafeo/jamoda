package com.jamoda.repository;

import com.jamoda.model.AttributeGroup;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AttributeGroupRepository extends CrudRepository<AttributeGroup, Integer> {
    AttributeGroup findByName(String name);

    AttributeGroup findById(long id);
}
