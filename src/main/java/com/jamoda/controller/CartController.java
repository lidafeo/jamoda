package com.jamoda.controller;

import com.jamoda.model.Cart;
import com.jamoda.model.Clothes;
import com.jamoda.model.ProductInCart;
import com.jamoda.repository.CategoryRepository;
import com.jamoda.repository.ClothesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.jamoda.controller.MainController.getModel;

@Controller
public class CartController {
    @Autowired
    ClothesRepository clothesRepository;
    @Autowired
    CategoryRepository categoryRepository;

    @GetMapping("/cart")
    public String cart(Model model, HttpSession session) {
        getCommonInfo(model, session);
        model.addAttribute("cart", getCart(session));
        return "cart";
    }

    public Model getCommonInfo(Model model, HttpSession session) {
        return getModel(model, session, categoryRepository);
    }

    public Cart getCart(HttpSession session) {
        @SuppressWarnings("unchecked")
        List<String> articleList = (List<String>) session.getAttribute("PRODUCTS");
        if(articleList == null)
            articleList = new ArrayList<>();

        @SuppressWarnings("unchecked")
        List<Integer> sizeList = (List<Integer>) session.getAttribute("SIZES");
        if(sizeList == null)
            sizeList = new ArrayList<>();

        Map<String, ProductInCart> cart = new HashMap<>();
        int commonPrice = 0;
        int commonCount = 0;
        for (int i = 0; i < articleList.size(); i++) {
            Clothes clothes = clothesRepository.findByArticle(articleList.get(i));
            int size = sizeList.get(i);
            commonCount++;
            commonPrice += clothes.getPrice();
            if(clothes == null || size == 0)
                return null;
            String product = articleList.get(i) + "," + size;
            ProductInCart productInCart = new ProductInCart();
            productInCart.setClothes(clothes);
            productInCart.setSize(size);
            int count = 1;
            int price = clothes.getPrice();
            if(cart.get(product) != null) {
                count = cart.get(product).getCount() + 1;
                price = clothes.getPrice() * count;
            }
            productInCart.setCount(count);
            productInCart.setPrice(price);
            cart.put(product, productInCart);
        }
        return new Cart(cart, commonCount, commonPrice);
    }
}
