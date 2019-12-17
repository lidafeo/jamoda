package com.jamoda.model;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest

class AttributeTest {

    @MockBean
    Attribute at = new Attribute();


    @Test
    void getId() {
        //com.jamoda.model.Attribute.getId();
    }

    @Test
    void getName() {
    }

    @Test
    void setName() throws NoSuchFieldException, SecurityException,
            IllegalArgumentException, IllegalAccessException {
//        at.setName("Example");
//        Field check = at.getClass().getDeclaredField("name");
//        check.setAccessible(true);
//        Assert.assertEquals("Example", check.get(this.at) );


//        com.jamoda.model.Attribute.setName()
//        at.setName("Example");
//        Assert.assertEquals("Example", at.getName());
    }

    @Test
    void getType() {
    }

    @Test
    void setType() {
    }

    @Test
    void getGroup() {
    }

    @Test
    void setGroup() {
    }

    @Test
    void getRequired() {
    }

    @Test
    void setRequired() {
    }

    @Test
    void getAttributesValue() {
    }

    @Test
    void setAttributesValue() {
    }
}