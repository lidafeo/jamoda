package com.jamoda.repository;

import com.jamoda.model.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Integer> {
    Category findByNameEnOrNameRusEquals(String nameEn, String nameRus);
    Category findByNameEn(String nameEn);
    Category findByNameRus(String nameRus);
    List<Category> findAll();

    List<Category> findAllByType(String type);
}
