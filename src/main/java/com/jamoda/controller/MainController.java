package com.jamoda.controller;

import com.jamoda.service.ClothesService;
import com.jamoda.service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import javax.servlet.http.HttpSession;

@Controller
//@RequestMapping("/")
public class MainController {

    private MainService mainService;
    private ClothesService clothesService;

    @GetMapping("/")
    public String main(Model model, HttpSession session) {
        model.addAttribute("clothes", clothesService.getClothesPopular());
        mainService.getSessionModel(model, session);
        return "main";
    }

    @GetMapping("/about")
    public String about(Model model, HttpSession session) {
        mainService.getSessionModel(model, session);
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
