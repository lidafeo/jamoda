package com.jamoda.controller;

import com.jamoda.model.*;
import com.jamoda.repository.*;
import com.jamoda.service.CategoryService;
import com.jamoda.service.ClothesService;
import com.jamoda.service.FilterService;
import com.jamoda.service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

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
                             HttpSession session) {
        mainService.getSessionModel(model, session);

        Category category = null;
        if(params.get("category") != null) {
            category = categoryService.findByNameEn(params.get("category"));
        }
        List<Clothes> clothes;
        if(category == null) {
            clothes = clothesService.findAll();
        }
        else {
            model.addAttribute("choosedCategory", category);
            clothes = getClothesWithoutFilters(1, category);
        }
        model.addAttribute("clothes", clothes);
        return "main";
    }

    @PostMapping
    public String applyFilter(@RequestParam Map<String, String> params,
                                   Model model) {
        Map<Attribute, List<String>> filters = filterService.getFilters(params);
        int sort = 1;
        if(params.get("sort") != null && params.get("sort").trim() != "")
            sort = Integer.parseInt(params.get("sort"));

        //определяем категорию
        Category category = null;
        if(params.get("category") != null && !params.get("category").equals("")) {
            category = categoryService.findByNameEn(params.get("category"));
        }

        if(filters.size() == 0) {
            model.addAttribute("clothes", getClothesWithoutFilters(sort, category));
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
        List<Clothes> clothes = new ArrayList<>();
        for (Clothes clo : map.keySet()) {
            if(map.get(clo) == filters.size()) {
                clothes.add(clo);
            }
        }
        model.addAttribute("clothes", sortClothes(clothes, sort));
        return "filterClothes";
    }

    public List<Clothes> sortClothes(List<Clothes> clothes, int sort) {
        Collections.sort(clothes, new Comparator<Clothes>() {
            @Override
            public int compare(Clothes c1, Clothes c2) {
                switch (sort) {
                    case 1:
                        return c2.getVisit() - c1.getVisit();
                    case 2:
                        return c1.getPrice() - c2.getPrice();
                    case 3:
                        return c2.getPrice() - c1.getPrice();
                }
                return c1.getName().compareTo(c2.getName());
            }
        });
        return clothes;
    }


    public List<Clothes> getClothesWithoutFilters(int sort, Category category) {
        if(sort == 0) {
            sort = 1;
        }
        if(category == null) {
           return sortClothes(clothesService.findAll(), sort);
        }
        return sortClothes(clothesService.findAllByCategoryIn(categoryService.getChildrenCategory(category)), sort);
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
