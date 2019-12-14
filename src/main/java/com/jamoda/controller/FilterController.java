package com.jamoda.controller;

import com.jamoda.model.*;
import com.jamoda.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

import java.util.*;

import static com.jamoda.controller.MainController.getModel;

@Controller
@RequestMapping("/filter")
public class FilterController {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ClothesRepository clothesRepository;
    @Autowired
    private FilterRepository filterRepository;
    @Autowired
    private AttributeValueRepository attributeValueRepository;

    @GetMapping
    public String mainFilter(@RequestParam Map<String, String> params,
                             Model model,
                             HttpSession session) {
        getCommonInfo(model, session);
        Category category = null;
        if(params.get("category") != null) {
            category = categoryRepository.findByNameEn(params.get("category"));
        }
        List<Clothes> clothes;
        if(category == null) {
            clothes = clothesRepository.findAll();
        }
        else {
            model.addAttribute("choosedCategory", category);
            //clothes = clothesRepository.findByCategory(category);
            List<Category> allCategories = new LinkedList<>();
            allCategories.add(category);
            List<Category> categories = categoryRepository.findAllByParent(category);
            while (!categories.isEmpty()) {
                allCategories.addAll(categories);
                categories = categoryRepository.findAllByParentIn(categories);
            }
            clothes = clothesRepository.findAllByCategoryIn(allCategories);
        }
        model.addAttribute("clothes", clothes);
        return "main";
    }

    @PostMapping
    public String applyFilter(@RequestParam Map<String, String> params,
                                   Model model,
                                   HttpSession session) {
        Map<Attribute, List<String>> filters = new HashMap<>();
        for (String nameFilter : params.keySet()) {
            Filter filter = filterRepository.findByNameEn(nameFilter);
            if(filter == null) {
                continue;
            }
            Attribute attribute = filter.getAttribute();
            List<String> values = Arrays.asList(params.get(nameFilter).split(","));
            filters.put(attribute, values);
        }
        if(filters.size() == 0) {
            model.addAttribute("clothes", clothesRepository.findAll());
            return "filterClothes";
        }
        List<AttributeValue> attributeValue = attributeValueRepository.findArticleClothesWithFilter(filters);

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
        int sort = 1;
        if(params.get("sort") != null && params.get("sort").trim() != "")
            sort = Integer.parseInt(params.get("sort"));
        model.addAttribute("clothes", sortClothes(clothes, sort));
        return "filterClothes";
    }

    public Model getCommonInfo(Model model, HttpSession session) {
        return getModel(model, session, categoryRepository, filterRepository, attributeValueRepository);
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
}
