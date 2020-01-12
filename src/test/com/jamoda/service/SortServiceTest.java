package com.jamoda.service;

import com.jamoda.model.Category;
import com.jamoda.model.Clothes;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.data.domain.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SortServiceTest {

    ClothesService clothesService = Mockito.mock(ClothesService.class);
    CategoryService categoryService = Mockito.mock(CategoryService.class);

    @Test
    void sortClothes() {
        SortService sortService = new SortService();
        sortService.setCategoryService(categoryService);
        sortService.setClothesService(clothesService);

        Pageable pageable = PageRequest.of(0, 8);
        Clothes clothes = new Clothes();
        clothes.setArticle("article");
        List<Clothes> cllist = new ArrayList<>();
        cllist.add(clothes);
        Page<Clothes> page = new PageImpl<Clothes>(cllist, pageable, 1l);
        List<String> strlist = new ArrayList<>();
        strlist.add("str");
        int priceMin = 1;
        int priceMax = 2;


        Mockito.when(clothesService.findAllByArticleInOrderByPresenceDescVisitDesc(
            strlist, pageable, priceMin, priceMax)).thenReturn(page);
        Mockito.when(clothesService.findAllByArticleInOrderByPresenceDescPriceAsc(
                strlist, pageable, priceMin, priceMax)).thenReturn(page);
        Mockito.when(clothesService.findAllByArticleInOrderByPresenceDescPriceDesc(
                strlist, pageable, priceMin, priceMax)).thenReturn(page);

        Assertions.assertEquals(sortService.sortClothes(
                strlist, 1, pageable, priceMin, priceMax), page);
        Assertions.assertEquals(sortService.sortClothes(
                strlist, 2, pageable, priceMin, priceMax), page);
        Assertions.assertEquals(sortService.sortClothes(
                strlist, 3, pageable, priceMin, priceMax), page);
        Assertions.assertEquals(sortService.sortClothes(
                strlist, 4, pageable, priceMin, priceMax), page);
    }

    @Test
    void sortFullClothes() {
        SortService sortService = new SortService();
        sortService.setCategoryService(categoryService);
        sortService.setClothesService(clothesService);

        Pageable pageable = PageRequest.of(0, 8);
        Clothes clothes = new Clothes();
        clothes.setArticle("article");
        List<Clothes> cllist = new ArrayList<>();
        cllist.add(clothes);
        Page<Clothes> page = new PageImpl<Clothes>(cllist, pageable, 1l);
        List<String> strlist = new ArrayList<>();
        strlist.add("str");
        int priceMin = 1;
        int priceMax = 2;


        Mockito.when(clothesService.getClothesPopular(
                 pageable, priceMin, priceMax)).thenReturn(page);
        Mockito.when(clothesService.findAllByOrderByPresenceDescPriceAsc(
                 pageable, priceMin, priceMax)).thenReturn(page);
        Mockito.when(clothesService.findAllByOrderByPresenceDescPriceDesc(
                 pageable, priceMin, priceMax)).thenReturn(page);

        Assertions.assertEquals(sortService.sortFullClothes(
                 1, pageable, priceMin, priceMax), page);
        Assertions.assertEquals(sortService.sortFullClothes(
                 2, pageable, priceMin, priceMax), page);
        Assertions.assertEquals(sortService.sortFullClothes(
                 3, pageable, priceMin, priceMax), page);
        Assertions.assertEquals(sortService.sortFullClothes(
                 4, pageable, priceMin, priceMax), page);
    }

    @Test
    void sortWithCategory() {
        SortService sortService = new SortService();
        sortService.setCategoryService(categoryService);
        sortService.setClothesService(clothesService);

        Pageable pageable = PageRequest.of(0, 8);
        Category category = new Category();
        category.setNameRus("name");
        List<Category> clist = new ArrayList<>();
        clist.add(category);
        Clothes clothes = new Clothes();
        clothes.setArticle("article");
        List<Clothes> cllist = new ArrayList<>();
        cllist.add(clothes);
        Page<Clothes> page = new PageImpl<Clothes>(cllist, pageable, 1l);
        List<String> strlist = new ArrayList<>();
        strlist.add("str");
        int priceMin = 1;
        int priceMax = 2;


        Mockito.when(clothesService.findAllByCategoryInOrderByPresenceDescVisitDesc(
                clist, pageable, priceMin, priceMax)).thenReturn(page);
        Mockito.when(clothesService.findAllByCategoryInOrderByPresenceDescPriceAsc(
                clist, pageable, priceMin, priceMax)).thenReturn(page);
        Mockito.when(clothesService.findAllByCategoryInOrderByPresenceDescPriceDesc(
                clist, pageable, priceMin, priceMax)).thenReturn(page);
        Mockito.when(clothesService.getClothesPopular(
                 pageable, priceMin, priceMax)).thenReturn(page);

        Assertions.assertEquals(sortService.sortWithCategory(
                clist, 1, pageable, priceMin, priceMax), page);
        Assertions.assertEquals(sortService.sortWithCategory(
                clist, 2, pageable, priceMin, priceMax), page);
        Assertions.assertEquals(sortService.sortWithCategory(
                clist, 3, pageable, priceMin, priceMax), page);
        Assertions.assertEquals(sortService.sortWithCategory(
                clist, 4, pageable, priceMin, priceMax), page);
    }

    @Test
    void getClothesWithoutFilters() {
        SortService sortService = new SortService();
        sortService.setCategoryService(categoryService);
        sortService.setClothesService(clothesService);
        Pageable pageable = PageRequest.of(0, 8);
        Clothes clothes = new Clothes();
        clothes.setArticle("article");
        List<Clothes> cllist = new ArrayList<>();
        cllist.add(clothes);
        Page<Clothes> page = new PageImpl<Clothes>(cllist, pageable, 1l);
        List<String> strlist = new ArrayList<>();
        strlist.add("str");
        int priceMin = 1;
        int priceMax = 2;
        Category category = new Category();
        category.setNameRus("name");
        List<Category> clist = new ArrayList<>();
        clist.add(category);

        Mockito.when(categoryService.getChildrenCategory(category)).
                thenReturn(clist);

        Mockito.when(clothesService.getClothesPopular(
                pageable, priceMin, priceMax)).thenReturn(page);
        Mockito.when(clothesService.findAllByOrderByPresenceDescPriceAsc(
                pageable, priceMin, priceMax)).thenReturn(page);
        Mockito.when(clothesService.findAllByOrderByPresenceDescPriceDesc(
                pageable, priceMin, priceMax)).thenReturn(page);

        Assertions.assertEquals(sortService.getClothesWithoutFilters(
                0, null, pageable, priceMin, priceMax),
                sortService.sortFullClothes(
                        0,  pageable, priceMin, priceMax));

        Assertions.assertEquals(sortService.getClothesWithoutFilters(
                0, category, pageable, priceMin, priceMax),
                sortService.sortWithCategory(
                        clist, 1, pageable, priceMin, priceMax));
    }
}