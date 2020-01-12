package com.jamoda.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class AttributeGroupTest {

    @Test
    void Attributes() {
        AttributeGroup attributeGroup = new AttributeGroup();
        attributeGroup.setName("name");
        Attribute attribute = new Attribute();
        attribute.setName("name");
        Set<Attribute> aset = new HashSet<>();
        aset.add(attribute);
        attributeGroup.setAttributes(aset);
        Assertions.assertEquals(attributeGroup.getAttributes(), aset);
    }
}