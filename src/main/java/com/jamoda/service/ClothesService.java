package com.jamoda.service;

import com.jamoda.model.AttributeGroup;
import com.jamoda.model.Category;
import com.jamoda.model.Clothes;
import com.jamoda.model.Warehouse;
import com.jamoda.repository.ClothesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Service
public class ClothesService {

    private ClothesRepository clothesRepository;

    public void saveClothes(Clothes clothes) {
        clothesRepository.saveAndFlush(clothes);
    }

    public Iterable<Clothes> getClothesPopular() {
        return clothesRepository.findAllByOrderByPresenceDescVisitDesc();
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

    public Map<String, Integer> getSizes(List<Warehouse> warehouses) {
        Map<String, Integer> sizes = new TreeMap<>();
        for(int i = 40; i <= 52; i+=2) {
            sizes.put(String.valueOf(i), 0);
        }
        for(Warehouse warehouse : warehouses) {
            sizes.put(String.valueOf(warehouse.getSize()), warehouse.getCount());
        }
        return sizes;
    }

    public Integer getCountProductInWarehouse(List<Warehouse> warehouses) {
        int count = 0;
        for(Warehouse warehouse : warehouses) {
            count += warehouse.getCount();
        }
        return count;
    }

    public Clothes findByAttributeGroupsContainsAndArticle(AttributeGroup attributeGroup, String article) {
        return clothesRepository.findByAttributeGroupsContainsAndArticle(attributeGroup, article);
    }

    @Autowired
    public void setClothesRepository(ClothesRepository clothesRepository) {
        this.clothesRepository = clothesRepository;
    }
}
