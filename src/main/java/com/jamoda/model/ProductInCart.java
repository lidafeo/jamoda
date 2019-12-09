package com.jamoda.model;

public class ProductInCart {
    private Clothes clothes;
    private int size;
    private int count;
    private int price;

    public ProductInCart() {
    }

    public ProductInCart(Clothes clothes, int size, int count) {
        this.clothes = clothes;
        this.size = size;
        this.count = count;
    }

    public Clothes getClothes() {
        return clothes;
    }

    public void setClothes(Clothes clothes) {
        this.clothes = clothes;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
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
