package com.jamoda.controller.admin;

import com.jamoda.model.Attribute;
import com.jamoda.model.AttributeGroup;
import com.jamoda.service.AttributeGroupService;
import com.jamoda.service.AttributeService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

class AttributeAdminControllerTest {

    private MockMvc mockMvc;

    AttributeGroupService agServMock = Mockito.mock(AttributeGroupService.class);
    AttributeService aServMock = Mockito.mock(AttributeService.class);
    Model model = Mockito.mock(Model.class);

    @Test
    void pageAddAttribute() throws Exception{
        AttributeAdminController attributeAdminController = new AttributeAdminController();
        attributeAdminController.setAttributeGroupService(agServMock);
        mockMvc = MockMvcBuilders.standaloneSetup(attributeAdminController).
                build();

        Model model = Mockito.mock(Model.class);
        AttributeGroup a = new AttributeGroup();
        a.setName("qwerty");
        List<AttributeGroup> al = new ArrayList<>();

        Mockito.when(agServMock.findAll()).thenReturn(al);
        this.mockMvc.perform(get("/admin/add_attribute"))
                .andExpect(model().attribute("groups", al));

        Assertions.assertEquals("admin/addAttribute",
                attributeAdminController.pageAddAttribute(model));
    }

    @Test
    void addAttribute() throws Exception {
        AttributeAdminController attributeAdminController = new AttributeAdminController();
        attributeAdminController.setAttributeGroupService(agServMock);
        attributeAdminController.setAttributeService(aServMock);
        Attribute attribute = new Attribute();
        attribute.setName("qwerty");
        List<AttributeGroup> attributes = new ArrayList<>();
        AttributeGroup attributeGroup = new AttributeGroup();
        attributeGroup.setName("123");
        attributes.add(attributeGroup);

        Mockito.when(agServMock.findAll()).thenReturn(attributes);
        Mockito.when(aServMock.findByName(anyString())).thenReturn(attribute);
        Mockito.when(agServMock.findById(anyInt())).thenReturn(attributeGroup);
        Mockito.when(aServMock.saveAttribute(any(Attribute.class))).thenReturn(attribute);

        Assertions.assertEquals("admin/addAttribute",
                attributeAdminController.addAttribute(
                        attribute, 1, model));
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

        Mockito.when(agServMock.findAll()).thenReturn(attributes);
        Mockito.when(aServMock.findByName(anyString())).thenReturn(null);
        Mockito.when(agServMock.findById(anyInt())).thenReturn(attributeGroup);
        Mockito.when(aServMock.saveAttribute(any(Attribute.class))).thenReturn(attribute);

        Assertions.assertEquals("admin/addAttribute",
                attributeAdminController.addAttribute(
                        attribute, 1, model));
    }
}