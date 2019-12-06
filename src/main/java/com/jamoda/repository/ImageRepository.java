package com.jamoda.repository;

import com.jamoda.model.Image;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepository extends CrudRepository<Image, Long> {
    List<Image> findAllByArticle(String article);

    Image findByArticle(String article);
}
