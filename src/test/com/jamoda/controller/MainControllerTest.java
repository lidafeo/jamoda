package com.jamoda.controller;

import com.jamoda.model.Cart;
import com.jamoda.model.Category;
import com.jamoda.model.Clothes;
import com.jamoda.service.CartService;
import com.jamoda.service.CategoryService;
import com.jamoda.service.ClothesService;
import com.jamoda.service.MainService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MainControllerTest {

    @Test
    void main() {
        Clothes clothes = new Clothes();
        clothes.setName("труселя");
        List<Clothes> clothess = List.of(clothes);
        ClothesService сlothesServMock = mock(ClothesService.class);
        Mockito.when(сlothesServMock.getClothesPopular()).thenReturn(clothess);

        MainService mainServMock = mock(MainService.class);
        Model model = mock(Model.class);
        model.addAttribute("0", 0);
        HttpSession session = mock(HttpSession.class);
        session.setAttribute("1", 1);
        Mockito.when(mainServMock.getModel(model, session)).thenReturn(model);

        MainController mainController = new MainController();
        mainController.setMainService(mainServMock);
        mainController.setClothesService(сlothesServMock);

        verify(model, times(1)).addAttribute("0", 0);
        Assert.assertEquals("main wasn't returned","main", mainController.main(model, session));
    }

    @Test
    void about() {
        MainService mainServMock = mock(MainService.class);
        Model model = mock(Model.class);
        model.addAttribute("0", 0);
        HttpSession session = mock(HttpSession.class);
        session.setAttribute("1", 1);
        Mockito.when(mainServMock.getModel(model, session)).thenReturn(model);

        MainController mainController = new MainController();
        mainController.setMainService(mainServMock);

        verify(model, times(1)).addAttribute("0", 0);
        Assert.assertEquals("about wasn't returned","about", mainController.about(model, session));
    }
}