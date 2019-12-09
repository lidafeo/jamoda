package com.jamoda.model;

import java.util.HashMap;
import java.util.Map;

public class Cart {
    private Map<String, ProductInCart> products = new HashMap<>();
    private int count;
    private int price;

    public Cart() {
    }

    public Cart(Map<String, ProductInCart> products) {
        this.products = products;
    }

    public Cart(Map<String, ProductInCart> products, int count, int price) {
        this.products = products;
        this.count = count;
        this.price = price;
    }

    public Map<String, ProductInCart> getProducts() {
        return products;
    }

    public void setProducts(Map<String, ProductInCart> products) {
        this.products = products;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
