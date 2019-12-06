package com.jamoda.controller;

import com.jamoda.model.Clothes;
import com.jamoda.repository.CategoryRepository;
import com.jamoda.repository.ClothesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/clothes")
public class ClothesController {
    @Autowired
    private ClothesRepository clothesRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("{article}")
    public String getClothes(@PathVariable String article, Model model) {
        Clothes clothes = clothesRepository.findByArticle(article);
        System.out.println(clothes.getAttributeValues());
        model.addAttribute("clothes", clothes);
        //model.addAttribute("groups", clothes.getAttributeGroups());
        //model.addAttribute("values", clothes.getAttributeValues());
        model.addAttribute("categories", categoryRepository.findAllByType("main"));
        return "clothes";
    }
}
