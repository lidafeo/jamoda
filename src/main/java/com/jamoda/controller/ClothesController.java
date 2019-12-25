package com.jamoda.controller;

import com.jamoda.model.Clothes;
import com.jamoda.service.ClothesService;
import com.jamoda.service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ClothesController {

    private MainService mainService;
    private ClothesService clothesService;

    @GetMapping("/clothes")
    public String getClothes(@RequestParam String article, Model model) {
        Clothes clothes = clothesService.findByArticle(article);
        model.addAttribute("clothes", clothes);
        model.addAttribute("sizes", clothesService.getSizes(clothes.getWarehouses()));
        model.addAttribute("commonCount", clothesService.getCountProductInWarehouse(clothes.getWarehouses()));
        clothes.addVisit();
        clothesService.saveClothes(clothes);
        mainService.getSessionModel(model);
        return "clothes";
    }

    @Autowired
    public void setMainService(MainService mainService) {
        this.mainService = mainService;
    }
    @Autowired
    public void setClothesService(ClothesService clothesService) {
        this.clothesService = clothesService;
    }
}
