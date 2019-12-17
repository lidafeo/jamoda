package com.jamoda.controller;

import com.jamoda.repository.AttributeValueRepository;
import com.jamoda.repository.CategoryRepository;
import com.jamoda.repository.FilterRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.List;

import static com.jamoda.controller.MainController.getModel;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

//@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest

class CartControllerTest {
    @MockBean
    private Model model;
    @MockBean
    HttpSession session;
    @MockBean
    CategoryRepository categoryRepository;
    @MockBean
    FilterRepository filterRepository;
    @MockBean
    AttributeValueRepository attributeValueRepository;

    @Autowired
    private CartController cartController;

    @Test
    void cart() throws Exception{
//        CartController q = mock(CartController.class);
//        verify(q, times(1)).getCommonInfo(any(), any());
        verify(model, atLeastOnce()).addAttribute(any());
        Assert.assertEquals("cart wasn't returned","cart", cartController.cart(model, session));
    }

    @Test
    void getCommonInfo() {
        Model m = getModel(model, session, categoryRepository, filterRepository, attributeValueRepository);
        Assert.assertTrue( "Model object wasn't returned", m instanceof Model);
        Assert.assertNotNull( "Model object is null", m);
    }

    @Test
    void getCart() {
        ArrayList<String> articleList = new ArrayList<String>(1);
        articleList.add("1");
        ArrayList<String> clothes = new ArrayList<String>(5);
       // articleList.add();
        HttpSession session = mock(HttpSession.class);
        Mockito.when(session.getAttribute("PRODUCTS")).thenReturn(articleList);
        CartController cartController = new CartController();
        Mockito.when(session.getAttribute("PRODUCTS")).thenReturn(articleList);

        cartController.getCart(session);
        Assert.assertNotNull(session.getAttribute("PRODUCTS"));

//        ModelAndView registration = registrationController.registration();
//        Assert.assertEquals("/registration", registration.getViewName());
//        Assert.assertEquals(categories, registration.getModel().get("categories"));
//        Assert.assertNotNull(registration.getModel().get("user"));



//        verify(session, atLeastOnce()).getAttribute("PRODUCTS");
//        verify(session, atLeastOnce()).getAttribute("SIZES");

        //List<Integer> articleList =
        //clothesRepository.findByArticle(articleList.get(i));
    }
}