package com.jamoda.service;

import com.jamoda.model.*;
import com.jamoda.repository.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

class ClothesServiceTest {

    @Test
    void saveClothesTest() {
        ClothesService clServMock = new ClothesService();
        ClothesRepository clRepMock = Mockito.mock(ClothesRepository.class);
        clServMock.setClothesRepository(clRepMock);

        Clothes clothes = new Clothes();
//        when(clRepMock.saveAndFlush(clothes))
//                .thenAnswer(i -> i.getArguments()[0]);
        Assertions.assertEquals(clServMock.saveClothes(clothes),
                clRepMock.saveAndFlush(clothes));
    }

    @Test
    void getClothesPopularTest() {
        ClothesService clServMock = new ClothesService();
        ClothesRepository clRepMock = Mockito.mock(ClothesRepository.class);
        clServMock.setClothesRepository(clRepMock);
        Pageable pageable = PageRequest.of(0, 8);

        Assertions.assertEquals(
                clServMock.getClothesPopular(pageable),
                clRepMock.findAllByOrderByPresenceDescVisitDesc(pageable));
    }

    @Test
    void findByArticleTest() {
        ClothesService clServMock = new ClothesService();
        ClothesRepository clRepMock = Mockito.mock(ClothesRepository.class);
        clServMock.setClothesRepository(clRepMock);

        Assertions.assertEquals(
                clServMock.findByArticle("1"),
                clRepMock.findByArticle("1"));
    }

    @Test
    void findAllTest() {
        ClothesService clServMock = new ClothesService();
        ClothesRepository clRepMock = Mockito.mock(ClothesRepository.class);
        clServMock.setClothesRepository(clRepMock);

        Assertions.assertEquals(clServMock.findAll(), clRepMock.findAll());
    }

    @Test
    void getClothesPopular() {
        ClothesService clServMock = new ClothesService();
        ClothesRepository clRepMock = Mockito.mock(ClothesRepository.class);
        clServMock.setClothesRepository(clRepMock);
        Pageable pageable = PageRequest.of(0, 8);

        Assertions.assertEquals(clServMock.getClothesPopular(pageable),
                clRepMock.findAllByOrderByPresenceDescVisitDesc(pageable));
    }

    @Test
    void findAllByCategoryInTest() {
        ClothesService clServMock = new ClothesService();
        ClothesRepository clRepMock = Mockito.mock(ClothesRepository.class);
        clServMock.setClothesRepository(clRepMock);

        Category category = new Category();
        category.setId(1L);
        List<Category> categories = List.of(category);

        Assertions.assertEquals(
                clServMock.findAllByCategoryIn(categories),
                clRepMock.findAllByCategoryIn(categories));
    }

    @Test
    void findAllByArticleInOrderByPresenceDescVisitDesc() {
        ClothesService clServMock = new ClothesService();
        ClothesRepository clRepMock = Mockito.mock(ClothesRepository.class);
        clServMock.setClothesRepository(clRepMock);

        List<String> articles = List.of("qwerty");
        Pageable pageable = PageRequest.of(0, 8);

        Assertions.assertEquals(
                clServMock.findAllByArticleInOrderByPresenceDescVisitDesc(articles, pageable),
                clRepMock.findAllByArticleInOrderByPresenceDescVisitDesc(articles, pageable));
    }

    @Test
    void findAllByArticleInOrderByPresenceDescPriceDesc() {
        ClothesService clServMock = new ClothesService();
        ClothesRepository clRepMock = Mockito.mock(ClothesRepository.class);
        clServMock.setClothesRepository(clRepMock);

        List<String> articles = List.of("qwerty");
        Pageable pageable = PageRequest.of(0, 8);

        Assertions.assertEquals(
                clServMock.findAllByArticleInOrderByPresenceDescPriceDesc(articles, pageable),
                clRepMock.findAllByArticleInOrderByPresenceDescPriceDesc(articles, pageable));
    }

    @Test
    void findAllByArticleInOrderByPresenceDescPriceAsc() {
        ClothesService clServMock = new ClothesService();
        ClothesRepository clRepMock = Mockito.mock(ClothesRepository.class);
        clServMock.setClothesRepository(clRepMock);

        List<String> articles = List.of("qwerty");
        Pageable pageable = PageRequest.of(0, 8);

        Assertions.assertEquals(
                clServMock.findAllByArticleInOrderByPresenceDescPriceAsc(articles, pageable),
                clRepMock.findAllByArticleInOrderByPresenceDescPriceAsc(articles, pageable));
    }

    @Test
    void findByAttributeGroupsContainsAndArticleTest() {
        ClothesService clServMock = new ClothesService();
        ClothesRepository clRepMock = Mockito.mock(ClothesRepository.class);
        clServMock.setClothesRepository(clRepMock);

        List<AttributeGroup> atglist = new ArrayList<>();
        AttributeGroup atg = new AttributeGroup();
        atg.setName("1");
        atglist.add(atg);
        Clothes clothes = new Clothes();
        clothes.setArticle("qwerty");
        clothes.setAttributeGroups(atglist);

        Assertions.assertEquals(
                clServMock.findByAttributeGroupsContainsAndArticle(atg, "1"),
                null);
    }
    @Test
    void getCountProductInWarehouse() {
        ClothesService clServMock = new ClothesService();
        ClothesRepository clRepMock = Mockito.mock(ClothesRepository.class);
        clServMock.setClothesRepository(clRepMock);

        Clothes clothes = new Clothes();
        clothes.setArticle("123");
        Warehouse wh = new Warehouse(clothes, 48, 1);
        List<Warehouse> whs = List.of(wh);

        Integer i = 1;
        Assertions.assertEquals(
                clServMock.getCountProductInWarehouse(whs),
                i);

    }

    @Test
    void getSizes() {
        ClothesService clServMock = new ClothesService();
        ClothesRepository clRepMock = Mockito.mock(ClothesRepository.class);
        clServMock.setClothesRepository(clRepMock);

        Clothes clothes = new Clothes();
        clothes.setArticle("123");
        Warehouse wh = new Warehouse(clothes, 48, 1);
        List<Warehouse> whs = List.of(wh);

        Map<String, Integer> sizes = new TreeMap<>();
        sizes.put("40", 0);
        sizes.put("42", 0);
        sizes.put("44", 0);
        sizes.put("46", 0);
        sizes.put("48", 1);
        sizes.put("50", 0);
        sizes.put("52", 0);

        Assertions.assertEquals(clServMock.getSizes(whs),
                sizes);
    }

    @Test
    void findByAttributeGroupsContainsAndArticle() {
        ClothesService clServMock = new ClothesService();
        ClothesRepository clRepMock = Mockito.mock(ClothesRepository.class);
        clServMock.setClothesRepository(clRepMock);

        AttributeGroup atg = new AttributeGroup();
        atg.setName("1");
        Clothes clothes = new Clothes();
        clothes.setArticle("123");

        Mockito.when(clRepMock.findByAttributeGroupsContainsAndArticle(
                any(), any())).thenReturn(clothes);
        Assertions.assertEquals(
                clServMock.findByAttributeGroupsContainsAndArticle(
                        atg, "123"),
                clRepMock.findByAttributeGroupsContainsAndArticle(
                        atg, "123"));
    }

    @Test
    void findAllByOrderByPresenceDescPriceDesc() {
        ClothesService clServMock = new ClothesService();
        ClothesRepository clRepMock = Mockito.mock(ClothesRepository.class);
        clServMock.setClothesRepository(clRepMock);
        Pageable pageable = PageRequest.of(0, 8);

        Assertions.assertEquals(clServMock.findAllByOrderByPresenceDescPriceDesc(pageable),
                clRepMock.findAllByOrderByPresenceDescPriceDesc(pageable));
    }

    @Test
    void findAllByOrderByPresenceDescPriceAsc() {
        ClothesService clServMock = new ClothesService();
        ClothesRepository clRepMock = Mockito.mock(ClothesRepository.class);
        clServMock.setClothesRepository(clRepMock);
        Pageable pageable = PageRequest.of(0, 8);

        Assertions.assertEquals(clServMock.findAllByOrderByPresenceDescPriceAsc(pageable),
                clRepMock.findAllByOrderByPresenceDescPriceAsc(pageable));
    }

    @Test
    void findAllByCategoryInOrderByPresenceDescPriceAsc() {
        ClothesService clServMock = new ClothesService();
        ClothesRepository clRepMock = Mockito.mock(ClothesRepository.class);
        clServMock.setClothesRepository(clRepMock);
        Pageable pageable = PageRequest.of(0, 8);
        Category category = new Category();
        category.setId(1);
        List<Category> c = List.of(category);

        Assertions.assertEquals(clServMock.findAllByCategoryInOrderByPresenceDescPriceAsc(c, pageable),
                clRepMock.findAllByCategoryInOrderByPresenceDescPriceAsc(c, pageable));
    }

    @Test
    void findAllByCategoryInOrderByPresenceDescVisitDesc() {
        ClothesService clServMock = new ClothesService();
        ClothesRepository clRepMock = Mockito.mock(ClothesRepository.class);
        clServMock.setClothesRepository(clRepMock);
        Pageable pageable = PageRequest.of(0, 8);
        Category category = new Category();
        category.setId(1);
        List<Category> c = List.of(category);

        Assertions.assertEquals(clServMock.findAllByCategoryInOrderByPresenceDescVisitDesc(c, pageable),
                clRepMock.findAllByCategoryInOrderByPresenceDescVisitDesc(c, pageable));
    }

    @Test
    void findAllByCategoryInOrderByPresenceDescPriceDesc() {
        ClothesService clServMock = new ClothesService();
        ClothesRepository clRepMock = Mockito.mock(ClothesRepository.class);
        clServMock.setClothesRepository(clRepMock);
        Pageable pageable = PageRequest.of(0, 8);
        Category category = new Category();
        category.setId(1);
        List<Category> c = List.of(category);

        Assertions.assertEquals(clServMock.findAllByCategoryInOrderByPresenceDescPriceDesc(c, pageable),
                clRepMock.findAllByCategoryInOrderByPresenceDescPriceDesc(c, pageable));
    }
}