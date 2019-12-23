package com.jamoda.controller;

import com.jamoda.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class CartController {

    private CategoryService categoryService;

    @GetMapping("/cart")
    public String cart(Model model) {
        model.addAttribute("categories", categoryService.findMainCategory());
        return "cart";
    }

    @Autowired
    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
}
