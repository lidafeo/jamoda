package com.jamoda.service;

import com.jamoda.model.Cart;
import com.jamoda.model.Clothes;
import com.jamoda.model.ProductInCart;
import com.jamoda.repository.CategoryRepository;
import com.jamoda.repository.ClothesRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;

class CartServiceTest {

    @Test
    void getCart() {
        CartService CartServMock = new CartService();
        CategoryRepository catRepMock = Mockito.mock(CategoryRepository.class);
        CartServMock.setCategoryRepository(catRepMock);
        ClothesRepository ClothesRepMock = Mockito.mock(ClothesRepository.class);
        CartServMock.setClothesRepository(ClothesRepMock);

        HttpSession session = mock(HttpSession.class);
        ArrayList<String> articleList = new ArrayList<>();
        articleList.add("123");
        session.setAttribute("PRODUCTS", "qwerty");
        session.setAttribute("SIZES", 48);
        ArrayList<Integer> sizelist = new ArrayList<>();
        sizelist.add(48);
        Clothes clothes = new Clothes();
        clothes.setPrice(10);
        clothes.setArticle("qwerty");
        Mockito.when(session.getAttribute("SIZES")).thenReturn(sizelist);
        Mockito.when(session.getAttribute("PRODUCTS")).thenReturn(articleList);
        Mockito.when(ClothesRepMock.findByArticle(anyString())).thenReturn(clothes);

        ProductInCart pr = new ProductInCart();
        pr.setClothes(clothes);
        pr.setSize(48);
        pr.setCount(1);
        pr.setPrice(10);
        Map<String, ProductInCart> prod = new HashMap<>();
        prod.put("123,48", pr);
        Cart cart = new Cart(prod, 1, 10);
        Cart c = CartServMock.getCart(session);
        Assertions.assertEquals(c.getPrice(), cart.getPrice());
        Assertions.assertEquals(c.getCount(), cart.getCount());
       // Assertions.assertEquals(c.getProducts(), cart.getProducts());
    }

    @Test
    void getCartNull() {
        CartService CartServMock = new CartService();
        CategoryRepository catRepMock = Mockito.mock(CategoryRepository.class);
        CartServMock.setCategoryRepository(catRepMock);
        ClothesRepository ClothesRepMock = Mockito.mock(ClothesRepository.class);
        CartServMock.setClothesRepository(ClothesRepMock);

        HttpSession session = mock(HttpSession.class);
        session.setAttribute("PRODUCTS", "qwerty");
        session.setAttribute("SIZES", 48);
        Clothes clothes = new Clothes();
        Mockito.when(session.getAttribute("SIZES")).thenReturn(null);
        Mockito.when(session.getAttribute("PRODUCTS")).thenReturn(null);
        Mockito.when(ClothesRepMock.findByArticle(anyString())).thenReturn(clothes);

        ProductInCart pr = new ProductInCart();
        pr.setClothes(clothes);
        Map<String, ProductInCart> prod = new HashMap<>();
        Cart cart = new Cart(prod, 0, 0);
        Cart c = CartServMock.getCart(session);
        Assertions.assertEquals(c.getPrice(), cart.getPrice());
        Assertions.assertEquals(c.getCount(), cart.getCount());
        Assertions.assertEquals(c.getPrice(), 0);
        Assertions.assertEquals(c.getCount(), 0);
    }

    @Test
    void addProductInCart() {
        CartService CartServMock = new CartService();

        HttpSession session = mock(HttpSession.class);
        ArrayList<String> articleList = new ArrayList<>();
        articleList.add("123");
        session.setAttribute("PRODUCTS", null);
        session.setAttribute("SIZES", null);
        ArrayList<Integer> sizelist = new ArrayList<>();
        sizelist.add(48);
        Clothes clothes = new Clothes();
        clothes.setPrice(10);
        clothes.setArticle("qwerty");
        HttpSession session2 = mock(HttpSession.class);
        session2.setAttribute("PRODUCTS", "qwerty");
        session2.setAttribute("SIZES", 48);
        Mockito.when(session.getAttribute("SIZES")).thenReturn(sizelist);
        Mockito.when(session.getAttribute("PRODUCTS")).thenReturn(articleList);

        Assertions.assertEquals(CartServMock.addProductInCart(session, clothes, 1),
                session2);
    }

    @Test
    void cleanCartTest() {
        CartService CartServMock = new CartService();

        HttpSession session = mock(HttpSession.class);
        session.setAttribute("PRODUCTS", "qwerty");
        session.setAttribute("SIZES", 48);
        CartServMock.cleanCart(session);
        Assertions.assertNull(session.getAttribute("PRODUCTS"));
        Assertions.assertNull(session.getAttribute("SIZES"));
    }

    @Test
    void destroySession() {
        CartService CartServMock = new CartService();

        HttpSession session = mock(HttpSession.class);
        session.setAttribute("PRODUCTS", "qwerty");
        session.setAttribute("SIZES", 48);
        CartServMock.destroySession(session);
        Assertions.assertNull(session.getAttribute("PRODUCTS"));
        Assertions.assertNull(session.getAttribute("SIZES"));
    }
}