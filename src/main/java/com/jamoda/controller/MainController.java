package com.jamoda.controller;

import com.jamoda.model.User;
import com.jamoda.service.CategoryService;
import com.jamoda.service.ClothesService;
import com.jamoda.service.FilterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    private ClothesService clothesService;
    private CategoryService categoryService;
    private FilterService filterService;
    private Model model;
    private Pageable pageable;
    private User user;

    @GetMapping("/")
    public String main(Model model,
                       @PageableDefault(size = 12) Pageable pageable,
                       @AuthenticationPrincipal User user) {
        model.addAttribute("page", clothesService.getClothesPopular(pageable));
        model.addAttribute("customer", user);
        model.addAttribute("max_price", clothesService.getMaxPriceAllClothes() + "");
        model.addAttribute("url", "/?");
        model.addAttribute("categories", categoryService.findMainCategory());
        model.addAttribute("filters", filterService.getActiveFilter());
        return "main";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("categories", categoryService.findMainCategory());
        return "registration";}

    @GetMapping("/about")
    public String about(Model model,
                        @AuthenticationPrincipal User user) {
        model.addAttribute("categories", categoryService.findMainCategory());
        model.addAttribute("customer", user);
        return "about";
    }

   @Autowired
    public void setClothesService(ClothesService clothesService) {
        this.clothesService = clothesService;
    }
    @Autowired
    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
    @Autowired
    public void setFilterService(FilterService filterService) {
        this.filterService = filterService;
    }
}
