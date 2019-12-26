package com.jamoda.model;
import javax.persistence.*;
import java.util.*;

@Entity
//@Table(name = "clothes")
public class Clothes {
    @Id
    @Column(name="article", length = 100)
    private String article;

    private Date date_added;
    private String name;
    private int price;
    private int visit = 0;
    private boolean presence = false;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Category category;

    @ManyToMany(targetEntity = AttributeGroup.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<AttributeGroup> attributeGroups = new ArrayList<>();

    @OneToMany(targetEntity = Image.class, mappedBy = "article", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Image> images = new ArrayList<>();

    @OneToMany(mappedBy = "clothes", cascade={CascadeType.ALL})
    @MapKeyColumn(name = "attribute_id")
    //@MapKey(name = "article")
    private Map<String, AttributeValue> attributeValues = new HashMap<String, AttributeValue>();

    @OneToMany(targetEntity = Warehouse.class, mappedBy = "clothes", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Warehouse> warehouses = new ArrayList<>();

    public Clothes() {
    }

    public Clothes(String article, String name, int price) {
        this.article = article;
        this.name = name;
        this.price = price;
    }

    public Clothes(Clothes clothes, String article, List<Image> images) {
        this.name = clothes.name;
        this.price = clothes.price;
        this.article = article;
        this.category = clothes.category;
        this.attributeGroups = clothes.attributeGroups;
        this.attributeValues = clothes.attributeValues;
        this.images = images;
    }

    public String getStringSizes(){
        String sizes = "";
        for(Warehouse warehouse: this.warehouses) {
            sizes += warehouse.getSize() + ":" + warehouse.getCount() + ",";
        }
        if (sizes != null && sizes.length() > 0) {
            sizes = sizes.substring(0, sizes.length() - 1);
        }
        return sizes;
    }
    public String getSizesGap() {
        String sizes = "";
        for(Warehouse warehouse: this.warehouses) {
            sizes += warehouse.getSize() + " ";
        }
        return sizes;
    }
    public String getCountsGap() {
        String counts = "";
        for(Warehouse warehouse: this.warehouses) {
            counts += warehouse.getCount() + " ";
        }
        return counts;
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

    public void addAttributeGroup(AttributeGroup attributeGroup) {
        this.attributeGroups.add(attributeGroup);
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public int getVisit() {
        return visit;
    }

    public void setVisit(int visit) {
        this.visit = visit;
    }

    public void addVisit() {
        this.visit ++;
    }

    public List<Warehouse> getWarehouses() {
        return warehouses;
    }

    public void setWarehouses(List<Warehouse> warehouses) {
        this.warehouses = warehouses;
    }

    public boolean isPresence() {
        return presence;
    }

    public void setPresence(boolean presence) {
        this.presence = presence;
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
        season.add("демисезон");
        return season;
    }

}
