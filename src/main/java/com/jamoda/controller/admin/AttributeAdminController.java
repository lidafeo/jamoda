package com.jamoda.controller.admin;

import com.jamoda.model.Attribute;
import com.jamoda.model.AttributeGroup;
import com.jamoda.service.AttributeGroupService;
import com.jamoda.service.AttributeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class AttributeAdminController {

    private AttributeGroupService attributeGroupService;
    private AttributeService attributeService;

    @GetMapping("/admin/add_attribute")
    public String pageAddAttribute(Model model) {
        model.addAttribute("groups", attributeGroupService.findAll());
        return "admin/addAttribute";
    }

    @PostMapping("/admin/add_attribute")
    public String addAttribute(Attribute attribute,
                               @RequestParam(name="groupId") long groupId,
                               Model model) {
        model.addAttribute("groups", attributeGroupService.findAll());

        Attribute attributeFromDb = attributeService.findByName(attribute.getName());
        if(attributeFromDb != null) {
            model.addAttribute("error", "Такой атрибут уже существует!");
            return "admin/addAttribute";
        }

        AttributeGroup attributeGroup = attributeGroupService.findById(groupId);
        if(attributeGroup != null) {
            attribute.setGroup(attributeGroup);
        }

        attributeService.saveAttribute(attribute);
        model.addAttribute("message", "success");
        return "admin/addAttribute";
    }

    @Autowired
    public void setAttributeGroupService(AttributeGroupService attributeGroupService) {
        this.attributeGroupService = attributeGroupService;
    }
    @Autowired
    public void setAttributeService(AttributeService attributeService) {
        this.attributeService = attributeService;
    }
}
