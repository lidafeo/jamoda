package com.jamoda.service;

import com.jamoda.model.AttributeGroup;
import com.jamoda.model.Category;
import com.jamoda.model.Clothes;
import com.jamoda.repository.ClothesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClothesService {

    private ClothesRepository clothesRepository;

    public Clothes saveClothes(Clothes clothes) {
        return clothesRepository.saveAndFlush(clothes);
    }

    public Iterable<Clothes> getClothesPopular() {
        return clothesRepository.findAllByOrderByVisitDesc();
    }

    public Clothes findByArticle(String article) {
        return clothesRepository.findByArticle(article);
    }

    public List<Clothes> findAll() {
        return clothesRepository.findAll();
    }

    public List<Clothes> findAllByCategoryIn(List<Category> categories) {
        return clothesRepository.findAllByCategoryIn(categories);
    }

    public Clothes findByAttributeGroupsContainsAndArticle(AttributeGroup attributeGroup, String article) {
        return clothesRepository.findByAttributeGroupsContainsAndArticle(attributeGroup, article);
    }

    @Autowired
    public void setClothesRepository(ClothesRepository clothesRepository) {
        this.clothesRepository = clothesRepository;
    }
}
