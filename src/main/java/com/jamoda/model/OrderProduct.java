package com.jamoda.model;

import javax.persistence.*;

@Entity
public class OrderProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private Integer count;
    private Integer size;
    private Integer price;
    @ManyToOne
    @JoinColumn(name = "clothes_id", referencedColumnName = "article")
    private Clothes clothes;
    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private Order order;

    public OrderProduct() {
    }

    public OrderProduct(Integer count, Integer size, Integer price, Order order, Clothes clothes) {
        this.count = count;
        this.size = size;
        this.price = price;
        this.clothes = clothes;
        this.order = order;
    }

    public long getId() {
        return id;
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

    public Clothes getClothes() {
        return clothes;
    }

    public void setClothes(Clothes clothes) {
        this.clothes = clothes;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
