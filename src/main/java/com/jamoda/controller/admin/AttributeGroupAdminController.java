package com.jamoda.controller.admin;

import com.jamoda.model.AttributeGroup;
import com.jamoda.service.AttributeGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AttributeGroupAdminController {

    private AttributeGroupService attributeGroupService;

    @GetMapping("/admin/add_group")
    public String pageAddGroup(Model model) {
        return "admin/addGroup";
    }

    @PostMapping("/admin/add_group")
    public String addGroup(AttributeGroup attributeGroup, Model model) {
        AttributeGroup attributeGroupFromDb = attributeGroupService.findByName(attributeGroup.getName());
        if(attributeGroupFromDb != null) {
            model.addAttribute("error", "Такая группа атрибутов уже существует!");
            return "admin/addGroup";
        }
        attributeGroupService.saveAttributeGroup(attributeGroup);
        model.addAttribute("message", "Группа атрибутов успешно добавлена");
        return "admin/addGroup";
    }

    @Autowired
    public void setAttributeGroupService(AttributeGroupService attributeGroupService) {
        this.attributeGroupService = attributeGroupService;
    }
}
