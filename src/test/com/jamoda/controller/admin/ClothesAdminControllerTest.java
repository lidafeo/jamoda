package com.jamoda.controller.admin;

import com.jamoda.model.*;
import com.jamoda.service.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.w3c.dom.Attr;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ClothesAdminControllerTest {

    CategoryService categoryService = Mockito.mock(CategoryService.class);
    AttributeService attributeService = Mockito.mock(AttributeService.class);
    AttributeGroupService attributeGroupService = Mockito.mock(AttributeGroupService.class);
    ClothesService сlothesService = Mockito.mock(ClothesService.class);
    ImageService imageService = Mockito.mock(ImageService.class);
    AttributeValueService attributeValueService = Mockito.mock(AttributeValueService.class);

    @Test
    void pageAddClothes() {
        ClothesAdminController clothesAdminController = new ClothesAdminController();
        clothesAdminController.setCategoryService(categoryService);
        clothesAdminController.setAttributeService(attributeService);
        clothesAdminController.setAttributeGroupService(attributeGroupService);
        Model model = mock(Model.class);

        Category category = new Category();
        category.setId(1);
        List<Category> catlist = new ArrayList<>();
        catlist.add(category);
        Attribute attribute = new Attribute();
        attribute.setName("qwerty");
        List<Attribute> attlist = new ArrayList<>();
        attlist.add(attribute);
        Set<Attribute> ags = new HashSet<>();
        ags.add(attribute);
        AttributeGroup attributeGroup = new AttributeGroup();
        attributeGroup.setAttributes(ags);
        List<AttributeGroup> attglist = new ArrayList<>();
        attglist.add(attributeGroup);

        when(categoryService.findAll()).thenReturn(catlist);
        when(attributeService.findAll()).thenReturn(attlist);
        when(attributeGroupService.findAll()).thenReturn(attglist);

        Assertions.assertEquals( "admin/addClothes",
                clothesAdminController.pageAddClothes(model));
    }

    @Test
    void addClothes() throws IOException {
        ClothesAdminController clothesAdminController = new ClothesAdminController();
        clothesAdminController.setCategoryService(categoryService);
        clothesAdminController.setAttributeService(attributeService);
        clothesAdminController.setAttributeGroupService(attributeGroupService);
        clothesAdminController.setClothesService(сlothesService);
        clothesAdminController.setImageService(imageService);

        Model model = mock(Model.class);

        Category category = new Category();
        category.setId(1);
        List<Category> catlist = new ArrayList<>();
        catlist.add(category);
        Attribute attribute = new Attribute();
        attribute.setName("qwerty");
        List<Attribute> attlist = new ArrayList<>();
        attlist.add(attribute);
        Set<Attribute> ags = new HashSet<>();
        ags.add(attribute);
        AttributeGroup attributeGroup = new AttributeGroup();
        attributeGroup.setAttributes(ags);
        List<AttributeGroup> attglist = new ArrayList<>();
        attglist.add(attributeGroup);
        Image image = new Image();
        image.setName("qwe");
        List<Image> ilist = new ArrayList<>();
        ilist.add(image);

        Clothes clothes = new Clothes();
        clothes.setCategory(category);//Id 1

        MultipartFile file = new MockMultipartFile("yes.jpg", new FileInputStream(new File("C:/users/homahel/Desktop/yes.jpg")));
        MultipartFile[] masm = {file};
        Long[] masl = {1l};
        String[] mass = {"123"};

        when(categoryService.findAll()).thenReturn(catlist);
        when(attributeService.findAll()).thenReturn(attlist);
        when(attributeGroupService.findAll()).thenReturn(attglist);
        when(сlothesService.findByArticle(clothes.getArticle())).thenReturn(clothes);

        Assertions.assertEquals( "admin/addClothes",
                clothesAdminController.addClothes(
                        clothes, 1L, 1L,
                        masl, masm, mass, model));

        when(сlothesService.findByArticle(clothes.getArticle())).thenReturn(null);
        when(categoryService.findAll()).thenReturn(null);
        Assertions.assertEquals( "admin/addClothes",
                clothesAdminController.addClothes(
                        clothes, 1L, 1L,
                        masl, masm, mass, model));
    }

    @Test
    void addClothes1() throws IOException {
        ClothesAdminController clothesAdminController = new ClothesAdminController();
        clothesAdminController.setCategoryService(categoryService);
        clothesAdminController.setAttributeService(attributeService);
        clothesAdminController.setAttributeGroupService(attributeGroupService);
        clothesAdminController.setClothesService(сlothesService);
        clothesAdminController.setImageService(imageService);
        clothesAdminController.setAttributeValueService(attributeValueService);

        Model model = mock(Model.class);

        Category category = new Category();
        category.setId(1);
        List<Category> catlist = new ArrayList<>();
        catlist.add(category);
        Attribute attribute = new Attribute();
        attribute.setName("qwerty");
        List<Attribute> attlist = new ArrayList<>();
        attlist.add(attribute);
        Set<Attribute> ags = new HashSet<>();
        ags.add(attribute);
        AttributeGroup attributeGroup = new AttributeGroup();
        attributeGroup.setAttributes(ags);
        List<AttributeGroup> attglist = new ArrayList<>();
        attglist.add(attributeGroup);
        Image image = new Image();
        image.setName("qwe");
        List<Image> ilist = new ArrayList<>();
        ilist.add(image);
        AttributeValue attributeValue = new AttributeValue();
        attributeValue.setAttribute(attribute);
        Clothes clothes = new Clothes();
        clothes.setCategory(category);//Id 1

        MultipartFile file = new MockMultipartFile("yes.jpg", new FileInputStream(new File("C:/users/homahel/Desktop/yes.jpg")));
        MultipartFile[] masm = {file};
        Long[] masl = {1l};
        String[] mass = {"123"};

        when(categoryService.findAll()).thenReturn(catlist);
        when(attributeService.findAll()).thenReturn(attlist);
        when(attributeGroupService.findAll()).thenReturn(attglist);
        when(сlothesService.findByArticle(clothes.getArticle())).thenReturn(null);
        when(imageService.addImages(masm, clothes.getArticle())).thenReturn(ilist);
        when(categoryService.findById(1L)).thenReturn(category);
        when(attributeValueService.saveAttributeValue(any(AttributeValue.class))).
                thenReturn(attributeValue);
        when(attributeGroupService.findById(1L)).thenReturn(attributeGroup);

        Assertions.assertEquals( "admin/addClothes",
                clothesAdminController.addClothes(
                        clothes, 1L, 1L,
                        masl, masm, mass, model));
    }

    @Test
    void pageAddAttributeValue() {
        ClothesAdminController clothesAdminController = new ClothesAdminController();
        clothesAdminController.setClothesService(сlothesService);
        clothesAdminController.setAttributeService(attributeService);

        Model model = mock(Model.class);

        Assertions.assertEquals( "admin/addAttributeValue",
                clothesAdminController.pageAddAttributeValue(model));
    }

    @Test
    void addAttributeValue() {
        ClothesAdminController clothesAdminController = new ClothesAdminController();
        clothesAdminController.setAttributeService(attributeService);
        clothesAdminController.setClothesService(сlothesService);
        clothesAdminController.setAttributeValueService(attributeValueService);

        Model model = mock(Model.class);

        Category category = new Category();
        category.setId(1);
        List<Category> catlist = new ArrayList<>();
        catlist.add(category);
        Attribute attribute = new Attribute();
        attribute.setName("qwerty");
        List<Attribute> attlist = new ArrayList<>();
        attlist.add(attribute);
        Set<Attribute> ags = new HashSet<>();
        ags.add(attribute);
        AttributeGroup attributeGroup = new AttributeGroup();
        attributeGroup.setAttributes(ags);
        List<AttributeGroup> attglist = new ArrayList<>();
        attglist.add(attributeGroup);
        AttributeValue attributeValue = new AttributeValue();
        attributeValue.setAttribute(attribute);

        Clothes clothes = new Clothes();
        clothes.setCategory(category);//Id 1
        List<Clothes> cllist = new ArrayList<>();
        cllist.add(clothes);

        Long[] masl = {1l};
        String[] mass = {"123"};

        when(сlothesService.findAll()).thenReturn(cllist);
        when(attributeService.findAll()).thenReturn(attlist);
        when(attributeValueService.findById(attributeValue.getId())).thenReturn(attributeValue);
        Assertions.assertEquals( "admin/addAttributeValue",
                clothesAdminController.addAttributeValue(
                        attributeValue, "123", 1L, model));

        when(attributeValueService.findById(attributeValue.getId())).thenReturn(null);
        when(сlothesService.findByArticle(any())).thenReturn(null);
        Assertions.assertEquals( "admin/addAttributeValue",
                clothesAdminController.addAttributeValue(
                        attributeValue, "123", 1L, model));

        when(attributeValueService.findById(attributeValue.getId())).thenReturn(null);
        when(сlothesService.findByArticle(any())).thenReturn(clothes);
        when(attributeService.findById(1L)).thenReturn(null);
        Mockito.when(attributeValueService.saveAttributeValue(attributeValue)).
                thenReturn(attributeValue);
        Assertions.assertEquals( "admin/addAttributeValue",
                clothesAdminController.addAttributeValue(
                        attributeValue, "123", 1L, model));

        when(attributeValueService.findById(attributeValue.getId())).thenReturn(null);
        when(сlothesService.findByArticle(any())).thenReturn(clothes);
        when(attributeService.findById(1L)).thenReturn(attribute);
        Mockito.when(attributeValueService.saveAttributeValue(attributeValue)).
                thenReturn(attributeValue);
        Assertions.assertEquals( "admin/addAttributeValue",
                clothesAdminController.addAttributeValue(
                        attributeValue, "123", 1L, model));

    }

    @Test
    void addAttributeGroup() {
        ClothesAdminController clothesAdminController = new ClothesAdminController();
        clothesAdminController.setAttributeService(attributeService);
        clothesAdminController.setClothesService(сlothesService);
        clothesAdminController.setAttributeValueService(attributeValueService);
        clothesAdminController.setAttributeGroupService(attributeGroupService);
        clothesAdminController.setClothesService(сlothesService);

        Model model = mock(Model.class);

        Category category = new Category();
        category.setId(1);
        List<Category> catlist = new ArrayList<>();
        catlist.add(category);
        Attribute attribute = new Attribute();
        attribute.setName("qwerty");
        List<Attribute> attlist = new ArrayList<>();
        attlist.add(attribute);
        Set<Attribute> ags = new HashSet<>();
        ags.add(attribute);
        AttributeGroup attributeGroup = new AttributeGroup();
        attributeGroup.setAttributes(ags);
        List<AttributeGroup> attglist = new ArrayList<>();
        attglist.add(attributeGroup);
        AttributeValue attributeValue = new AttributeValue();
        attributeValue.setAttribute(attribute);

        Clothes clothes = new Clothes();
        clothes.setCategory(category);//Id 1
        List<Clothes> cllist = new ArrayList<>();
        cllist.add(clothes);

        when(attributeGroupService.findAll()).thenReturn(attglist);
        when(сlothesService.findAll()).thenReturn(cllist);
        when(сlothesService.findByArticle("123")).thenReturn(null);
        Assertions.assertEquals( "admin/addAttributeGroup",
                clothesAdminController.addAttributeGroup(
                        1L, "123", model));

        when(attributeGroupService.findAll()).thenReturn(attglist);
        when(сlothesService.findAll()).thenReturn(cllist);
        when(сlothesService.findByArticle("123")).thenReturn(clothes);
        when(attributeGroupService.findById(1L)).thenReturn(null);
        Assertions.assertEquals( "admin/addAttributeGroup",
                clothesAdminController.addAttributeGroup(
                        1L, "123", model));

        when(attributeGroupService.findAll()).thenReturn(attglist);
        when(сlothesService.findAll()).thenReturn(cllist);
        when(сlothesService.findByArticle("123")).thenReturn(clothes);
        when(attributeGroupService.findById(1L)).thenReturn(attributeGroup);
        when(сlothesService.findByAttributeGroupsContainsAndArticle(
                attributeGroup, clothes.getArticle())).thenReturn(clothes);
        Assertions.assertEquals( "admin/addAttributeGroup",
                clothesAdminController.addAttributeGroup(
                        1L, "123", model));

        when(attributeGroupService.findAll()).thenReturn(attglist);
        when(сlothesService.findAll()).thenReturn(cllist);
        when(сlothesService.findByArticle("123")).thenReturn(clothes);
        when(attributeGroupService.findById(1L)).thenReturn(attributeGroup);
        when(сlothesService.findByAttributeGroupsContainsAndArticle(
                attributeGroup, clothes.getArticle())).thenReturn(null);
        Assertions.assertEquals( "admin/addAttributeGroup",
                clothesAdminController.addAttributeGroup(
                        1L, "123", model));

    }

    @Test
    void pageAddAttributeGroup() {
        ClothesAdminController clothesAdminController = new ClothesAdminController();
        clothesAdminController.setClothesService(сlothesService);
        clothesAdminController.setAttributeGroupService(attributeGroupService);
        Model model = mock(Model.class);

        Attribute attribute = new Attribute();
        attribute.setName("qwerty");
        Set<Attribute> ags = new HashSet<>();
        ags.add(attribute);
        AttributeGroup attributeGroup = new AttributeGroup();
        attributeGroup.setAttributes(ags);
        List<AttributeGroup> attglist = new ArrayList<>();
        attglist.add(attributeGroup);
        Clothes clothes = new Clothes();
        clothes.setName("qwerty");
        List<Clothes> clist = new ArrayList<>();
        clist.add(clothes);

        when(сlothesService.findAll()).thenReturn(clist);
        when(attributeGroupService.findAll()).thenReturn(attglist);

        Assertions.assertEquals( "admin/addAttributeGroup",
                clothesAdminController.pageAddAttributeGroup(model));
    }
}