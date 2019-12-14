package com.jamoda.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Attribute {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    @Column(name = "name_en")
    private String nameEn;
    private String type;
    private Boolean required;

    @OneToMany (mappedBy="attribute")
    private List<AttributeValue> attributesValue;

    @ManyToOne
    @JoinColumn(name = "attribute_group_id", referencedColumnName = "id")
    private AttributeGroup group;

    /*
    @ManyToMany
    @JoinTable(
            name = "group_attribute_product",
            joinColumns = @JoinColumn(name = "attribute_id"),
            inverseJoinColumns = @JoinColumn(name = "group_id")
    )
    private List<AttributeGroup> groups;
     */

    public Attribute() {}

    public Attribute(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public AttributeGroup getGroup() {
        return group;
    }

    public void setGroup(AttributeGroup group) {
        this.group = group;
    }

    public Boolean getRequired() {
        return required;
    }

    public void setRequired(Boolean required) {
        this.required = required;
    }

    public List<AttributeValue> getAttributesValue() {
        return attributesValue;
    }

    public void setAttributesValue(List<AttributeValue> attributesValue) {
        this.attributesValue = attributesValue;
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }
}
