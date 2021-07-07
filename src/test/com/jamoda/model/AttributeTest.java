package com.jamoda.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AttributeTest {

    @Test
    void getName() {
        Attribute attribute = new Attribute();
        attribute.setType("type");
        AttributeGroup atg = new AttributeGroup();
        atg.setName("name");
        Assertions.assertEquals(attribute.getType(), "type");
        attribute.setGroup(atg);
        Assertions.assertEquals(attribute.getGroup(), atg);
        attribute.setRequired(true);
        Assertions.assertEquals(attribute.getRequired(), true);
        AttributeValue atVal = new AttributeValue();
        List<AttributeValue> atvlist = new ArrayList<>();
        atvlist.add(atVal);
        attribute.setAttributesValue(atvlist);
        Assertions.assertEquals(attribute.getAttributesValue(), atvlist);
        attribute.setNameEn("nameen");
        Assertions.assertEquals(attribute.getNameEn(), "nameen");
    }
}