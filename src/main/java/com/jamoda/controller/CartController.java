package com.jamoda.controller;

import com.jamoda.model.User;
import com.jamoda.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class CartController {

    private CategoryService categoryService;

    @GetMapping("/cart")
    public String cart(Model model, @AuthenticationPrincipal User user) {
        model.addAttribute("categories", categoryService.findMainCategory());
        model.addAttribute("customer", user);
        return "cart";
    }

    @Autowired
    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
}
