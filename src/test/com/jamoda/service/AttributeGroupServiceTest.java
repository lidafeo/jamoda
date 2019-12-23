package com.jamoda.service;

import com.jamoda.model.Attribute;
import com.jamoda.model.AttributeGroup;
import com.jamoda.model.AttributeValue;
import com.jamoda.repository.AttributeGroupRepository;
import com.jamoda.repository.AttributeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class AttributeGroupServiceTest {

    @Test
    void saveAttributeGroup() {
        AttributeGroupService agservMock = new AttributeGroupService();
        AttributeGroupRepository agRepMock = Mockito.mock(AttributeGroupRepository.class);
        agservMock.setAttributeGroupRepository(agRepMock);

        AttributeGroup ag = new AttributeGroup();
        ag.setName("1");

        when(agRepMock.saveAndFlush(ag))
                .thenAnswer(i -> i.getArguments()[0]);
        Assertions.assertEquals(agservMock.saveAttributeGroup(ag),
                agRepMock.saveAndFlush(ag));
    }

    @Test
    void findAll() {
        AttributeGroupService agservMock = new AttributeGroupService();
        AttributeGroupRepository agRepMock = Mockito.mock(AttributeGroupRepository.class);
        agservMock.setAttributeGroupRepository(agRepMock);

        AttributeGroup ag = new AttributeGroup();
        ag.setName("1");

        Assertions.assertEquals(
                agservMock.findAll(),
                agRepMock.findAll());
    }

    @Test
    void findById() {
        AttributeGroupService agservMock = new AttributeGroupService();
        AttributeGroupRepository agRepMock = Mockito.mock(AttributeGroupRepository.class);
        agservMock.setAttributeGroupRepository(agRepMock);

        AttributeGroup ag = new AttributeGroup();
        ag.setName("1");

        Assertions.assertEquals(
                agservMock.findById(1),
                agRepMock.findById(1));
    }

    @Test
    void findByName() {
        AttributeGroupService agservMock = new AttributeGroupService();
        AttributeGroupRepository agRepMock = Mockito.mock(AttributeGroupRepository.class);
        agservMock.setAttributeGroupRepository(agRepMock);

        AttributeGroup ag = new AttributeGroup();
        ag.setName("1");

        Assertions.assertEquals(
                agservMock.findByName("1"),
                agRepMock.findByName("1"));
    }
}