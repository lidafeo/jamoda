package com.jamoda.service;

import com.jamoda.model.*;
import com.jamoda.repository.ClothesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Service
public class ClothesService {

    private ClothesRepository clothesRepository;

    public void saveClothes(Clothes clothes) {
        clothesRepository.saveAndFlush(clothes);
    }

    public Integer getMaxPriceAllClothes() {
        return clothesRepository.findMaxPrice();
    }

    public Integer getMaxPriceByClothesIn(List<Category> categories) {
        return clothesRepository.findMaxPriceByCategoryIn(categories);
    }

    public Page<Clothes> getClothesPopular(Pageable pageable) {
        return clothesRepository.findAllByOrderByPresenceDescVisitDesc(pageable);
    }
    public Page<Clothes> getClothesPopular(Pageable pageable, int priceMin, int priceMax) {
        if(priceMin == -1 || priceMax == -1) {
            return getClothesPopular(pageable);
        }
        return clothesRepository.findAllByPriceBetweenOrderByPresenceDescVisitDesc(priceMin, priceMax, pageable);
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

    public Page<Clothes> findAllByArticleInOrderByPresenceDescVisitDesc(List<String> articles, Pageable pageable) {
        return clothesRepository.findAllByArticleInOrderByPresenceDescVisitDesc(articles, pageable);
    }
    public Page<Clothes> findAllByArticleInOrderByPresenceDescVisitDesc(List<String> articles, Pageable pageable, int priceMin, int priceMax) {
        if(priceMin == -1 || priceMax == -1) {
            return findAllByArticleInOrderByPresenceDescVisitDesc(articles, pageable);
        }
        return clothesRepository.findAllByArticleInAndPriceBetweenOrderByPresenceDescVisitDesc(articles, priceMin, priceMax, pageable);
    }

    public Page<Clothes> findAllByArticleInOrderByPresenceDescPriceDesc(List<String> articles, Pageable pageable) {
        return clothesRepository.findAllByArticleInOrderByPresenceDescPriceDesc(articles, pageable);
    }
    public Page<Clothes> findAllByArticleInOrderByPresenceDescPriceDesc(List<String> articles, Pageable pageable, int priceMin, int priceMax) {
        if(priceMin == -1 || priceMax == -1) {
            return findAllByArticleInOrderByPresenceDescPriceDesc(articles, pageable);
        }
        return clothesRepository.findAllByArticleInAndPriceBetweenOrderByPresenceDescPriceDesc(articles, priceMin, priceMax, pageable);
    }

    public Page<Clothes> findAllByArticleInOrderByPresenceDescPriceAsc(List<String> articles, Pageable pageable) {
        return clothesRepository.findAllByArticleInOrderByPresenceDescPriceAsc(articles, pageable);
    }
    public Page<Clothes> findAllByArticleInOrderByPresenceDescPriceAsc(List<String> articles, Pageable pageable, int priceMin, int priceMax) {
        if(priceMin == -1 || priceMax == -1) {
            return findAllByArticleInOrderByPresenceDescPriceAsc(articles, pageable);
        }
        return clothesRepository.findAllByArticleInAndPriceBetweenOrderByPresenceDescPriceAsc(articles, priceMin, priceMax, pageable);
    }

    public Page<Clothes> findAllByOrderByPresenceDescPriceDesc(Pageable pageable) {
        return clothesRepository.findAllByOrderByPresenceDescPriceDesc(pageable);
    }
    public Page<Clothes> findAllByOrderByPresenceDescPriceDesc(Pageable pageable, int priceMin, int priceMax) {
        if(priceMin == -1 || priceMax == -1) {
            return findAllByOrderByPresenceDescPriceDesc(pageable);
        }
        return clothesRepository.findAllByPriceBetweenOrderByPresenceDescPriceDesc(priceMin, priceMax, pageable);
    }

    public List<Clothes> findClothesPresence() {
        return clothesRepository.findAllByPresence(true);
    }
    public Page<Clothes> findAllByOrderByPresenceDescPriceAsc(Pageable pageable) {
        return clothesRepository.findAllByOrderByPresenceDescPriceAsc(pageable);
    }
    public Page<Clothes> findAllByOrderByPresenceDescPriceAsc(Pageable pageable, int priceMin, int priceMax) {
        if(priceMin == -1 || priceMax == -1) {
            return findAllByOrderByPresenceDescPriceAsc(pageable);
        }
        return clothesRepository.findAllByPriceBetweenOrderByPresenceDescPriceAsc(priceMin, priceMax, pageable);
    }

    public Page<Clothes> findAllByCategoryInOrderByPresenceDescVisitDesc(List<Category> categories, Pageable pageable) {
        return clothesRepository.findAllByCategoryInOrderByPresenceDescVisitDesc(categories, pageable);
    }
    public Page<Clothes> findAllByCategoryInOrderByPresenceDescVisitDesc(List<Category> categories, Pageable pageable, int priceMin, int priceMax) {
        if(priceMin == -1 || priceMax == -1) {
            return findAllByCategoryInOrderByPresenceDescVisitDesc(categories, pageable);
        }
        return clothesRepository.findAllByCategoryInAndPriceBetweenOrderByPresenceDescVisitDesc(categories, priceMin, priceMax, pageable);
    }

    public Page<Clothes> findAllByCategoryInOrderByPresenceDescPriceDesc(List<Category> categories, Pageable pageable) {
        return clothesRepository.findAllByCategoryInOrderByPresenceDescPriceDesc(categories, pageable);
    }
    public Page<Clothes> findAllByCategoryInOrderByPresenceDescPriceDesc(List<Category> categories, Pageable pageable, int priceMin, int priceMax) {
        if(priceMin == -1 || priceMax == -1) {
            return findAllByCategoryInOrderByPresenceDescPriceDesc(categories, pageable);
        }
        return clothesRepository.findAllByCategoryInAndPriceBetweenOrderByPresenceDescPriceDesc(categories, priceMin, priceMax, pageable);
    }

    public Page<Clothes> findAllByCategoryInOrderByPresenceDescPriceAsc(List<Category> categories, Pageable pageable) {
        return clothesRepository.findAllByCategoryInOrderByPresenceDescPriceAsc(categories, pageable);
    }
    public Page<Clothes> findAllByCategoryInOrderByPresenceDescPriceAsc(List<Category> categories, Pageable pageable, int priceMin, int priceMax) {
        if(priceMin == -1 || priceMax == -1) {
            return findAllByCategoryInOrderByPresenceDescPriceAsc(categories, pageable);
        }
        return clothesRepository.findAllByCategoryInAndPriceBetweenOrderByPresenceDescPriceAsc(categories, priceMin, priceMax, pageable);
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
