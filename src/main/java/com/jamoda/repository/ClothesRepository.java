package com.jamoda.repository;

import com.jamoda.model.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClothesRepository extends JpaRepository<Clothes, Integer> {
    Clothes findByArticle(String article);
    List<Clothes> findByCategory(Category category);
    List<Clothes> findAll();
    List<Clothes> findAllByCategoryIn(List<Category> categories);
    //@Query("SELECT * FROM Clothes order by visit ASC")
    //List<Clothes> findAllOrderByVisitAsc();
    Page<Clothes> findAllByOrderByPresenceDescVisitDesc(Pageable pageable);

    //for sort
    Page<Clothes> findAllByArticleInOrderByPresenceDescVisitDesc(List<String> articles, Pageable pageable);
    Page<Clothes> findAllByArticleInOrderByPresenceDescPriceDesc(List<String> articles, Pageable pageable);
    Page<Clothes> findAllByArticleInOrderByPresenceDescPriceAsc(List<String> articles, Pageable pageable);

    //for sort full
    Page<Clothes> findAllByOrderByPresenceDescPriceDesc(Pageable pageable);
    Page<Clothes> findAllByOrderByPresenceDescPriceAsc(Pageable pageable);

    //for sort with category
    Page<Clothes> findAllByCategoryInOrderByPresenceDescVisitDesc(List<Category> categories, Pageable pageable);
    Page<Clothes> findAllByCategoryInOrderByPresenceDescPriceDesc(List<Category> categories, Pageable pageable);
    Page<Clothes> findAllByCategoryInOrderByPresenceDescPriceAsc(List<Category> categories, Pageable pageable);

    Clothes findByAttributeGroupsContainsAndArticle(AttributeGroup attributeGroup, String article);
}
