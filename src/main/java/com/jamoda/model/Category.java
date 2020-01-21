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

    //private long parentId;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "parent_id", referencedColumnName = "id")
    private Category parent;

    private String type;

    public void setId(long id) {
        this.id = id;
    }

    public Category() {
    }

    public Category(String nameEn, String nameRus, String type) {
        this.nameEn = nameEn;
        this.nameRus = nameRus;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Category getParent() {
        return parent;
    }

    public void setParent(Category category) {
        this.parent = category;
    }

    public String getIdString(){
        return this.id + "";
    }
}
