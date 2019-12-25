package com.jamoda.controller.admin;

import com.jamoda.model.Category;
import com.jamoda.service.AttributeGroupService;
import com.jamoda.service.CategoryService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.ui.Model;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class CategoryAdminControllerTest {

    @Test
    void pageAddCategory() {
        CategoryAdminController categoryAdminController = new CategoryAdminController();
        CategoryService categoryService = Mockito.mock(CategoryService.class);
        categoryAdminController.setCategoryService(categoryService);
        Model model = mock(Model.class);

        Assertions.assertNotNull(categoryAdminController.pageAddCategory(model));
        Assertions.assertEquals( "admin/addCategory",
                categoryAdminController.pageAddCategory(model));
    }

    @Test
    void addCategory() {
        CategoryAdminController categoryAdminController = new CategoryAdminController();
        CategoryService categoryService = Mockito.mock(CategoryService.class);
        categoryAdminController.setCategoryService(categoryService);
        Model model = mock(Model.class);

        long parentId = 1;
        Category category = new Category();

        Mockito.when(categoryService.findByNameEnOrNameRusEquals(category.getNameEn(), category.getNameRus())).
                thenReturn(category);

        Assertions.assertEquals( "admin/addCategory",
                categoryAdminController.addCategory(category, parentId, model));
    }

//    @Test
//    void addCategoryNotNull() {
////        CategoryAdminController categoryAdminController = new CategoryAdminController();
////        CategoryService categoryService = Mockito.mock(CategoryService.class);
////        categoryAdminController.setCategoryService(categoryService);
////        Model model = mock(Model.class);
////        long parentId = 1;
////        Category category = new Category();
////        Category category2 = new Category();
////        category2.setId(1);
////        category.setParent(category2);
////
////        Mockito.when(categoryService.findByNameEnOrNameRusEquals(category.getNameEn(), category.getNameRus())).
////                thenReturn(null);
////        Mockito.when(categoryService.findById(parentId)).
////                thenReturn(category);
////
////
////        Assertions.assertEquals( "admin/addCategory",
////                categoryAdminController.addCategory(category, parentId, model));
//    }
}