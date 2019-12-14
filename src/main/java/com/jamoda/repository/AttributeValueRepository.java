package com.jamoda.repository;

import com.jamoda.model.Attribute;
import com.jamoda.model.AttributeValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AttributeValueRepository extends JpaRepository<AttributeValue, Integer>, AttributeValueCustom {
    AttributeValue findById(long id);
    @Query(value = "SELECT DISTINCT value from AttributeValue WHERE attribute=?1")
    List<String> findDistinctValueByAttribute(Attribute attribute);
    //List<AttributeValue> findDistinctByValueAndAttribute(Attribute attribute);
}
