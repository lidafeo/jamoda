package com.jamoda.service;

import com.jamoda.model.Cart;
import com.jamoda.model.Clothes;
import com.jamoda.model.ProductInCart;
import com.jamoda.repository.CategoryRepository;
import com.jamoda.repository.ClothesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CartService {
    @Autowired
    private ClothesRepository clothesRepository;
    @Autowired
    private CategoryRepository categoryRepository;

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

    public void cleanCart(HttpSession session) {
        session.removeAttribute("SIZES");
        session.removeAttribute("PRODUCTS");
    }

    static HttpSession destroySession(HttpSession session) {
        session.invalidate();
        return session;
    }
}
