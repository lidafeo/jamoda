package com.jamoda.controller;

import com.jamoda.model.Clothes;
import com.jamoda.service.CartService;
import com.jamoda.service.ClothesService;
import com.jamoda.service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
public class ClothesController {

    @Autowired
    private MainService mainService;
    @Autowired
    private ClothesService clothesService;
    @Autowired
    private CartService cartService;

    @GetMapping("/clothes")
    public String getClothes(@RequestParam String article, Model model, HttpSession session) {
        Clothes clothes = clothesService.findByArticle(article);
        model.addAttribute("clothes", clothes);
        clothes.addVisit();
        clothesService.saveClothes(clothes);
        mainService.getModel(model, session);
        return "clothes";
    }

    @PostMapping("/clothes")
    public String addClothesInCart(String article_clothes,
                                   @RequestParam("size") int size,
                                   Model model,
                                   HttpSession session) {
        Clothes clothes = clothesService.findByArticle(article_clothes);
        if(clothes == null) {
            mainService.getModel(model, session);
            model.addAttribute("error", "Такого товара нет!");
            return "clothes";
        }
        cartService.addProductInCart(session, clothes, size);

        mainService.getModel(model, session);
        model.addAttribute("message", "ок");
        model.addAttribute("clothes", clothes);
        return "clothes";
    }

    @PostMapping(value = "/clothes_json", produces = "application/json")
    public String addClothesInCartJson(String article_clothes,
                                   @RequestParam("size") int size,
                                   Model model,
                                   HttpSession session) {
        Clothes clothes = clothesService.findByArticle(article_clothes);
        if(clothes == null) {
            model.addAttribute("error", "error");
            return "json";
        }
        model.addAttribute("message", "1");
        cartService.addProductInCart(session, clothes, size);
        return "json";
    }
}
