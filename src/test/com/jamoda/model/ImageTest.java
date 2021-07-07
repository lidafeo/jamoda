package com.jamoda.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ImageTest {

    @Test
    void Image() {
        Image img = new Image("123", "1234 ");
        img.setName("name");
        img.setArticle("article");
        Assertions.assertEquals(img.getName(), "name");
        Assertions.assertEquals(img.getArticle(), "article");
    }
}