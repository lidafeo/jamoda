package com.jamoda.controller;

import com.jamoda.service.ClothesService;
import com.jamoda.service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
//@RequestMapping("/")
public class MainController {

    private MainService mainService;
    private ClothesService clothesService;

    @GetMapping("/")
    public String main(Model model, @PageableDefault(size = 12) Pageable pageable) {
        model.addAttribute("page", clothesService.getClothesPopular(pageable));
        //model.addAttribute("page", clothesService.getClothesPopular(pageable));
        model.addAttribute("url", "/?");
        mainService.getSessionModel(model);
        return "main";
    }

    @GetMapping("/about")
    public String about(Model model) {
        mainService.getSessionModel(model);
        return "about";
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
