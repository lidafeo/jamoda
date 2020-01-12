package com.jamoda.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class ClothesTest {

    @Test
    void Clothes() {
        Clothes clothes = new Clothes("article", "name", 1000);
        Assertions.assertEquals(clothes.getArticle(), "article");
        Assertions.assertEquals(clothes.getName(), "name");
        Assertions.assertEquals(clothes.getPrice(), 1000);
        List<String> season = new LinkedList<>();
        season.add("зима");
        season.add("лето");
        season.add("демисезон");
        Assertions.assertEquals(clothes.getSeasonForSelect(), season);
        List<String> gender = new LinkedList<>();
        gender.add("женский");
        gender.add("мужской");
        gender.add("унисекс");
        Assertions.assertEquals(clothes.getGenderForSelect(), gender);
        clothes.setVisit(2);
        Assertions.assertEquals(clothes.getVisit(), 2);
        clothes.setPrice(1000);
        Assertions.assertEquals(clothes.getPrice(), 1000);
        clothes.setName("name");
        Assertions.assertEquals(clothes.getName(), "name");
        Date date = new Date(2020, 1, 1);
        clothes.setDate_added(date);
        Assertions.assertEquals(clothes.getDate_added(), date);
    }

    @Test
    void getStringSizes() {
        Clothes clothes = new Clothes("article", "name", 1000);
        String sizes = "";
        Assertions.assertEquals(clothes.getStringSizes(), sizes);
    }

    @Test
    void getSizesGap() {
        Clothes clothes = new Clothes("article", "name", 1000);
        String sizes = "";
        Assertions.assertEquals(clothes.getSizesGap(), sizes);
    }

    @Test
    void getgetCountsGapSizesGap() {
        Clothes clothes = new Clothes("article", "name", 1000);
        String count = "";
        Assertions.assertEquals(clothes.getCountsGap(), count);
    }

    @Test
    void AttributeValues() {
        Clothes clothes = new Clothes("article", "name", 1000);
        Warehouse wh = new Warehouse(clothes, 48, 1);
        List<Warehouse> whs = List.of(wh);
        AttributeValue attributeValue = new AttributeValue();
        attributeValue.setClothes(clothes);

        Map<String, AttributeValue> map = new TreeMap<>();
        map.put("40", attributeValue);
        clothes.setAttributeValues(map);
        clothes.setWarehouses(whs);
        Image image = new Image();
        image.setArticle("article");
        List<Image> ilist = new ArrayList<>();
        ilist.add(image);
        clothes.setImages(ilist);

        Assertions.assertEquals(clothes.getAttributeValues(), map);
        Assertions.assertEquals(clothes.getWarehouses(), whs);
        Assertions.assertEquals(clothes.getImages(), ilist);
    }


}