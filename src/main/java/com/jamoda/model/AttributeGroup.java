package com.jamoda.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "attribute_group")
public class AttributeGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;

    @OneToMany(targetEntity = Attribute.class, mappedBy = "group", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Attribute> attributes = new ArrayList<>();

    public AttributeGroup() {}

    public AttributeGroup(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public List<Attribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<Attribute> attributes) {
        this.attributes = attributes;
    }
}
