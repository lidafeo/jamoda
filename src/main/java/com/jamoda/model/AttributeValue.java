package com.jamoda.model;


import javax.persistence.*;

@Entity
@Table(name = "attribute_value")
public class AttributeValue {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    //@Column(name = "product_article")
    //private String productArticle;
    //@Column(name = "attribute_id")
    //private long attributeId;
    private String value;
    private Boolean active;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn (name="product_article")
    private Clothes clothes;

    @OneToOne (optional=false, cascade=CascadeType.ALL)
    @JoinColumn (name="attribute_id")
    private Attribute attribute;


    public AttributeValue() {}

    public AttributeValue(String value, Clothes clothes, Attribute attribute) {
        this.value = value;
        this.clothes = clothes;
        this.attribute = attribute;
    }

    public Clothes getClothes() {
        return clothes;
    }

    public void setClothes(Clothes clothes) {
        this.clothes = clothes;
    }

    public Attribute getAttribute() {
        return attribute;
    }

    public void setAttribute(Attribute attribute) {
        this.attribute = attribute;
    }

    public long getId() {
        return id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
