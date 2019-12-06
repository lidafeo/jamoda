package com.jamoda.model;
import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "clothes")
public class Clothes {
    @Id
    private String article;

    private String category;
    private Date date_added;
    private String name;
    private int price;

    @OneToMany(targetEntity = AttributeGroup.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<AttributeGroup> attributeGroups = new ArrayList<>();

    @OneToMany(targetEntity = Image.class, mappedBy = "article", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Image> images = new ArrayList<>();

    @OneToMany(mappedBy = "clothes")
    @MapKeyColumn(name = "attribute_id")
    //@MapKey(name = "article")
    private Map<String, AttributeValue> attributeValues = new HashMap<String, AttributeValue>();

    /*
    @ManyToMany
    @JoinTable(
            name = "attribute_value_product",
            joinColumns={@JoinColumn(name="fk_clothes", referencedColumnName="article")},
            inverseJoinColumns={@JoinColumn(name="fk_attribute_value", referencedColumnName="id")}
    )
    @MapKey(name = "productArticle")
    private Map<String, AttributeValue> attributeValues = new HashMap<String, AttributeValue>();
     */

    public Clothes() {
    }

    public Clothes(String article, String category, String name, int price) {
        this.article = article;
        this.category = category;
        this.name = name;
        this.price = price;
    }

    public Map<String, AttributeValue> getAttributeValues() {
        return attributeValues;
    }

    public void setAttributeValues(Map<String, AttributeValue> attributeValues) {
        this.attributeValues = attributeValues;
    }

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Date getDate_added() {
        return date_added;
    }

    public void setDate_added(Date date_added) {
        this.date_added = date_added;
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

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public List<AttributeGroup> getAttributeGroups() {
        return attributeGroups;
    }

    public void setAttributeGroups(List<AttributeGroup> attributeGroups) {
        this.attributeGroups = attributeGroups;
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
        season.add("межсезон");
        return season;
    }

}
