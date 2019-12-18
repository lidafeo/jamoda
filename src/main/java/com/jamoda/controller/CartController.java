package com.jamoda.controller;

import com.jamoda.service.CartService;
import com.jamoda.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;


@Controller
public class CartController {

    @Autowired
    public void setCartService(CartService cartService) {
        this.cartService = cartService;
    }
    private CartService cartService;

    @Autowired
    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
    private CategoryService categoryService;

    @GetMapping("/cart")
    public String cart(Model model, HttpSession session) {
        model.addAttribute("categories", categoryService.findMainCategory()); //для панели
        model.addAttribute("cart", cartService.getCart(session)); //для отображения корзины
        return "cart";
    }

    @GetMapping("/cart/clean")
    public String cleanCart(HttpSession session) {
        cartService.cleanCart(session);
        return "redirect:/cart";
    }
}
