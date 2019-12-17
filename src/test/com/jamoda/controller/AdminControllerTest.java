package com.jamoda.controller;

import com.jamoda.model.*;
import com.jamoda.repository.AttributeRepository;
import com.jamoda.repository.CategoryRepository;
import com.jamoda.repository.ClothesRepository;
import com.jamoda.repository.UserRepository;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.junit.Assert;
import org.junit.experimental.categories.Categories;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.validation.constraints.Null;
import java.io.*;
import java.nio.file.Files;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.apache.tomcat.util.http.fileupload.FileUtils.deleteDirectory;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

//@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest

public class AdminControllerTest {

    @MockBean
    private Model model;
    @MockBean
    Clothes clothes;
    @MockBean
    Categories categories;
    @MockBean
    private User user;
    @MockBean
    private UserRepository userRepository;
    @MockBean
    private CategoryRepository categoryRepository;
    @MockBean
    private ClothesRepository clothesRepository;
    @MockBean
    private AttributeRepository attributeRepository;
//    @MockBean
//    MultipartFile file;


    @Autowired
    private AdminController AdminController;

    @Test
    void admin() {
        Assert.assertEquals("admin wasn't returned", "admin", AdminController.admin(model));
    }

    @Test
    void pageAddUser() throws Exception{
        Assert.assertEquals("addUser wasn't returned","addUser", AdminController.pageAddUser(model));
    }

    @Test
    void addUser() throws Exception{
        Assert.assertEquals("addUser wasn't returned","addUser", AdminController.addUser(user, model));
        Mockito.when(userRepository.findByLogin(user.getLogin())).thenReturn(user);
        if (user == null) {
            String ex = AdminController.addUser(user, model);
            verify(user, atLeastOnce()).setActive(true);
            verify(user, atLeastOnce()).setRoles(Collections.singleton(Role.ADMIN));
            verify(userRepository, atLeastOnce()).save(user);
        }
        else {
            Assert.assertNotNull(user);
        }
    }

    @Test
    void pageAddFile() throws Exception {
        Assert.assertEquals("addFile wasn't returned","addFile", AdminController.pageAddFile(model));
        Mockito.when(model.addAttribute("products", clothesRepository.findAll())).thenReturn(model);
        Assert.assertNotNull(model);
    }

    @Test
    void addFile() throws IOException, NullPointerException {
        String str = new String("random");
//надо создать empty файл, чтобы затестить работу метода с ним ???
        MultipartFile ff = (MultipartFile)Files.createTempDirectory(null).toFile();
        Assert.assertEquals("addFile wasn't returned with not-null file argument", "addFile", AdminController.addFile(str, ff, model));
        //тест не проходит
        Assert.assertEquals("addFile wasn't returned with null file argument",
                "addFile", AdminController.addFile(str, null, model));
        //тест проходит
        deleteDirectory((File)ff);
    }

    @Test
    void pageAddClothes() {
        List<Category> exp = categoryRepository.findAll();
        Assert.assertNotNull(exp);
        List<Attribute> expp = attributeRepository.findAll();
        Assert.assertNotNull(expp);
        Mockito.when(clothesRepository.findByArticle(clothes.getArticle())).thenReturn(clothes);
        Clothes clothes = Mockito.mock(Clothes.class);
        AttributeRepository attributeRepository = Mockito.mock(AttributeRepository.class);
        if (clothes == null) {
            verify(categoryRepository, atLeastOnce()).findById(anyInt());
        }
        else {
            verify(clothes, atLeastOnce()).setVisit(0);
            verify(clothes, atLeastOnce()).setDate_added(new Date());
            verify(clothesRepository, atLeastOnce()).save(clothes);
            Assert.assertNotNull(clothes);
        }

        verify(categoryRepository, atLeastOnce()).findAll();
        verify(attributeRepository, atLeastOnce()).findAll();
    }

    @Test
    void addClothes() {
    }

    @Test
    void pageAddCategory() {
    }

    @Test
    void addCategory() {
    }

    @Test
    void pageAddAttribute() {
    }

    @Test
    void addAttribute() {
    }

    @Test
    void pageAddGroup() {
    }

    @Test
    void addGroup() {
    }

    @Test
    void pageAddAttributeValue() {
    }

    @Test
    void addAttributeValue() {
    }

    @Test
    void pageAddAttributeGroup() {
    }

    @Test
    void addAttributeGroup() {
    }
}