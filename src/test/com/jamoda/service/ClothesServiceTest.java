package com.jamoda.service;

import com.jamoda.model.*;
import com.jamoda.repository.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
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

        Assertions.assertEquals(
                clServMock.getClothesPopular(),
                clRepMock.findAllByOrderByPresenceDescVisitDesc());
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

        Assertions.assertEquals(clServMock.getClothesPopular(),
                clRepMock.findAllByOrderByPresenceDescVisitDesc());
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

//        Assertions.assertEquals(
//                clServMock.findByAttributeGroupsContainsAndArticle(atg, "1"),
//                clRepMock.findByAttributeGroupsContainsAndArticle(atg, "1"));

        Assertions.assertEquals(
                clServMock.findByAttributeGroupsContainsAndArticle(atg, "1"),
                clothes);
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
        Assertions.assertEquals(clServMock.getCountProductInWarehouse(whs),
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
}