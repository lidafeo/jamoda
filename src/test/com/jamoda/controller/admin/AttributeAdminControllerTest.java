package com.jamoda.controller.admin;

import com.jamoda.model.Attribute;
import com.jamoda.model.AttributeGroup;
import com.jamoda.service.AttributeGroupService;
import com.jamoda.service.AttributeService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;

class AttributeAdminControllerTest {

    AttributeGroupService agServMock = Mockito.mock(AttributeGroupService.class);
    AttributeService aServMock = Mockito.mock(AttributeService.class);


    @Test
    void pageAddAttribute() {
        AttributeAdminController attributeAdminController = new AttributeAdminController();
        attributeAdminController.setAttributeGroupService(agServMock);

        Model model = Mockito.mock(Model.class);
        AttributeGroup a = new AttributeGroup();
        a.setName("qwerty");
        List<AttributeGroup> al = new ArrayList<>();
        al.add(a);

        Mockito.when(agServMock.findAll()).thenReturn(al);

        Assertions.assertEquals("admin/addAttribute",
                attributeAdminController.pageAddAttribute(model));
}

    @Test
    void addAttribute() {
        AttributeAdminController attributeAdminController = new AttributeAdminController();
        attributeAdminController.setAttributeGroupService(agServMock);
        attributeAdminController.setAttributeService(aServMock);

        Attribute attribute = new Attribute();
        attribute.setName("qwerty");
        List<AttributeGroup> attributes = new ArrayList<>();
        AttributeGroup attributeGroup = new AttributeGroup();
        attributeGroup.setName("123");
        attributes.add(attributeGroup);
        Model model = Mockito.mock(Model.class);

        Mockito.when(agServMock.findAll()).thenReturn(attributes);
        Mockito.when(aServMock.findByName(anyString())).thenReturn(attribute);
        Mockito.when(agServMock.findById(anyInt())).thenReturn(attributeGroup);
        Mockito.when(aServMock.saveAttribute(any(Attribute.class))).thenReturn(attribute);


        Assertions.assertEquals("admin/addAttribute",
                attributeAdminController.addAttribute(
                        attribute, 1, model));
    }

    @Test
    void addAttribute1() {
        AttributeAdminController attributeAdminController = new AttributeAdminController();
        attributeAdminController.setAttributeGroupService(agServMock);
        attributeAdminController.setAttributeService(aServMock);

        long groupId = 1;
        Attribute attribute = new Attribute();
        attribute.setName("qwerty");
        List<AttributeGroup> attributes = new ArrayList<>();
        AttributeGroup attributeGroup = new AttributeGroup();
        attributeGroup.setName("123");
        attributes.add(attributeGroup);
        Model model = Mockito.mock(Model.class);

        Mockito.when(agServMock.findById(groupId)).thenReturn(attributeGroup);
        Mockito.when(aServMock.saveAttribute(any(Attribute.class))).thenReturn(attribute);


        Assertions.assertEquals("admin/addAttribute",
                attributeAdminController.addAttribute(
                        attribute, groupId, model));
    }

    @Test
    void addAttribute2() {
        AttributeAdminController attributeAdminController = new AttributeAdminController();
        attributeAdminController.setAttributeGroupService(agServMock);
        attributeAdminController.setAttributeService(aServMock);

        Attribute attribute = new Attribute();
        attribute.setName("qwerty");
        List<AttributeGroup> attributes = new ArrayList<>();
        AttributeGroup attributeGroup = new AttributeGroup();
        attributeGroup.setName("123");
        attributes.add(attributeGroup);
        Model model = Mockito.mock(Model.class);

        Mockito.when(agServMock.findAll()).thenReturn(attributes);
        Mockito.when(aServMock.findByName(anyString())).thenReturn(null);
        Mockito.when(agServMock.findById(anyInt())).thenReturn(attributeGroup);
        Mockito.when(aServMock.saveAttribute(any(Attribute.class))).thenReturn(attribute);


        Assertions.assertEquals("admin/addAttribute",
                attributeAdminController.addAttribute(
                        attribute, 1, model));
    }
}