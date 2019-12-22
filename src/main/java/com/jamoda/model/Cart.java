package com.jamoda.model;

public class Cart {
    private ProductInCart[] cart;
    private Integer price;
    private Integer count;

    public Cart() {
    }

    public Cart(ProductInCart[] cart) {
        this.cart = cart;
    }

    public ProductInCart[] getCart() {
        return cart;
    }

    public void setCart(ProductInCart[] cart) {
        this.cart = cart;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
