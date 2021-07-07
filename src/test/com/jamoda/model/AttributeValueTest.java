package com.jamoda.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.w3c.dom.Attr;

import static org.junit.jupiter.api.Assertions.*;

class AttributeValueTest {

    @Test
    void AttributeValue() {
        Clothes clothes = new Clothes();
        clothes.setName("qwerty");
        Attribute attribute = new Attribute();
        attribute.setName("qwerty");
        AttributeValue atVal = new AttributeValue("value", clothes, attribute);

        Clothes cl = atVal.getClothes();
        atVal.setClothes(cl);
        Assertions.assertEquals(atVal.getClothes(), cl);
        Attribute att = atVal.getAttribute();
        Assertions.assertEquals(atVal.getAttribute(), attribute);
        Assertions.assertEquals(atVal.getValue(), "value");
        atVal.setActive(true);
        Assertions.assertEquals(atVal.getActive(), true);

    }
}