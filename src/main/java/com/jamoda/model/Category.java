package com.jamoda.model;

import javax.persistence.*;

@Entity
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "name_en")
    private String nameEn;
    @Column(name = "name_rus")
    private String nameRus;

    private int parentId;
    private String type;

    public Category() {
    }

    public Category(String nameEn, String nameRus, int parentId, String type) {
        this.nameEn = nameEn;
        this.nameRus = nameRus;
        this.parentId = parentId;
        this.type = type;
    }

    public long getId() {
        return id;
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public String getNameRus() {
        return nameRus;
    }

    public void setNameRus(String nameRus) {
        this.nameRus = nameRus;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
