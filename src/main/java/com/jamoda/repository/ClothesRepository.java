package com.jamoda.repository;

import com.jamoda.model.Clothes;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ClothesRepository extends CrudRepository<Clothes, Integer> {
    Clothes findByArticle(String article);
    @Query("SELECT DISTINCT brand FROM Clothes")
    List<String> findDistinctBrand();
}
