package com.jamoda.repository;

import com.jamoda.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
    List<Image> findAllByArticle(String article);
    Image findByArticle(String article);
    List<Image> findAll();
}
