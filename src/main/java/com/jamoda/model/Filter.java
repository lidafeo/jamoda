package com.jamoda.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Entity
public class Filter {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    @Column(name = "name_en")
    private String nameEn;
    private Boolean active = true;
    //strict_equal = 0, not_strict_equal = 1, interval = 2
    private int type = 0;
    @Column(name = "search_all")
    private boolean searchAll;
    //@OneToMany(targetEntity = FilterValue.class, mappedBy = "filter", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    //private List<FilterValue> values = new LinkedList<>();
    @ElementCollection
    @CollectionTable(name = "filter_values", joinColumns = @JoinColumn(name = "filter_id"))
    @Column(name = "filter_val")
    private List<String> values;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "attribute_id", referencedColumnName = "id")
    private Attribute attribute;

    public Filter() {
    }

    public Filter(String name, Boolean active) {
        this.name = name;
        this.active = active;
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

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Attribute getAttribute() {
        return attribute;
    }

    public void setAttribute(Attribute attribute) {
        this.attribute = attribute;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public boolean isSearchAll() {
        return searchAll;
    }

    public void setSearchAll(boolean searchAll) {
        this.searchAll = searchAll;
    }

    public List<String> getValues() {
        return values;
    }

    public void setValues(List<String> values) {
        this.values = values;
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }
}
