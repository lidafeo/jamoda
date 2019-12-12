package com.jamoda.controller;

import com.jamoda.model.Category;
import com.jamoda.model.Clothes;
import com.jamoda.repository.CategoryRepository;
import com.jamoda.repository.ClothesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

import java.util.List;
import java.util.Map;

import static com.jamoda.controller.MainController.getModel;

@Controller
@RequestMapping("/filter")
public class FilterController {
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    private ClothesRepository clothesRepository;

    @GetMapping
    public String main(@RequestParam Map<String, String> params, Model model, HttpSession session) {
        getCommonInfo(model, session);
        Category category = null;
        if(params.get("category") != null)
            category = categoryRepository.findByNameEn(params.get("category"));
        List<Clothes> clothes;
        if(category == null)
            clothes = clothesRepository.findAll();
        else {
            clothes = clothesRepository.findByCategory(category);
            List<Category> categories = categoryRepository.findAllByParent(category);
            clothes = clothesRepository.findAllByCategoryIn(categories);
        }
        model.addAttribute("clothes", clothes);
        return "main";
    }

    public Model getCommonInfo(Model model, HttpSession session) {
        return getModel(model, session, categoryRepository);
    }
}
