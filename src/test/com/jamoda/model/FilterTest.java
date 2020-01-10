package com.jamoda.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FilterTest {

    @Test
    void Filter() {
        Filter filter = new Filter("name", true);
        filter.setActive(false);
        Assertions.assertEquals(filter.getActive(), false);
        filter.setType(1);
        Assertions.assertEquals(filter.getType(),1);
        List<String> vals = new ArrayList<>();
        vals.add("123");
        filter.setValues(vals);
        Assertions.assertEquals(filter.getValues(), vals);
    }
}