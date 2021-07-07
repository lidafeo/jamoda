package com.jamoda.controller.admin;

import com.jamoda.model.Attribute;
import com.jamoda.model.AttributeGroup;
import com.jamoda.service.AttributeGroupService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.ui.Model;

import java.util.HashSet;
import java.util.Set;

import static org.mockito.Mockito.mock;

class AttributeGroupAdminControllerTest {

    AttributeGroupService attributeGroupService = Mockito.mock(AttributeGroupService.class);
    Model model = mock(Model.class);

    @Test
    void pageAddGroup(){
        AttributeGroupAdminController attributeGroupAdminController = new AttributeGroupAdminController();
        Assertions.assertEquals( "admin/addGroup",
                attributeGroupAdminController.pageAddGroup(model));
    }

    @Test
    void addGroup() throws Exception {
        AttributeGroupAdminController attributeGroupAdminController = new AttributeGroupAdminController();
        attributeGroupAdminController.setAttributeGroupService(attributeGroupService);
        Model model = mock(Model.class);
        model.addAttribute("0",0);

        Attribute attribute = new Attribute();
        attribute.setName("qwerty");
        Set<Attribute> ags = new HashSet<>();
        ags.add(attribute);
        AttributeGroup ag = new AttributeGroup("ag");
        ag.setAttributes(ags);

        Mockito.when(attributeGroupService.findByName(ag.getName())).thenReturn(ag);
        Assertions.assertEquals( "admin/addGroup",
                attributeGroupAdminController.addGroup(ag, model));
    }
    @Test

    void addGroupNull() {
        AttributeGroupAdminController attributeGroupAdminController = new AttributeGroupAdminController();
        attributeGroupAdminController.setAttributeGroupService(attributeGroupService);
        Model model = mock(Model.class);
        model.addAttribute("0",0);

        Attribute attribute = new Attribute();
        attribute.setName("qwerty");
        Set<Attribute> ags = new HashSet<>();
        ags.add(attribute);
        AttributeGroup ag = new AttributeGroup("ag");
        ag.setAttributes(ags);

        Mockito.when(attributeGroupService.findByName(ag.getName())).thenReturn(null);
        Mockito.when(attributeGroupService.saveAttributeGroup(ag)).thenReturn(ag);

        Assertions.assertEquals( "admin/addGroup",
                attributeGroupAdminController.addGroup(ag, model));
    }
}