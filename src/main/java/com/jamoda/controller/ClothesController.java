package com.jamoda.controller;

import com.jamoda.model.Category;
import com.jamoda.model.Clothes;
import com.jamoda.model.User;
import com.jamoda.service.ClothesService;
import com.jamoda.service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;

@Controller
public class ClothesController {

    private MainService mainService;
    private ClothesService clothesService;

    @GetMapping("/clothes")
    public String getClothes(@RequestParam String article, Model model, @AuthenticationPrincipal User user) {
        model.addAttribute("customer", user);
        mainService.getSessionModel(model);

        Clothes clothes = clothesService.findByArticle(article);
        if(clothes == null) {
            model.addAttribute("error", "Товар не найден");
            return "clothes";
        }
        model.addAttribute("clothes", clothes);
        model.addAttribute("sizes", clothesService.getSizes(clothes.getWarehouses()));
        model.addAttribute("commonCount", clothesService.getCountProductInWarehouse(clothes.getWarehouses()));

        List<Category> categories = new LinkedList<>();
        Category category = clothes.getCategory();
        while (category != null) {
            categories.add(0, category);
            category = category.getParent();
        }
        model.addAttribute("list_categories", categories);

        clothes.addVisit();
        clothesService.saveClothes(clothes);
        return "clothes";
    }

    /*
    @PostMapping("/clothes")
    public String addClothesInCart(String article_clothes,
                                   @RequestParam("size") int size,
                                   Model model,
                                   HttpSession session) {
        Clothes clothes = clothesService.findByArticle(article_clothes);
        if(clothes == null) {
            mainService.getSessionModel(model, session);
            model.addAttribute("error", "Такого товара нет!");
            return "clothes";
        }

        mainService.getSessionModel(model, session);
        model.addAttribute("message", "ок");
        model.addAttribute("clothes", clothes);
        model.addAttribute("sizes", clothesService.getSizes(clothes.getWarehouses()));
        return "clothes";
    }
     */
    /*
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
     */

    @Autowired
    public void setMainService(MainService mainService) {
        this.mainService = mainService;
    }
    @Autowired
    public void setClothesService(ClothesService clothesService) {
        this.clothesService = clothesService;
    }
}
