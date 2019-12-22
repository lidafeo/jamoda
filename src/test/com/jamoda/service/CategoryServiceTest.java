package com.jamoda.service;

import com.jamoda.model.Attribute;
import com.jamoda.model.Category;
import com.jamoda.model.Clothes;
import com.jamoda.model.Filter;
import com.jamoda.repository.CategoryRepository;
import com.jamoda.repository.ClothesRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;

class CategoryServiceTest {

    @Test
    void saveCategoryTest() {
        CategoryService catServMock = new CategoryService();
        CategoryRepository catRepMock = Mockito.mock(CategoryRepository.class);
        catServMock.setCategoryRepository(catRepMock);

        Category category = new Category();
//        when(clRepMock.saveAndFlush(clothes))
//                .thenAnswer(i -> i.getArguments()[0]);
        Assertions.assertEquals(catServMock.saveCategory(category),
                catRepMock.saveAndFlush(category));
    }

    @Test
    void findAll() {
        CategoryService catServMock = new CategoryService();
        CategoryRepository catRepMock = Mockito.mock(CategoryRepository.class);
        catServMock.setCategoryRepository(catRepMock);

        Assertions.assertEquals(catServMock.findAll(), catRepMock.findAll());
    }

    @Test
    void findMainCategory() {
        CategoryService catServMock = new CategoryService();
        CategoryRepository catRepMock = Mockito.mock(CategoryRepository.class);
        catServMock.setCategoryRepository(catRepMock);
        Category category = new Category();
        category.setType("main");
        List<Category> categories = List.of(category);
        Mockito.when(catRepMock.findAllByType("main")).thenReturn(categories);

        Assertions.assertEquals(catServMock.findMainCategory(),
                catRepMock.findAllByType("main"));
        Assertions.assertNotNull( catRepMock.findAllByType("main"));
    }

    @Test
    void findByNameEn() {
        CategoryService catServMock = new CategoryService();
        CategoryRepository catRepMock = Mockito.mock(CategoryRepository.class);
        catServMock.setCategoryRepository(catRepMock);

        Assertions.assertEquals(catServMock.findByNameEn("example"),
                catRepMock.findByNameEn("example"));
        Assertions.assertNotNull( catRepMock.findAllByType("example"));
    }

    @Test
    void findById() {
        CategoryService catServMock = new CategoryService();
        CategoryRepository catRepMock = Mockito.mock(CategoryRepository.class);
        catServMock.setCategoryRepository(catRepMock);
        Category category = new Category();
        category.setId(77);
        Mockito.when(catServMock.findById(77)).thenReturn(category);
        Mockito.when(catRepMock.findById(77)).thenReturn(Optional.of(category));

        Assertions.assertEquals(Optional.of(catServMock.findById(77)),
                catRepMock.findById(77));
        Assertions.assertNotNull( catRepMock.findById(77));
    }

    @Test
    void findByNameEnOrNameRusEquals() {
        CategoryService catServMock = new CategoryService();
        CategoryRepository catRepMock = Mockito.mock(CategoryRepository.class);
        catServMock.setCategoryRepository(catRepMock);

        Assertions.assertEquals(
                catServMock.findByNameEnOrNameRusEquals("En",
                        "Ru"),
                catRepMock.findByNameEnOrNameRusEquals("En",
                        "Ru"));
    }

    @Test
    void getChildrenCategory() {
        CategoryService catServMock = new CategoryService();
        CategoryRepository catRepMock = Mockito.mock(CategoryRepository.class);
        catServMock.setCategoryRepository(catRepMock);

        Category category = new Category();
        category.setId(1L);
        List<Category> categories = List.of(category);
        Mockito.when(catRepMock.findAllByParent(category)).thenReturn(categories);
        List<Category> categories1 = List.of(category, category);
        Assertions.assertEquals(
                catServMock.getChildrenCategory(category), categories1);

        Category category1 = new Category();
        category1 = null;
        Assertions.assertNull(catServMock.getChildrenCategory(category1));
    }
}