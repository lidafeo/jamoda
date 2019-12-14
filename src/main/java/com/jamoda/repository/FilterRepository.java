package com.jamoda.repository;

import com.jamoda.model.Attribute;
import com.jamoda.model.Filter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FilterRepository extends JpaRepository<Filter, Integer> {
    Filter findByNameEnOrNameOrAttribute(String nameEn, String name, Attribute attribute);
    List<Filter> findAllByActive(boolean active);
    Filter findByNameEn(String nameEn);
}
