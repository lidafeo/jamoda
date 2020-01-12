package com.jamoda.controller;

import com.jamoda.model.Clothes;
import com.jamoda.model.User;
import com.jamoda.service.CategoryService;
import com.jamoda.service.ClothesService;
import com.jamoda.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class SearchController {
    private CategoryService categoryService;
    private ClothesService clothesService;
    private SearchService searchService;

    @GetMapping("/search")
    public String order(@RequestParam("q") String q,
                        Model model,
                        @PageableDefault(size = 12) Pageable pageable,
                        @AuthenticationPrincipal User user) {
        model.addAttribute("categories", categoryService.findMainCategory());
        model.addAttribute("customer", user);
        model.addAttribute("q", q.trim());
        if(q.trim() == null || q.trim().equals("")) {
            model.addAttribute("error", "Задан пустой поисковый запрос");
            return "search";
        }
        Clothes clothes = clothesService.findByArticle(q.trim());
        if(clothes != null) {
            return ("redirect:/clothes?article=" + clothes.getArticle());
        }
        Page<Clothes> clothesList = searchService.findClothesByQ(q, pageable);
        model.addAttribute("clothes", clothesList);
        return "search";
    }

    @Autowired
    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
    @Autowired
    public void setClothesService(ClothesService clothesService) {
        this.clothesService = clothesService;
    }
    @Autowired
    public void setSearchService(SearchService searchService) {
        this.searchService = searchService;
    }
}
