package com.jamoda.repository;

import com.jamoda.model.Clothes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ClothesRepository extends JpaRepository<Clothes, Integer> {
    Clothes findByArticle(String article);
}
