package com.jamoda.controller.admin;

import com.jamoda.model.Category;
import com.jamoda.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CategoryAdminController {

    private CategoryService categoryService;

    @GetMapping("/admin/add_category")
    public String pageAddCategory(Model model) {
        model.addAttribute("categories", categoryService.findAll());
        return "admin/addCategory";
    }

    @PostMapping("/admin/add_category")
    public String addCategory(Category category,
                              @RequestParam(name="parentId") long parentId,
                              Model model) {
        model.addAttribute("categories", categoryService.findAll());

        Category categoryFromDb = categoryService.findByNameEnOrNameRusEquals(category.getNameEn(), category.getNameRus());
        if(categoryFromDb != null) {
            model.addAttribute("error", "Такая категория уже существует!");
            return "admin/addCategory";
        }
        if(parentId != -1) {
            Category parent = categoryService.findById(parentId);
            if(parent != null) {
                category.setParent(parent);
            }
        }
        categoryService.saveCategory(category);
        model.addAttribute("message", "success");
        return "admin/addCategory";
    }

    @Autowired
    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
}
