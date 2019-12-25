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

    @Test
    void pageAddClothes() {
        ClothesAdminController clothesAdminController = new ClothesAdminController();
        CategoryService categoryService = Mockito.mock(CategoryService.class);
        clothesAdminController.setCategoryService(categoryService);
        AttributeService attributeService = Mockito.mock(AttributeService.class);
        clothesAdminController.setAttributeService(attributeService);
        AttributeGroupService attributeGroupService = Mockito.mock(AttributeGroupService.class);
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

        Assertions.assertNotNull(clothesAdminController.pageAddClothes(model));
        Assertions.assertEquals( "admin/addClothes",
                clothesAdminController.pageAddClothes(model));
    }

    @Test
    void addClothes() throws IOException {
        ClothesAdminController clothesAdminController = new ClothesAdminController();
        CategoryService categoryService = Mockito.mock(CategoryService.class);
        clothesAdminController.setCategoryService(categoryService);
        AttributeService attributeService = Mockito.mock(AttributeService.class);
        clothesAdminController.setAttributeService(attributeService);
        AttributeGroupService attributeGroupService = Mockito.mock(AttributeGroupService.class);
        clothesAdminController.setAttributeGroupService(attributeGroupService);
        ClothesService сlothesService = Mockito.mock(ClothesService.class);
        clothesAdminController.setClothesService(сlothesService);
        ImageService imageService = Mockito.mock(ImageService.class);
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

        Assertions.assertNotNull(clothesAdminController.addClothes(
                clothes, 1L, 1L,
                masl, masm, mass, model));
        Assertions.assertEquals( "admin/addClothes",
                clothesAdminController.addClothes(
                        clothes, 1L, 1L,
                        masl, masm, mass, model));
    }

    @Test
    void addClothes1() throws IOException {
        ClothesAdminController clothesAdminController = new ClothesAdminController();
        CategoryService categoryService = Mockito.mock(CategoryService.class);
        clothesAdminController.setCategoryService(categoryService);
        AttributeService attributeService = Mockito.mock(AttributeService.class);
        clothesAdminController.setAttributeService(attributeService);
        AttributeGroupService attributeGroupService = Mockito.mock(AttributeGroupService.class);
        clothesAdminController.setAttributeGroupService(attributeGroupService);
        ClothesService сlothesService = Mockito.mock(ClothesService.class);
        clothesAdminController.setClothesService(сlothesService);
        ImageService imageService = Mockito.mock(ImageService.class);
        clothesAdminController.setImageService(imageService);
        AttributeValueService attributeValueService = Mockito.mock(AttributeValueService.class);
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

        Assertions.assertNotNull(clothesAdminController.addClothes(
                clothes, 1L, 1L,
                masl, masm, mass, model));
        Assertions.assertEquals( "admin/addClothes",
                clothesAdminController.addClothes(
                        clothes, 1L, 1L,
                        masl, masm, mass, model));
    }

    @Test
    void pageAddAttributeValue() {
        ClothesAdminController clothesAdminController = new ClothesAdminController();
        ClothesService сlothesService = Mockito.mock(ClothesService.class);
        clothesAdminController.setClothesService(сlothesService);
        AttributeService attributeService = Mockito.mock(AttributeService.class);
        clothesAdminController.setAttributeService(attributeService);

        Model model = mock(Model.class);

        Assertions.assertNotNull(clothesAdminController.pageAddAttributeValue(model));
        Assertions.assertEquals( "admin/addAttributeValue",
                clothesAdminController.pageAddAttributeValue(model));
    }

    @Test
    void addAttributeValue() {
        ClothesAdminController clothesAdminController = new ClothesAdminController();
        AttributeService attributeService = Mockito.mock(AttributeService.class);
        clothesAdminController.setAttributeService(attributeService);
        ClothesService сlothesService = Mockito.mock(ClothesService.class);
        clothesAdminController.setClothesService(сlothesService);
        AttributeValueService attributeValueService = Mockito.mock(AttributeValueService.class);
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
        when(сlothesService.findByArticle(any())).thenReturn(clothes);
        when(attributeService.findById(anyInt())).thenReturn(attribute);
        Mockito.when(attributeValueService.saveAttributeValue(attributeValue)).
                thenReturn(attributeValue);

        Assertions.assertNotNull(clothesAdminController.addAttributeValue(
                attributeValue, "123", 1L, model));
        Assertions.assertEquals( "admin/addAttributeValue",
                clothesAdminController.addAttributeValue(
                        attributeValue, "123", 1L, model));
    }

    @Test
    void addAttributeValue1() {
//        ClothesAdminController clothesAdminController = new ClothesAdminController();
//        AttributeService attributeService = Mockito.mock(AttributeService.class);
//        clothesAdminController.setAttributeService(attributeService);
//        ClothesService сlothesService = Mockito.mock(ClothesService.class);
//        clothesAdminController.setClothesService(сlothesService);
//        AttributeValueService attributeValueService = Mockito.mock(AttributeValueService.class);
//        clothesAdminController.setAttributeValueService(attributeValueService);
//
//        Model model = mock(Model.class);
//
//        Category category = new Category();
//        category.setId(1);
//        List<Category> catlist = new ArrayList<>();
//        catlist.add(category);
//        Attribute attribute = new Attribute();
//        attribute.setName("qwerty");
//        List<Attribute> attlist = new ArrayList<>();
//        attlist.add(attribute);
//        Set<Attribute> ags = new HashSet<>();
//        ags.add(attribute);
//        AttributeGroup attributeGroup = new AttributeGroup();
//        attributeGroup.setAttributes(ags);
//        List<AttributeGroup> attglist = new ArrayList<>();
//        attglist.add(attributeGroup);
//        AttributeValue attributeValue = new AttributeValue();
//        attributeValue.setAttribute(attribute);
//
//        Clothes clothes = new Clothes();
//        clothes.setCategory(category);//Id 1
//        List<Clothes> cllist = new ArrayList<>();
//        cllist.add(clothes);
//
//        Long[] masl = {1l};
//        String[] mass = {"123"};
//
//        Mockito.when(сlothesService.findAll()).thenReturn(cllist);
//        Mockito.when(attributeService.findAll()).thenReturn(attlist);
//        Mockito.when(attributeValueService.findById(attributeValue.getId())).
//                thenReturn(null);
//        Mockito.when(сlothesService.findByArticle(any())).thenReturn(null);
//        Mockito.when(attributeService.findById(1L)).thenReturn(null);
//
//
//
//        Assertions.assertNotNull(clothesAdminController.addAttributeValue(
//                attributeValue, "123", 1L, model));
//        Assertions.assertEquals( "admin/addAttributeValue",
//                clothesAdminController.addAttributeValue(
//                        attributeValue, "123", 1L, model));
    }

    @Test
    void pageAddAttributeGroup() {
        ClothesAdminController clothesAdminController = new ClothesAdminController();
        ClothesService clothesService = Mockito.mock(ClothesService.class);
        clothesAdminController.setClothesService(clothesService);
        AttributeGroupService attributeGroupService = Mockito.mock(AttributeGroupService.class);
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

        when(clothesService.findAll()).thenReturn(clist);
        when(attributeGroupService.findAll()).thenReturn(attglist);

        Assertions.assertNotNull(clothesAdminController.pageAddAttributeGroup(model));
        Assertions.assertEquals( "admin/addAttributeGroup",
                clothesAdminController.pageAddAttributeGroup(model));
    }

    @Test
    void addAttributeGroup() {
        ClothesAdminController clothesAdminController = new ClothesAdminController();
        AttributeGroupService attributeGroupService = Mockito.mock(AttributeGroupService.class);
        clothesAdminController.setAttributeGroupService(attributeGroupService);
        AttributeService attributeService = Mockito.mock(AttributeService.class);
        clothesAdminController.setAttributeService(attributeService);
        ClothesService сlothesService = Mockito.mock(ClothesService.class);
        clothesAdminController.setClothesService(сlothesService);
        AttributeValueService attributeValueService = Mockito.mock(AttributeValueService.class);
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

        when(attributeGroupService.findAll()).thenReturn(attglist);
        when(сlothesService.findAll()).thenReturn(cllist);
        when(сlothesService.findByArticle("123")).thenReturn(clothes);
        when(attributeGroupService.findById(1L)).thenReturn(attributeGroup);
        when(сlothesService.findByAttributeGroupsContainsAndArticle(
                attributeGroup,clothes.getArticle())).
                thenReturn(clothes);
        when(сlothesService.saveClothes(clothes)).thenReturn(clothes);

        Assertions.assertNotNull(clothesAdminController.addAttributeGroup(
                 1L, "123", model));
        Assertions.assertEquals( "admin/addAttributeGroup",
                clothesAdminController.addAttributeGroup(
                        1L, "123", model));
    }

    @Test
    void addAttributeGroup1() {
        ClothesAdminController clothesAdminController = new ClothesAdminController();
        AttributeGroupService attributeGroupService = Mockito.mock(AttributeGroupService.class);
        clothesAdminController.setAttributeGroupService(attributeGroupService);
        AttributeService attributeService = Mockito.mock(AttributeService.class);
        clothesAdminController.setAttributeService(attributeService);
        ClothesService сlothesService = Mockito.mock(ClothesService.class);
        clothesAdminController.setClothesService(сlothesService);
        AttributeValueService attributeValueService = Mockito.mock(AttributeValueService.class);
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


        when(attributeGroupService.findAll()).thenReturn(attglist);
        when(сlothesService.findAll()).thenReturn(cllist);
        when(сlothesService.findByArticle("123")).thenReturn(null);
        when(attributeGroupService.findById(1L)).thenReturn(attributeGroup);
        when(сlothesService.findByAttributeGroupsContainsAndArticle(
                attributeGroup,clothes.getArticle())).
                thenReturn(clothes);
        when(сlothesService.saveClothes(clothes)).thenReturn(clothes);

        Assertions.assertNotNull(clothesAdminController.addAttributeGroup(
                1L, "123", model));
        Assertions.assertEquals( "admin/addAttributeGroup",
                clothesAdminController.addAttributeGroup(
                        1L, "123", model));
    }
    @Test
    void addAttributeGroup2() {
        ClothesAdminController clothesAdminController = new ClothesAdminController();
        AttributeGroupService attributeGroupService = Mockito.mock(AttributeGroupService.class);
        clothesAdminController.setAttributeGroupService(attributeGroupService);
        AttributeService attributeService = Mockito.mock(AttributeService.class);
        clothesAdminController.setAttributeService(attributeService);
        ClothesService сlothesService = Mockito.mock(ClothesService.class);
        clothesAdminController.setClothesService(сlothesService);
        AttributeValueService attributeValueService = Mockito.mock(AttributeValueService.class);
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

        when(attributeGroupService.findAll()).thenReturn(attglist);
        when(сlothesService.findAll()).thenReturn(cllist);
        when(сlothesService.findByArticle("123")).thenReturn(clothes);
        when(attributeGroupService.findById(1L)).thenReturn(null);
        when(сlothesService.findByAttributeGroupsContainsAndArticle(
                attributeGroup,clothes.getArticle())).
                thenReturn(clothes);
        when(сlothesService.saveClothes(clothes)).thenReturn(clothes);

        Assertions.assertNotNull(clothesAdminController.addAttributeGroup(
                1L, "123", model));
        Assertions.assertEquals( "admin/addAttributeGroup",
                clothesAdminController.addAttributeGroup(
                        1L, "123", model));
    }
}