package com.jamoda.service;

import com.jamoda.model.*;
import com.jamoda.repository.*;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class ClothesServiceTest {

    ClothesRepository clRepMock = Mockito.mock(ClothesRepository.class);

    @Test
    void saveClothesTest() {
        ClothesService clServMock = new ClothesService();
        clServMock.setClothesRepository(clRepMock);

        Clothes clothes = new Clothes();
        when(clRepMock.saveAndFlush(clothes))
                .thenAnswer(i -> i.getArguments()[0]);
        Assertions.assertEquals(clServMock.saveClothes(clothes),
                clRepMock.saveAndFlush(clothes));
    }


    @Test
    void getMaxPriceAllClothes() {
        ClothesService clServMock = new ClothesService();
        clServMock.setClothesRepository(clRepMock);

        Mockito.when(clRepMock.findMaxPrice()).thenReturn(1);

        Assert.assertEquals(java.util.Optional.ofNullable(clServMock.getMaxPriceAllClothes()),
                java.util.Optional.ofNullable(1));
    }

    @Test
    void getMaxPriceByClothesIn() {
        ClothesService clServMock = new ClothesService();
        clServMock.setClothesRepository(clRepMock);

        Category category = new Category();
        category.setId(1L);
        List<Category> categories = List.of(category);

        Mockito.when(clRepMock.findMaxPriceByCategoryIn(categories)).thenReturn(1);

        Assert.assertEquals(java.util.Optional.ofNullable(clServMock.getMaxPriceByClothesIn(categories)),
                java.util.Optional.ofNullable(1));
    }

    @Test
    void getClothesPopularTest() {
        ClothesService clServMock = new ClothesService();
        clServMock.setClothesRepository(clRepMock);
        Pageable pageable = PageRequest.of(0, 8);

        Assertions.assertEquals(
                clServMock.getClothesPopular(pageable),
                clRepMock.findAllByOrderByPresenceDescVisitDesc(pageable));
    }

    @Test
    void findByArticleTest() {
        ClothesService clServMock = new ClothesService();
        clServMock.setClothesRepository(clRepMock);

        Clothes clothes = new Clothes();
        Mockito.when(clRepMock.findByArticle("1")).thenReturn(clothes);

        Assertions.assertEquals(
                clServMock.findByArticle("1"),
                clothes);
    }

    @Test
    void findAllTest() {
        ClothesService clServMock = new ClothesService();
        clServMock.setClothesRepository(clRepMock);

        Assertions.assertEquals(clServMock.findAll(), clRepMock.findAll());
    }

    @Test
    void getClothesPopular() {
        ClothesService clServMock = new ClothesService();
        clServMock.setClothesRepository(clRepMock);
        Pageable pageable = PageRequest.of(0, 8);

        Assertions.assertEquals(clServMock.getClothesPopular(pageable),
                clRepMock.findAllByOrderByPresenceDescVisitDesc(pageable));
    }


    @Test
    void getClothesPopular2right() {
        ClothesService clServMock = new ClothesService();
        clServMock.setClothesRepository(clRepMock);

        Pageable pageable = PageRequest.of(0, 8);

        Assertions.assertEquals(
                clServMock.getClothesPopular(pageable, 0, 1),
                clRepMock.findAllByPriceBetweenOrderByPresenceDescVisitDesc(0, 1, pageable));
    }

    @Test
    void getClothesPopular2notright() {
        ClothesService clServMock = new ClothesService();
        clServMock.setClothesRepository(clRepMock);

        Pageable pageable = PageRequest.of(0, 8);

        Assertions.assertEquals(
                clServMock.getClothesPopular(pageable, -1, 1),
                clServMock.getClothesPopular(pageable));
        Assertions.assertEquals(
                clServMock.getClothesPopular(pageable, 1, -1),
                clServMock.getClothesPopular(pageable));
    }


    @Test
    void findAllByCategoryInTest() {
        ClothesService clServMock = new ClothesService();
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
        clServMock.setClothesRepository(clRepMock);

        List<String> articles = List.of("qwerty");
        Pageable pageable = PageRequest.of(0, 8);

        Assertions.assertEquals(
                clServMock.findAllByArticleInOrderByPresenceDescVisitDesc(articles, pageable),
                clRepMock.findAllByArticleInOrderByPresenceDescVisitDesc(articles, pageable));
    }

    @Test
    void findAllByArticleInOrderByPresenceDescVisitDesc2notright() {
        ClothesService clServMock = new ClothesService();
        clServMock.setClothesRepository(clRepMock);

        Pageable pageable = PageRequest.of(0, 8);
        List<String> articles = List.of("qwerty");

        Assertions.assertEquals(
                clServMock.findAllByArticleInOrderByPresenceDescVisitDesc(
                        articles, pageable, -1, 1),
                clServMock.findAllByArticleInOrderByPresenceDescVisitDesc(
                        articles, pageable));
        Assertions.assertEquals(
                clServMock.findAllByArticleInOrderByPresenceDescVisitDesc(
                        articles, pageable, 1, -1),
                clServMock.findAllByArticleInOrderByPresenceDescVisitDesc(
                        articles, pageable));
    }

    @Test
    void findAllByArticleInOrderByPresenceDescVisitDesc2right() {
        ClothesService clServMock = new ClothesService();
        clServMock.setClothesRepository(clRepMock);

        Pageable pageable = PageRequest.of(0, 8);
        List<String> articles = List.of("qwerty");

        Assertions.assertEquals(
                clServMock.findAllByArticleInOrderByPresenceDescVisitDesc(
                        articles, pageable, 0, 1),
                clRepMock.findAllByArticleInAndPriceBetweenOrderByPresenceDescVisitDesc(
                        articles, 0, 1, pageable));
    }

    @Test
    void findAllByArticleInOrderByPresenceDescPriceDesc() {
        ClothesService clServMock = new ClothesService();
        clServMock.setClothesRepository(clRepMock);

        List<String> articles = List.of("qwerty");
        Pageable pageable = PageRequest.of(0, 8);

        Assertions.assertEquals(
                clServMock.findAllByArticleInOrderByPresenceDescPriceDesc(articles, pageable),
                clRepMock.findAllByArticleInOrderByPresenceDescPriceDesc(articles, pageable));
    }

    @Test
    void findAllByArticleInOrderByPresenceDescPriceDesc2notright() {
        ClothesService clServMock = new ClothesService();
        clServMock.setClothesRepository(clRepMock);

        Pageable pageable = PageRequest.of(0, 8);
        List<String> articles = List.of("qwerty");

        Assertions.assertEquals(
                clServMock.findAllByArticleInOrderByPresenceDescPriceDesc(
                        articles, pageable, -1, 1),
                clServMock.findAllByArticleInOrderByPresenceDescPriceDesc(
                        articles, pageable));
        Assertions.assertEquals(
                clServMock.findAllByArticleInOrderByPresenceDescPriceDesc(
                        articles, pageable, 1, -1),
                clServMock.findAllByArticleInOrderByPresenceDescPriceDesc(
                        articles, pageable));
    }

    @Test
    void findAllByArticleInOrderByPresenceDescPriceDesc2right() {
        ClothesService clServMock = new ClothesService();
        clServMock.setClothesRepository(clRepMock);

        Pageable pageable = PageRequest.of(0, 8);
        List<String> articles = List.of("qwerty");

        Assertions.assertEquals(
                clServMock.findAllByArticleInOrderByPresenceDescPriceDesc(
                        articles, pageable, 0, 1),
                clRepMock.findAllByArticleInAndPriceBetweenOrderByPresenceDescPriceDesc(
                        articles, 0, 1, pageable));
    }

    @Test
    void findAllByArticleInOrderByPresenceDescPriceAsc() {
        ClothesService clServMock = new ClothesService();
        clServMock.setClothesRepository(clRepMock);

        List<String> articles = List.of("qwerty");
        Pageable pageable = PageRequest.of(0, 8);

        Assertions.assertEquals(
                clServMock.findAllByArticleInOrderByPresenceDescPriceAsc(articles, pageable),
                clRepMock.findAllByArticleInOrderByPresenceDescPriceAsc(articles, pageable));
    }

    @Test
    void findAllByArticleInOrderByPresenceDescPriceAsc2notright() {
        ClothesService clServMock = new ClothesService();
        clServMock.setClothesRepository(clRepMock);

        Pageable pageable = PageRequest.of(0, 8);
        List<String> articles = List.of("qwerty");

        Assertions.assertEquals(
                clServMock.findAllByArticleInOrderByPresenceDescPriceAsc(
                        articles, pageable, -1, 1),
                clServMock.findAllByArticleInOrderByPresenceDescPriceAsc(
                        articles, pageable));
        Assertions.assertEquals(
                clServMock.findAllByArticleInOrderByPresenceDescPriceAsc(
                        articles, pageable, 1, -1),
                clServMock.findAllByArticleInOrderByPresenceDescPriceAsc(
                        articles, pageable));
    }

    @Test
    void findAllByArticleInOrderByPresenceDescPriceAsc2right() {
        ClothesService clServMock = new ClothesService();
        clServMock.setClothesRepository(clRepMock);

        Pageable pageable = PageRequest.of(0, 8);
        List<String> articles = List.of("qwerty");

        Assertions.assertEquals(
                clServMock.findAllByArticleInOrderByPresenceDescPriceAsc(
                        articles, pageable, 0, 1),
                clRepMock.findAllByArticleInAndPriceBetweenOrderByPresenceDescPriceAsc(
                        articles, 0, 1, pageable));
    }

    @Test
    void findByAttributeGroupsContainsAndArticleTest() {
        ClothesService clServMock = new ClothesService();
        clServMock.setClothesRepository(clRepMock);

        List<AttributeGroup> atglist = new ArrayList<>();
        AttributeGroup atg = new AttributeGroup();
        atg.setName("1");
        atglist.add(atg);
        Clothes clothes = new Clothes();
        clothes.setArticle("qwerty");
        clothes.setAttributeGroups(atglist);
        Mockito.when(clRepMock.findByAttributeGroupsContainsAndArticle(atg, "1")).
                thenReturn(clothes);

        Assertions.assertEquals(
                clServMock.findByAttributeGroupsContainsAndArticle(atg, "1"),
                clothes);
    }
    @Test
    void getCountProductInWarehouse() {
        ClothesService clServMock = new ClothesService();
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
        clServMock.setClothesRepository(clRepMock);
        Pageable pageable = PageRequest.of(0, 8);


        Assertions.assertEquals(clServMock.findAllByOrderByPresenceDescPriceDesc(pageable),
                clRepMock.findAllByOrderByPresenceDescPriceDesc(pageable));
    }

    @Test
    void findAllByOrderByPresenceDescPriceAsc() {
        ClothesService clServMock = new ClothesService();
        clServMock.setClothesRepository(clRepMock);
        Pageable pageable = PageRequest.of(0, 8);
        Clothes clothes = new Clothes();
        clothes.setArticle("article");
        List<Clothes> list = new ArrayList<>();
        list.add(clothes);
        Page<Clothes> page = new PageImpl<>(list, pageable, 1l);
        Mockito.when( clRepMock.findAllByOrderByPresenceDescPriceAsc(pageable)).
                thenReturn(page);

        Assertions.assertEquals(clServMock.findAllByOrderByPresenceDescPriceAsc(pageable),
                page);
    }

    @Test
    void findClothesPresence() {
        ClothesService clServMock = new ClothesService();
        clServMock.setClothesRepository(clRepMock);

        Pageable pageable = PageRequest.of(0, 8);
        Clothes clothes = new Clothes();
        clothes.setArticle("article");
        List<Clothes> list = new ArrayList<>();
        list.add(clothes);

        Mockito.when(clRepMock.findAllByPresence(true)).
                thenReturn(list);

        Assertions.assertEquals(clServMock.findClothesPresence(),
                list);
    }

    @Test
    void findAllByCategoryInOrderByPresenceDescPriceAsc() {
        ClothesService clServMock = new ClothesService();
        clServMock.setClothesRepository(clRepMock);
        Pageable pageable = PageRequest.of(0, 8);
        Category category = new Category();
        category.setId(1);
        List<Category> c = List.of(category);

        Clothes clothes = new Clothes();
        clothes.setArticle("article");
        List<Clothes> list = new ArrayList<>();
        list.add(clothes);
        Page<Clothes> page = new PageImpl<>(list, pageable, 1l);

        Mockito.when(clRepMock.findAllByCategoryInOrderByPresenceDescPriceAsc(c, pageable)).
                thenReturn(page);

        Assertions.assertEquals(clServMock.findAllByCategoryInOrderByPresenceDescPriceAsc(c, pageable),
                page);
    }

    @Test
    void findAllByCategoryInOrderByPresenceDescVisitDesc() {
        ClothesService clServMock = new ClothesService();
        clServMock.setClothesRepository(clRepMock);
        Pageable pageable = PageRequest.of(0, 8);
        Category category = new Category();
        category.setId(1);
        List<Category> c = List.of(category);
        Clothes clothes = new Clothes();
        clothes.setArticle("article");
        List<Clothes> list = new ArrayList<>();
        list.add(clothes);
        Page<Clothes> page = new PageImpl<>(list, pageable, 1l);

        Mockito.when(clRepMock.findAllByCategoryInOrderByPresenceDescVisitDesc(c, pageable)).
                thenReturn(page);

        Assertions.assertEquals(clServMock.findAllByCategoryInOrderByPresenceDescVisitDesc(c, pageable),
               page);
    }

    @Test
    void findAllByCategoryInOrderByPresenceDescPriceDesc() {
        ClothesService clServMock = new ClothesService();
        clServMock.setClothesRepository(clRepMock);
        Pageable pageable = PageRequest.of(0, 8);
        Category category = new Category();
        category.setId(1);
        List<Category> c = List.of(category);
        Clothes clothes = new Clothes();
        clothes.setArticle("article");
        List<Clothes> list = new ArrayList<>();
        list.add(clothes);
        Page<Clothes> page = new PageImpl<>(list, pageable, 1l);

        Mockito.when(clRepMock.findAllByCategoryInOrderByPresenceDescPriceDesc(c, pageable)).
                thenReturn(page);

        Assertions.assertEquals(clServMock.findAllByCategoryInOrderByPresenceDescPriceDesc(c, pageable),
                page);
    }


    @Test
    void findAllByOrderByPresenceDescPriceDesc2notright() {
        ClothesService clServMock = new ClothesService();
        clServMock.setClothesRepository(clRepMock);

        Pageable pageable = PageRequest.of(0, 8);
        Clothes clothes = new Clothes();
        clothes.setArticle("article");
        List<Clothes> list = new ArrayList<>();
        list.add(clothes);
        Page<Clothes> page = new PageImpl<>(list, pageable, 1l);

        Mockito.when(clRepMock.findAllByOrderByPresenceDescPriceDesc(pageable)).
                thenReturn(page);

        Assertions.assertEquals(
                clServMock.findAllByOrderByPresenceDescPriceDesc(
                         pageable, -1, 1),
                page);
        Assertions.assertEquals(
                clServMock.findAllByOrderByPresenceDescPriceDesc(
                         pageable, 1, -1),
               page);
    }

    @Test
    void findAllByOrderByPresenceDescPriceDesc2right() {
        ClothesService clServMock = new ClothesService();
        clServMock.setClothesRepository(clRepMock);

        Pageable pageable = PageRequest.of(0, 8);
        Clothes clothes = new Clothes();
        clothes.setArticle("article");
        List<Clothes> list = new ArrayList<>();
        list.add(clothes);
        Page<Clothes> page = new PageImpl<>(list, pageable, 1l);

        Mockito.when(clRepMock.findAllByPriceBetweenOrderByPresenceDescPriceDesc(
                0, 1, pageable)).
                thenReturn(page);

        Assertions.assertEquals(
                clServMock.findAllByOrderByPresenceDescPriceDesc(
                         pageable, 0, 1),
                page);
    }

    @Test
    void findAllByOrderByPresenceDescPriceAsc2notright() {
        ClothesService clServMock = new ClothesService();
        clServMock.setClothesRepository(clRepMock);

        Pageable pageable = PageRequest.of(0, 8);
        Clothes clothes = new Clothes();
        clothes.setArticle("article");
        List<Clothes> list = new ArrayList<>();
        list.add(clothes);
        Page<Clothes> page = new PageImpl<>(list, pageable, 1l);

        Mockito.when(clRepMock.findAllByOrderByPresenceDescPriceAsc(pageable)).
                thenReturn(page);

        Assertions.assertEquals(
                clServMock.findAllByOrderByPresenceDescPriceAsc(
                        pageable, -1, 1),
               page);
        Assertions.assertEquals(
                clServMock.findAllByOrderByPresenceDescPriceAsc(
                        pageable, 1, -1),
                page);
    }

    @Test
    void findAllByOrderByPresenceDescPriceAsc2right() {
        ClothesService clServMock = new ClothesService();
        clServMock.setClothesRepository(clRepMock);

        Pageable pageable = PageRequest.of(0, 8);
        Clothes clothes = new Clothes();
        clothes.setArticle("article");
        List<Clothes> list = new ArrayList<>();
        list.add(clothes);
        Page<Clothes> page = new PageImpl<>(list, pageable, 1l);

        Mockito.when(clRepMock.findAllByPriceBetweenOrderByPresenceDescPriceAsc(
                0, 1, pageable)).thenReturn(page);

        Assertions.assertEquals(
                clServMock.findAllByOrderByPresenceDescPriceAsc(
                        pageable, 0, 1),
               page);
    }

    @Test
    void findAllByCategoryInOrderByPresenceDescVisitDesc2notright() {
        ClothesService clServMock = new ClothesService();
        clServMock.setClothesRepository(clRepMock);

        Pageable pageable = PageRequest.of(0, 8);
        Category category = new Category();
        category.setId(1L);
        List<Category> categories = List.of(category);
        Clothes clothes = new Clothes();
        clothes.setArticle("article");
        List<Clothes> list = new ArrayList<>();
        list.add(clothes);
        Page<Clothes> page = new PageImpl<>(list, pageable, 1l);

        Mockito.when(clRepMock.findAllByCategoryInOrderByPresenceDescVisitDesc(
                categories, pageable)).thenReturn(page);

        Assertions.assertEquals(
                clServMock.findAllByCategoryInOrderByPresenceDescVisitDesc(
                        categories, pageable, -1, 1),
              page);
        Assertions.assertEquals(
                clServMock.findAllByCategoryInOrderByPresenceDescVisitDesc(
                        categories, pageable, 1, -1),
                page);
    }

    @Test
    void findAllByCategoryInOrderByPresenceDescVisitDesc2right() {
        ClothesService clServMock = new ClothesService();
        clServMock.setClothesRepository(clRepMock);

        Pageable pageable = PageRequest.of(0, 8);
        Category category = new Category();
        category.setId(1L);
        List<Category> categories = List.of(category);
        Clothes clothes = new Clothes();
        clothes.setArticle("article");
        List<Clothes> list = new ArrayList<>();
        list.add(clothes);
        Page<Clothes> page = new PageImpl<>(list, pageable, 1l);

        Mockito.when(clRepMock.findAllByCategoryInAndPriceBetweenOrderByPresenceDescVisitDesc(
                categories, 0, 1, pageable)).thenReturn(page);

        Assertions.assertEquals(
                clServMock.findAllByCategoryInOrderByPresenceDescVisitDesc(
                        categories, pageable, 0, 1),
                page);
    }

    @Test
    void findAllByCategoryInOrderByPresenceDescPriceDesc2notright() {
        ClothesService clServMock = new ClothesService();
        clServMock.setClothesRepository(clRepMock);

        Pageable pageable = PageRequest.of(0, 8);
        Category category = new Category();
        category.setId(1L);
        List<Category> categories = List.of(category);
        Clothes clothes = new Clothes();
        clothes.setArticle("article");
        List<Clothes> list = new ArrayList<>();
        list.add(clothes);
        Page<Clothes> page = new PageImpl<>(list, pageable, 1l);

        Mockito.when(clRepMock.findAllByCategoryInOrderByPresenceDescPriceDesc(
                categories, pageable)).thenReturn(page);

        Assertions.assertEquals(
                clServMock.findAllByCategoryInOrderByPresenceDescPriceDesc(
                        categories, pageable, -1, 1),
              page);
        Assertions.assertEquals(
                clServMock.findAllByCategoryInOrderByPresenceDescPriceDesc(
                        categories, pageable, 1, -1),
               page);
    }

    @Test
    void findAllByCategoryInOrderByPresenceDescPriceDesc2right() {
        ClothesService clServMock = new ClothesService();
        clServMock.setClothesRepository(clRepMock);

        Pageable pageable = PageRequest.of(0, 8);
        Category category = new Category();
        category.setId(1L);
        List<Category> categories = List.of(category);
        Clothes clothes = new Clothes();
        clothes.setArticle("article");
        List<Clothes> list = new ArrayList<>();
        list.add(clothes);
        Page<Clothes> page = new PageImpl<>(list, pageable, 1l);

        Mockito.when(clRepMock.findAllByCategoryInAndPriceBetweenOrderByPresenceDescPriceDesc(
                categories, 0, 1, pageable)).thenReturn(page);

        Assertions.assertEquals(
                clServMock.findAllByCategoryInOrderByPresenceDescPriceDesc(
                        categories, pageable, 0, 1),
                page);
    }

    @Test
    void findAllByCategoryInOrderByPresenceDescPriceAsc2notright() {
        ClothesService clServMock = new ClothesService();
        clServMock.setClothesRepository(clRepMock);

        Pageable pageable = PageRequest.of(0, 8);
        Category category = new Category();
        category.setId(1L);
        List<Category> categories = List.of(category);
        Clothes clothes = new Clothes();
        clothes.setArticle("article");
        List<Clothes> list = new ArrayList<>();
        list.add(clothes);
        Page<Clothes> page = new PageImpl<>(list, pageable, 1l);

        Mockito.when(clRepMock.findAllByCategoryInOrderByPresenceDescPriceAsc(
                categories,  pageable)).thenReturn(page);
        Assertions.assertEquals(
                clServMock.findAllByCategoryInOrderByPresenceDescPriceAsc(
                        categories, pageable, -1, 1),
               page);
        Assertions.assertEquals(
                clServMock.findAllByCategoryInOrderByPresenceDescPriceAsc(
                        categories, pageable, 1, -1),
                page);
    }

    @Test
    void findAllByCategoryInOrderByPresenceDescPriceAsc2right() {
        ClothesService clServMock = new ClothesService();
        clServMock.setClothesRepository(clRepMock);

        Pageable pageable = PageRequest.of(0, 8);
        Category category = new Category();
        category.setId(1L);
        List<Category> categories = List.of(category);
        Clothes clothes = new Clothes();
        clothes.setArticle("article");
        List<Clothes> list = new ArrayList<>();
        list.add(clothes);
        Page<Clothes> page = new PageImpl<>(list, pageable, 1l);

        Mockito.when(clRepMock.findAllByCategoryInAndPriceBetweenOrderByPresenceDescPriceAsc(
                categories,0, 1,  pageable)).thenReturn(page);

        Assertions.assertEquals(
                clServMock.findAllByCategoryInOrderByPresenceDescPriceAsc(
                        categories, pageable, 0, 1),
                page);
    }
}