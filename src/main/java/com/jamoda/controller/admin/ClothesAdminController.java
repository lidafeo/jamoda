package com.jamoda.controller.admin;

import com.jamoda.model.*;
import com.jamoda.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@Controller
public class ClothesAdminController {

    private CategoryService categoryService;
    private AttributeService attributeService;
    private AttributeGroupService attributeGroupService;
    private AttributeValueService attributeValueService;
    private ImageService imageService;
    private ClothesService clothesService;

    @GetMapping("/admin/add_clothes")
    public String pageAddClothes(Model model) {
        model.addAttribute("category", categoryService.findAll());
        model.addAttribute("attributes", attributeService.findAll());
        model.addAttribute("groups", attributeGroupService.findAll());
        return "admin/addClothes";
    }

    @PostMapping("/admin/add_clothes")
    public String addClothes(Clothes clothes,
                             @RequestParam("category_id") Long category_id,
                             @RequestParam("group_id") Long group_id,
                             @RequestParam("attribute") Long[] attribute,
                             @RequestParam("files") MultipartFile[] files,
                             @RequestParam("value") String[] values,
                             Model model) throws IOException {

        model.addAttribute("groups", attributeGroupService.findAll());
        model.addAttribute("category", categoryService.findAll());
        model.addAttribute("attributes", attributeService.findAll());

        Clothes clothesFromDb = clothesService.findByArticle(clothes.getArticle());
        if(clothesFromDb != null) {
            model.addAttribute("error", "Такой товар уже существует!");
            return "admin/addClothes";
        }
        List<Image> images = imageService.addImages(files, clothes.getArticle());
        clothes.setImages(images);

        Category categoryFromDb = categoryService.findById(category_id);
        if(categoryFromDb == null) {
            model.addAttribute("error", "Такой категории не существует!");
            return "admin/addClothes";
        }

        AttributeGroup attributeGroupFromDb = attributeGroupService.findById(group_id);
        if(attributeGroupFromDb != null) {
            clothes.getAttributeGroups().add(attributeGroupFromDb);
        }

        clothes.setVisit(0);
        clothes.setDate_added(new Date());
        clothes.setCategory(categoryFromDb);
        clothesService.saveClothes(clothes);

        for(int i = 0; i < attribute.length; i++) {
            Attribute attributeFromDb = attributeService.findById(attribute[i]);
            AttributeValue attributeValue = new AttributeValue(values[i],true, clothes, attributeFromDb);
            attributeValueService.saveAttributeValue(attributeValue);
        }

        model.addAttribute("message", "Товар успешно добавлен");
        return "admin/addClothes";
    }

    @GetMapping("/admin/add_attribute_value")
    public String pageAddAttributeValue(Model model) {
        model.addAttribute("clothes", clothesService.findAll());
        model.addAttribute("attributes", attributeService.findAll());
        return "admin/addAttributeValue"; }

    @PostMapping("/admin/add_attribute_value")
    public String addAttributeValue(AttributeValue attributeValue,
                                    @RequestParam(name="product_article") String article,
                                    @RequestParam(name="attribute_id") long attribute_id,
                                    Model model) {

        model.addAttribute("clothes", clothesService.findAll());
        model.addAttribute("attributes", attributeService.findAll());

        AttributeValue attributeValueFromDb = attributeValueService.findById(attributeValue.getId());
        if(attributeValueFromDb != null) {
            model.addAttribute("error", "Такой атрибут уже добавлен!");
            return "admin/addAttributeValue"; }

        Clothes clothes = clothesService.findByArticle(article);
        if(clothes == null) {
            model.addAttribute("error", "Такого товара не существует!");
            return "admin/addAttributeValue"; }

        attributeValue.setClothes(clothes);

        Attribute attribute = attributeService.findById(attribute_id);
        if(attribute == null) {
            model.addAttribute("error", "Такого атрибута не существует!");
            return "admin/addAttributeValue";
        }
        attributeValue.setAttribute(attribute);
        attributeValueService.saveAttributeValue(attributeValue);
        model.addAttribute("message", "Атрибут успешно добавлен к товару");
        return "admin/addAttributeValue";
    }

    @GetMapping("/admin/add_attribute_group")
    public String pageAddAttributeGroup(Model model) {
        model.addAttribute("groups", attributeGroupService.findAll());
        model.addAttribute("products", clothesService.findAll());
        return "admin/addAttributeGroup";
    }

    @PostMapping("/admin/add_attribute_group")
    public String addAttributeGroup(@RequestParam(name="group_id") long groupId,
                                    @RequestParam(name="product_article") String article,
                                    Model model) {
        model.addAttribute("groups", attributeGroupService.findAll());
        model.addAttribute("products", clothesService.findAll());

        Clothes clothesFromDb = clothesService.findByArticle(article);
        if(clothesFromDb == null) {
            model.addAttribute("error", "Такого товара не существует!");
            return "admin/addAttributeGroup";
        }

        AttributeGroup attributeGroupFromDB = attributeGroupService.findById(groupId);
        if(attributeGroupFromDB == null) {
            model.addAttribute("error", "Такой группы атрибутов не существует!");
            return "admin/addAttributeGroup";
        }

        Clothes clothes = clothesService.findByAttributeGroupsContainsAndArticle(attributeGroupFromDB, clothesFromDb.getArticle());
        if(clothes != null) {
            model.addAttribute("error", "Такая группа атрибутов уже добавлена к этому товару!");
            return "admin/addAttributeGroup";
        }

        clothesFromDb.addAttributeGroup(attributeGroupFromDB);
        clothesService.saveClothes(clothesFromDb);
        model.addAttribute("message", "Группа атрибутов успешно добавлена к товару");
        return "admin/addAttributeGroup";
    }

    @Autowired
    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
    @Autowired
    public void setAttributeService(AttributeService attributeService) {
        this.attributeService = attributeService;
    }
    @Autowired
    public void setAttributeGroupService(AttributeGroupService attributeGroupService) {
        this.attributeGroupService = attributeGroupService;
    }
    @Autowired
    public void setAttributeValueService(AttributeValueService attributeValueService) {
        this.attributeValueService = attributeValueService;
    }
    @Autowired
    public void setImageService(ImageService imageService) {
        this.imageService = imageService;
    }
    @Autowired
    public void setClothesService(ClothesService clothesService) {
        this.clothesService = clothesService;
    }
}
