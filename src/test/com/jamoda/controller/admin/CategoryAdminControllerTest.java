package com.jamoda.controller.admin;

import com.jamoda.model.Category;
import com.jamoda.service.CategoryService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.ui.Model;

class CategoryAdminControllerTest {

    CategoryService categoryService = Mockito.mock(CategoryService.class);
    Model model = Mockito.mock(Model.class);

    @Test
    void pageAddCategory() {
        CategoryAdminController categoryAdminController = new CategoryAdminController();
        categoryAdminController.setCategoryService(categoryService);

        Assertions.assertNotNull(categoryAdminController.pageAddCategory(model));
        Assertions.assertEquals( "admin/addCategory",
                categoryAdminController.pageAddCategory(model));
    }

    @Test
    void addCategory() {
        CategoryAdminController categoryAdminController = new CategoryAdminController();
        categoryAdminController.setCategoryService(categoryService);

        long parentId = 1;
        Category category = new Category();

        Mockito.when(categoryService.findByNameEnOrNameRusEquals(category.getNameEn(), category.getNameRus())).
                thenReturn(category);
        Assertions.assertEquals( "admin/addCategory",
                categoryAdminController.addCategory(category, parentId, model));

        Mockito.when(categoryService.findByNameEnOrNameRusEquals(category.getNameEn(), category.getNameRus())).
                thenReturn(null);
        Mockito.when(categoryService.findById(parentId)).
                thenReturn(category);
        Assertions.assertEquals( "admin/addCategory",
                categoryAdminController.addCategory(category, parentId, model));
    }
}