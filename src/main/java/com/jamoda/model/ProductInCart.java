package com.jamoda.model;

public class ProductInCart {
    private String article;
    private Integer count;
    private Integer size;
    private Integer price;

    public ProductInCart() {
    }

    public ProductInCart(String article, Integer size, Integer count) {
        this.article = article;
        this.count = count;
        this.size = size;
    }

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
