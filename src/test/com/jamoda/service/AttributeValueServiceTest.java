package com.jamoda.service;

import com.jamoda.model.Attribute;
import com.jamoda.model.AttributeValue;
import com.jamoda.model.Clothes;
import com.jamoda.model.Warehouse;
import com.jamoda.repository.AttributeRepository;
import com.jamoda.repository.AttributeValueRepository;
import com.jamoda.repository.WarehouseRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class AttributeValueServiceTest {

    @Test
    void saveAttributeValue() {
        AttributeValueService avservMock = new AttributeValueService();
        AttributeValueRepository avRepMock = Mockito.mock(AttributeValueRepository.class);
        avservMock.setAttributeValueRepository(avRepMock);

        AttributeValue av = new AttributeValue();
        av.setValue("qwerty");

        when(avRepMock.saveAndFlush(av))
                .thenAnswer(i -> i.getArguments()[0]);
        Assertions.assertEquals(avservMock.saveAttributeValue(av),
                avRepMock.save(av));
    }

    @Test
    void findById() {
        AttributeValueService avservMock = new AttributeValueService();
        AttributeValueRepository avRepMock = Mockito.mock(AttributeValueRepository.class);
        avservMock.setAttributeValueRepository(avRepMock);

        AttributeValue av = new AttributeValue();
        Attribute a = new Attribute();
        a.setName("qwerty");
        av.setAttribute(a);

        Assertions.assertEquals(
                avservMock.findById(1),
                avRepMock.findById(1));
    }
}