package com.jamoda.repository;

import com.jamoda.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    Category findByNameEnOrNameRusEquals(String nameEn, String nameRus);
    Category findByNameEn(String nameEn);
    Category findByNameRus(String nameRus);
    Category findById(Long id);
    List<Category> findAll();
    List<Category> findAllByParent(Category parent);
    List<Category> findAllByParentIn(List<Category> parent);

    List<Category> findAllByType(String type);
}
