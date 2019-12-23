package com.jamoda.repository;

import com.jamoda.model.Clothes;
import com.jamoda.model.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface WarehouseRepository extends JpaRepository<Warehouse, Integer> {
    Warehouse findByClothesAndSize(Clothes clothes, int size);
    @Query(value = "SELECT w from Warehouse w WHERE w.clothes.article=?1 AND w.size=?2")
    Warehouse findAllByArticleAndSize(String article, Integer size);
}
