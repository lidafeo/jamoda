package com.jamoda.controller;

import com.jamoda.model.*;
import com.jamoda.service.CategoryService;
import com.jamoda.service.ClothesService;
import com.jamoda.service.FilterService;
import com.jamoda.service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.*;

@Controller
@RequestMapping("/filter")
public class FilterController {

    private MainService mainService;
    private FilterService filterService;
    private CategoryService categoryService;
    private ClothesService clothesService;

    @GetMapping
    public String mainFilter(@RequestParam Map<String, String> params,
                             Model model,
                             @PageableDefault(size = 12) Pageable pageable,
                             @AuthenticationPrincipal User user) {
        mainService.getSessionModel(model);
        model.addAttribute("customer", user);

        Category category = null;
        if(params.get("category") != null) {
            category = categoryService.findByNameEn(params.get("category"));
            model.addAttribute("url", "/filter?category=" + params.get("category") + "&");
        }
        else {
            model.addAttribute("url", "/?");
        }
        Page<Clothes> clothes;
        if(category == null) {
            clothes = clothesService.getClothesPopular(pageable);
        }
        else {
            model.addAttribute("choosedCategory", category);
            clothes = getClothesWithoutFilters(1, category, pageable, -1, -1);
        }
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

        if(filters.size() == 0) {
            model.addAttribute("page", getClothesWithoutFilters(sort, category, pageable, price_min, price_max));
            return "filterClothes";
        }
        List<AttributeValue> attributeValue = filterService.findArticleClothesWithFilter(filters, categoryService.getChildrenCategory(category));

        Map<Clothes, Integer> map = new HashMap<>();
        //смотрим сколько совпало
        for(AttributeValue val : attributeValue) {
            if(map.get(val.getClothes()) == null) {
                map.put(val.getClothes(), 1);
            }
            else {
                map.put(val.getClothes(), map.get(val.getClothes()) + 1);
            }
        }
        List<String> clothes = new ArrayList<>();
        for (Clothes clo : map.keySet()) {
            if(map.get(clo) == filters.size()) {
                clothes.add(clo.getArticle());
            }
        }
        model.addAttribute("page", sortClothes(clothes, sort, pageable, price_min, price_max));
        return "filterClothes";
    }

    public Page<Clothes> sortClothes(List<String> articles, int sort, Pageable pageable, int priceMin, int priceMax) {
        switch (sort) {
            case 1:
                return clothesService.findAllByArticleInOrderByPresenceDescVisitDesc(articles, pageable, priceMin, priceMax);
            case 2:
                return clothesService.findAllByArticleInOrderByPresenceDescPriceAsc(articles, pageable, priceMin, priceMax);
            case 3:
                return clothesService.findAllByArticleInOrderByPresenceDescPriceDesc(articles, pageable, priceMin, priceMax);
            default:
                return clothesService.findAllByArticleInOrderByPresenceDescVisitDesc(articles, pageable, priceMin, priceMax);
        }
    }

    public Page<Clothes> sortFullClothes(int sort, Pageable pageable, int priceMin, int priceMax) {
        switch (sort) {
            case 1:
                return clothesService.getClothesPopular(pageable, priceMin, priceMax);
            case 2:
                return clothesService.findAllByOrderByPresenceDescPriceAsc(pageable, priceMin, priceMax);
            case 3:
                return clothesService.findAllByOrderByPresenceDescPriceDesc(pageable, priceMin, priceMax);
            default:
                return clothesService.getClothesPopular(pageable, priceMin, priceMax);
        }
    }

    //for sort with category
    public Page<Clothes> sortWithCategory(List<Category> categories, int sort, Pageable pageable, int priceMin, int priceMax) {
        switch (sort) {
            case 1:
                return clothesService.findAllByCategoryInOrderByPresenceDescVisitDesc(categories, pageable, priceMin, priceMax);
            case 2:
                return clothesService.findAllByCategoryInOrderByPresenceDescPriceAsc(categories, pageable, priceMin, priceMax);
            case 3:
                return clothesService.findAllByCategoryInOrderByPresenceDescPriceDesc(categories, pageable, priceMin, priceMax);
            default:
                return clothesService.getClothesPopular(pageable, priceMin, priceMax);
        }
    }


    public Page<Clothes> getClothesWithoutFilters(int sort, Category category, Pageable pageable, int priceMin, int priceMax) {
        if(sort == 0) {
            sort = 1;
        }
        if(category == null) {
           return sortFullClothes(sort, pageable, priceMin, priceMax);
        }
        return sortWithCategory(categoryService.getChildrenCategory(category), sort, pageable, priceMin, priceMax);
    }

    @Autowired
    public void setMainService(MainService mainService) {
        this.mainService = mainService;
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
}
