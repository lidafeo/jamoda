package com.jamoda.controller;

import com.jamoda.controller.MainController;
import com.jamoda.model.Cart;
import com.jamoda.model.Category;
import com.jamoda.model.Clothes;
import com.jamoda.service.CategoryService;
import com.jamoda.service.ClothesService;
import com.jamoda.service.MainService;
import com.jamoda.service.OrderService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@RunWith(SpringRunner.class)
//@SpringBootTest
//@AutoConfigureMockMvc
class MainControllerTest {

    MainService mainService = mock(MainService.class);
    ClothesService clothesService = mock(ClothesService.class);

    @Test
    public void main() throws Exception{
        MainController mainController = new MainController();
        Clothes clothes = new Clothes();
        clothes.setName("труселя");
        List<Clothes> list = List.of(clothes);
        Page<Clothes> page = new PageImpl<>(list, PageRequest.of(1, 12),
                1);

        Model model = mock(Model.class);
        model.addAttribute("string", 123);

        Pageable pageable = PageRequest.of(0, 8);

        Mockito.when(clothesService.getClothesPopular(any(Pageable.class))).thenReturn(page);
        Mockito.when(mainService.getSessionModel(any(Model.class))).thenReturn(model);

        mainController.setMainService(mainService);
        mainController.setClothesService(clothesService);

//        this.mockMvc.perform(MockMvcRequestBuilders.get("/").
//                requestAttr("pageable", pageable)).
//                andDo(MockMvcResultHandlers.print()).
//                andExpect(status().isOk()).
//                andExpect(MockMvcResultMatchers.model().attribute("page", page)).
//                andExpect(MockMvcResultMatchers.model().attribute("url","/"));
        Assert.assertEquals("main", mainController.main(model, pageable));
    }

    @Test
    public void about() {
        MainController mainController = new MainController();
        Model model = mock(Model.class);
        model.addAttribute("0", 0);

        Mockito.when(mainService.getSessionModel(any(Model.class))).thenReturn(model);

        Clothes clothes1 = new Clothes();
        clothes1.setName("труселя");
        clothes1.setVisit(1);
// Clothes clothes2 = new Clothes();
// clothes2.setName("труселя");
// clothes2.setVisit(10);
        List<Clothes> clothess = List.of(clothes1);

        mainController.setMainService(mainService);

        verify(model, times(1)).addAttribute("0", 0);
        Assert.assertEquals("about", mainController.about(model));
    }
}