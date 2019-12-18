package com.jamoda.service;

import com.jamoda.model.Clothes;
import com.jamoda.repository.ClothesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClothesService {

    @Autowired
    private ClothesRepository clothesRepository;

    public Iterable<Clothes> getClothesPopular() {
        return clothesRepository.findAllByOrderByVisitDesc();
    }

    public Clothes findByArticle(String article) {
        return clothesRepository.findByArticle(article);
    }

    public void saveClothes(Clothes clothes) {
        clothesRepository.saveAndFlush(clothes);
    }

    public List<Clothes> findAll() {
        return clothesRepository.findAll();
    }
}
