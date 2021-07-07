package com.jamoda.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WarehouseTest {

    @Test
    void Clothes() {
        Clothes clothes = new Clothes();
        clothes.setName("qwerty");
        Warehouse wh = new Warehouse();
        wh.setClothes(clothes);
        wh.setSize(40);
        Assertions.assertEquals(wh.getClothes(), clothes);
        Assertions.assertEquals(wh.getSize(), 40);
    }
}