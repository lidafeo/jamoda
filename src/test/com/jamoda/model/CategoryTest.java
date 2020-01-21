package com.jamoda.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CategoryTest {

    @Test
    void Category() {
        Category category = new Category(
                "name", "name", "type");
        Assertions.assertEquals(category.getNameEn(), "name");
        Assertions.assertEquals(category.getNameRus(), "name");
        Assertions.assertEquals(category.getType(), "type");
        category.setNameEn("nameen");
        Assertions.assertEquals(category.getNameEn(), "nameen");

    }
}