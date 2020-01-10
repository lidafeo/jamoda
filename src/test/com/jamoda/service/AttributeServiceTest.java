package com.jamoda.service;

import com.jamoda.model.Attribute;
import com.jamoda.model.AttributeValue;
import com.jamoda.repository.AttributeRepository;
import com.jamoda.repository.AttributeValueRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class AttributeServiceTest {

    @Test
    void saveAttribute() {
        AttributeService aservMock = new AttributeService();
        AttributeRepository aRepMock = Mockito.mock(AttributeRepository.class);
        aservMock.setAttributeRepository(aRepMock);

        Attribute a = new Attribute();
        a.setName("1");

        when(aRepMock.saveAndFlush(a))
                .thenAnswer(i -> i.getArguments()[0]);
        Assertions.assertEquals(aservMock.saveAttribute(a),
                aRepMock.saveAndFlush(a));
    }

    @Test
    void findAll() {
        AttributeService aservMock = new AttributeService();
        AttributeRepository aRepMock = Mockito.mock(AttributeRepository.class);
        aservMock.setAttributeRepository(aRepMock);

        AttributeValue av = new AttributeValue();
        Attribute a = new Attribute("qwerty");
        a.setNameEn("qwerty");
        av.setAttribute(a);

        Assertions.assertEquals(
                aservMock.findAll(),
                aRepMock.findAll());
    }

    @Test
    void findById() {
        AttributeService aservMock = new AttributeService();
        AttributeRepository aRepMock = Mockito.mock(AttributeRepository.class);
        aservMock.setAttributeRepository(aRepMock);

        AttributeValue av = new AttributeValue();
        Attribute a = new Attribute("qwerty");
        av.setAttribute(a);

        Assertions.assertEquals(
                aservMock.findById(1),
                aRepMock.findById(1));
    }

    @Test
    void findByName() {
        AttributeService aservMock = new AttributeService();
        AttributeRepository aRepMock = Mockito.mock(AttributeRepository.class);
        aservMock.setAttributeRepository(aRepMock);

        AttributeValue av = new AttributeValue();
        Attribute a = new Attribute("1");
        av.setAttribute(a);

        Assertions.assertEquals(
                aservMock.findByName("1"),
                aRepMock.findByName("1"));
    }
}