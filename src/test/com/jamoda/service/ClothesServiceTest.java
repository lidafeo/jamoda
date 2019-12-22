package com.jamoda.service;

import com.jamoda.model.*;
import com.jamoda.repository.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
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
                clRepMock.findAllByOrderByVisitDesc());
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

        AttributeGroup atg = new AttributeGroup();
        atg.setName("1");

        Assertions.assertEquals(
                clServMock.findByAttributeGroupsContainsAndArticle(atg, "1"),
                clRepMock.findByAttributeGroupsContainsAndArticle(atg, "1"));
    }
}