package com.jamoda.service;

import com.jamoda.model.Category;
import com.jamoda.model.Clothes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SortService {

    private ClothesService clothesService;
    private CategoryService categoryService;

    public Page<Clothes> sortClothes(List<String> articles, int sort, Pageable pageable, int priceMin, int priceMax) {
        switch (sort) {
            case 1:
                return clothesService.findAllByArticleInOrderByPresenceDescVisitDesc(articles, pageable, priceMin, priceMax);
            case 2:
                return clothesService.findAllByArticleInOrderByPresenceDescPriceAsc(articles, pageable, priceMin, priceMax);
            case 3:
                return clothesService.findAllByArticleInOrderByPresenceDescPriceDesc(articles, pageable, priceMin, priceMax);
            default:
                return clothesService.findAllByArticleInOrderByPresenceDescVisitDesc(articles, pageable, priceMin, priceMax);
        }
    }

    public Page<Clothes> sortFullClothes(int sort, Pageable pageable, int priceMin, int priceMax) {
        switch (sort) {
            case 1:
                return clothesService.getClothesPopular(pageable, priceMin, priceMax);
            case 2:
                return clothesService.findAllByOrderByPresenceDescPriceAsc(pageable, priceMin, priceMax);
            case 3:
                return clothesService.findAllByOrderByPresenceDescPriceDesc(pageable, priceMin, priceMax);
            default:
                return clothesService.getClothesPopular(pageable, priceMin, priceMax);
        }
    }

    //for sort with category
    public Page<Clothes> sortWithCategory(List<Category> categories, int sort, Pageable pageable, int priceMin, int priceMax) {
        switch (sort) {
            case 1:
                return clothesService.findAllByCategoryInOrderByPresenceDescVisitDesc(categories, pageable, priceMin, priceMax);
            case 2:
                return clothesService.findAllByCategoryInOrderByPresenceDescPriceAsc(categories, pageable, priceMin, priceMax);
            case 3:
                return clothesService.findAllByCategoryInOrderByPresenceDescPriceDesc(categories, pageable, priceMin, priceMax);
            default:
                return clothesService.getClothesPopular(pageable, priceMin, priceMax);
        }
    }


    public Page<Clothes> getClothesWithoutFilters(int sort, Category category, Pageable pageable, int priceMin, int priceMax) {
        if(sort == 0) {
            sort = 1;
        }
        if(category == null) {
            return sortFullClothes(sort, pageable, priceMin, priceMax);
        }
        return sortWithCategory(categoryService.getChildrenCategory(category), sort, pageable, priceMin, priceMax);
    }

    @Autowired
    public void setClothesService(ClothesService clothesService) {
        this.clothesService = clothesService;
    }
    @Autowired
    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
}
