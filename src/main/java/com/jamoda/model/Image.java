package com.jamoda.model;

import javax.persistence.*;

@Entity
@Table(name="image")
public class Image {
    @Id
    private String name;
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
        this.main = false;
        this.hide = false;
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
}
