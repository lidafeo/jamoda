package com.jamoda.controller;

import com.jamoda.model.Cart;
import com.jamoda.model.Clothes;
import com.jamoda.repository.AttributeValueRepository;
import com.jamoda.repository.CategoryRepository;
import com.jamoda.repository.ClothesRepository;
import com.jamoda.repository.FilterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.jamoda.controller.MainController.destroySession;
import static com.jamoda.controller.MainController.getModel;

@Controller
public class ClothesController {
    @Autowired
    private ClothesRepository clothesRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private FilterRepository filterRepository;
    @Autowired
    private AttributeValueRepository attributeValueRepository;

    @GetMapping("/clothes")
    public String getClothes(@RequestParam String article, Model model, HttpSession session) {
        Clothes clothes = clothesRepository.findByArticle(article);
        model.addAttribute("clothes", clothes);
        clothes.addVisit();
        clothesRepository.saveAndFlush(clothes);
        getCommonInfo(model, session);
        return "clothes";
    }

    @PostMapping("/clothes")
    public String addClothesInCart(String article_clothes,
                                   @RequestParam("size") int size,
                                   Model model,
                                   HttpSession session) {
        Clothes clothes = clothesRepository.findByArticle(article_clothes);
        if(clothes == null) {
            getCommonInfo(model, session);
            model.addAttribute("error", "Такого товара нет!");
            model.addAttribute("clothes", clothes);
            return "clothes";
        }
        addProductInCart(session, clothes, size);

        getCommonInfo(model, session);
        model.addAttribute("message", "ок");
        model.addAttribute("clothes", clothes);
        return "clothes";
    }

    @PostMapping(value = "/clothes_json", produces = "application/json")
    public String addClothesInCartJson(String article_clothes,
                                   @RequestParam("size") int size,
                                   Model model,
                                   HttpSession session) {
        Clothes clothes = clothesRepository.findByArticle(article_clothes);
        if(clothes == null) {
            model.addAttribute("error", "error");
            return "json";
        }
        model.addAttribute("message", "1");
        addProductInCart(session, clothes, size);
        return "json";
    }

    public Model getCommonInfo(Model model, HttpSession session) {
        return getModel(model, session, categoryRepository, filterRepository, attributeValueRepository);
    }

    public HttpSession addProductInCart(HttpSession session, Clothes clothes, int size) {
        @SuppressWarnings("unchecked")
        List<String> articleList = (List<String>) session.getAttribute("PRODUCTS");
        if(articleList == null)
            articleList = new ArrayList<>();
        articleList.add(clothes.getArticle());
        session.setAttribute("PRODUCTS", articleList);

        @SuppressWarnings("unchecked")
        List<Integer> sizeList = (List<Integer>) session.getAttribute("SIZES");
        if(sizeList == null)
            sizeList = new ArrayList<>();
        sizeList.add(size);
        session.setAttribute("SIZES", sizeList);

        return session;
    }
}
