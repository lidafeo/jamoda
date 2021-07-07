package com.jamoda.controller;

import com.jamoda.model.*;
import com.jamoda.service.CategoryService;
import com.jamoda.service.ClothesService;
import com.jamoda.service.FilterService;
import com.jamoda.service.SortService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequestMapping("/filter")
public class FilterController {

    private FilterService filterService;
    private CategoryService categoryService;
    private ClothesService clothesService;
    private SortService sortService;

    @GetMapping
    public String mainFilter(@RequestParam Map<String, String> params,
                             Model model,
                             @PageableDefault(size = 12) Pageable pageable,
                             @AuthenticationPrincipal User user) {
        model.addAttribute("filters", filterService.getActiveFilter());
        model.addAttribute("categories", categoryService.findMainCategory());
        model.addAttribute("customer", user);
        Category category = null;
        if(params.get("category") != null) {
            category = categoryService.findByNameEn(params.get("category"));
            model.addAttribute("url", "/filter?category=" + params.get("category") + "&"); }
        else {
            model.addAttribute("url", "/?");
        }
        Page<Clothes> clothes;
        if(category == null) {
            clothes = clothesService.getClothesPopular(pageable);
        }
        else {
            model.addAttribute("choosedCategory", category);
            clothes = sortService.getClothesWithoutFilters(1, category, pageable, -1, -1); }
        model.addAttribute("page", clothes);
        model.addAttribute("max_price", clothesService.getMaxPriceByClothesIn(categoryService.getChildrenCategory(category)) + "");
        return "main";
    }

    @PostMapping
    public String applyFilter(@RequestParam Map<String, String> params,
                              Model model,
                              @PageableDefault Pageable pageable,
                              @AuthenticationPrincipal User user) {
        model.addAttribute("customer", user);
        Map<Attribute, List<String>> filters = filterService.getFilters(params);
        //определяем типо сортировки
        int sort = 1;
        if(params.get("sorting") != null && params.get("sorting").trim() != "")
            sort = Integer.parseInt(params.get("sorting"));
        //определяем категорию
        Category category = null;
        if(params.get("category") != null && !params.get("category").equals("")) {
            category = categoryService.findByNameEn(params.get("category"));
            model.addAttribute("url", "/filter?category=" + params.get("category") + "&");
        }
        else {
            model.addAttribute("url", "/?");
        }
        //определяем диапазон цены
        int price_min = -1;
        int price_max = -1;
        if(params.get("price_min") != null && params.get("price_max") != null) {
            try {
                price_min = Integer.parseInt(params.get("price_min").trim());
                price_max = Integer.parseInt(params.get("price_max").trim());
            }
            catch (NumberFormatException e) {
                System.out.println(e);
            }
        }
        //определяем размеры одежды
        //фильтры по атрибутам
        if(filters.size() == 0 && params.get("size_clothes") == null) {
            model.addAttribute("page", sortService.getClothesWithoutFilters(sort, category, pageable, price_min, price_max));
            return "filterClothes";
        }
        List<String> clothes;
        if(filters.size() == 0) {
            clothes = filterService.filteredSize(params.get("size_clothes"), categoryService.getChildrenCategory(category));
        }
        else {
            List<AttributeValue> attributeValue = filterService.findArticleClothesWithFilter(filters, categoryService.getChildrenCategory(category));
            clothes = filterService.getFilteredClothes(attributeValue, filters, params.get("size_clothes"));
        }
        model.addAttribute("page", sortService.sortClothes(clothes, sort, pageable, price_min, price_max));
        return "filterClothes";
    }

    @Autowired
    public void setFilterService(FilterService filterService) {
        this.filterService = filterService;
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
    public void setSortService(SortService sortService) {
        this.sortService = sortService;
    }
}
