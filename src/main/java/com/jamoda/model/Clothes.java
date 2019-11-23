package com.jamoda.model;
import javax.persistence.*;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "clothes")
public class Clothes {
    @Id
    private String article;

    private String brand;
    private String category;
    private String composition;
    private Date date_added;
    private String gender;
    private String made_in;
    private String name;
    private int price;
    private String season;
    private String filename;

    public Clothes() {
    }

    public Clothes(String article, String brand, String category, String composition, String gender, String made_in, String name, int price, String season) {
        this.article = article;
        this.brand = brand;
        this.category = category;
        this.composition = composition;
        this.gender = gender;
        this.made_in = made_in;
        this.name = name;
        this.price = price;
        this.season = season;
    }

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getComposition() {
        return composition;
    }

    public void setComposition(String composition) {
        this.composition = composition;
    }

    public Date getDate_added() {
        return date_added;
    }

    public void setDate_added(Date date_added) {
        this.date_added = date_added;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getMade_in() {
        return made_in;
    }

    public void setMade_in(String made_in) {
        this.made_in = made_in;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public static List<String> getGenderForSelect() {
        List<String> gender = new LinkedList<>();
        gender.add("женский");
        gender.add("мужской");
        gender.add("унисекс");
        return gender;
    }

    public static List<String> getSeasonForSelect() {
        List<String> season = new LinkedList<>();
        season.add("зима");
        season.add("лето");
        return season;
    }
}
