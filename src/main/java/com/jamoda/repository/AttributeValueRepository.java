package com.jamoda.repository;

import com.jamoda.model.AttributeValue;
import org.springframework.data.repository.CrudRepository;

public interface AttributeValueRepository extends CrudRepository<AttributeValue, Integer> {
    AttributeValue findById(long id);
}
