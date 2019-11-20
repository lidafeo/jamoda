package com.jamoda.repository;

import com.jamoda.model.Clothes;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ClothesRepository extends CrudRepository<Clothes, Integer> {
    Clothes findByArticle(String article);
}
