package com.jamoda.model;

import javax.persistence.*;

@Entity
@Table(name="images")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name="name", length = 100)
    private String name;
    @Column(name="article", length = 100)
    private String article;
    private boolean hide;
    private boolean main;

    //@ManyToOne
    //@JoinColumn(name = "clothes_article", referencedColumnName = "article")
    //private Clothes clothes;

    public Image() {}

    public Image(String name, String article) {
        this.name = name;
        this.article = article;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }
/*
    public boolean isHide() {
        return hide;
    }

    public void setHide(boolean hide) {
        this.hide = hide;
    }

    public boolean isMain() {
        return main;
    }

    public void setMain(boolean main) {
        this.main = main;
    }

 */
}
