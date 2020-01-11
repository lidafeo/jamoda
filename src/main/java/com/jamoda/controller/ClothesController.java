package com.jamoda.controller;

import com.jamoda.model.Category;
import com.jamoda.model.Clothes;
import com.jamoda.model.User;
import com.jamoda.service.CategoryService;
import com.jamoda.service.ClothesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;

@Controller
public class ClothesController {

    private ClothesService clothesService;
    private CategoryService categoryService;

    @GetMapping("/clothes")
    public String getClothes(@RequestParam String article, Model model, @AuthenticationPrincipal User user) {
        model.addAttribute("customer", user);
        model.addAttribute("categories", categoryService.findMainCategory());

        Clothes clothes = clothesService.findByArticle(article);
        if(clothes == null) {
            model.addAttribute("error", "Товар не найден");
            return "clothes";
        }
        model.addAttribute("clothes", clothes);
        model.addAttribute("sizes", clothesService.getSizes(clothes.getWarehouses()));
        model.addAttribute("commonCount", clothesService.getCountProductInWarehouse(clothes.getWarehouses()));
        List<Category> categories = new LinkedList<>();
        Category category = clothes.getCategory();
        while (category != null) {
            categories.add(0, category);
            category = category.getParent();
        }
        model.addAttribute("list_categories", categories);
        clothes.addVisit();
        clothesService.saveClothes(clothes);
        return "clothes";
    }

   @Autowired
    public void setClothesService(ClothesService clothesService) {
        this.clothesService = clothesService;
    }
    @Autowired
    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
}
