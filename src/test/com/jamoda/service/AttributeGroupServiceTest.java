package com.jamoda.service;

import com.jamoda.model.Attribute;
import com.jamoda.model.AttributeGroup;
import com.jamoda.model.AttributeValue;
import com.jamoda.repository.AttributeGroupRepository;
import com.jamoda.repository.AttributeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class AttributeGroupServiceTest {

    AttributeGroupRepository agRepMock = Mockito.mock(AttributeGroupRepository.class);


    @Test
    void saveAttributeGroup() {
        AttributeGroupService agservMock = new AttributeGroupService();
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
        agservMock.setAttributeGroupRepository(agRepMock);

        AttributeGroup ag = new AttributeGroup();
        ag.setName("1");
        List<AttributeGroup> atglist = new ArrayList<>();
        atglist.add(ag);
        Mockito.when(agRepMock.findAll()).thenReturn(atglist);

        Assertions.assertEquals(agservMock.findAll(), atglist);
    }

    @Test
    void findById() {
        AttributeGroupService agservMock = new AttributeGroupService();
        agservMock.setAttributeGroupRepository(agRepMock);

        AttributeGroup ag = new AttributeGroup();
        ag.setName("1");
        Mockito.when(agRepMock.findById(1)).thenReturn(ag);

        Assertions.assertEquals(agservMock.findById(1), ag);
    }

    @Test
    void findByName() {
        AttributeGroupService agservMock = new AttributeGroupService();
        agservMock.setAttributeGroupRepository(agRepMock);

        AttributeGroup ag = new AttributeGroup();
        ag.setName("1");

        Assertions.assertEquals(
                agservMock.findByName("1"),
                agRepMock.findByName("1"));
    }

}