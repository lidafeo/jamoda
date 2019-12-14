package com.jamoda.repository;

import com.jamoda.model.Category;
import com.jamoda.model.Clothes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ClothesRepository extends JpaRepository<Clothes, Integer> {
    Clothes findByArticle(String article);
    List<Clothes> findByCategory(Category category);
    List<Clothes> findAll();
    List<Clothes> findAllByCategoryIn(List<Category> categories);
    //@Query("SELECT * FROM Clothes order by visit ASC")
    //List<Clothes> findAllOrderByVisitAsc();
    List<Clothes> findAllByOrderByVisitDesc();
}
