package com.jamoda.model;

import javax.persistence.*;

@Entity
public class Warehouse {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "clothes_article", referencedColumnName = "article")
    private Clothes clothes;
    private int size;
    private int count = 0;

    public Warehouse() {
    }

    public Warehouse(Clothes clothes, int size, int count) {
        this.clothes = clothes;
        this.size = size;
        this.count = count;
    }

    public long getId() {
        return id;
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
}
