package com.jamoda.controller;

import com.jamoda.model.Category;
import com.jamoda.model.Clothes;
import com.jamoda.model.Filter;
import com.jamoda.model.User;
import com.jamoda.service.CategoryService;
import com.jamoda.service.ClothesService;
import com.jamoda.service.FilterService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.web.servlet.MockMvc;import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

class MainControllerTest {

    ClothesService clothesService = Mockito.mock(ClothesService.class);
    CategoryService categoryService = Mockito.mock(CategoryService.class);
    FilterService filterService = Mockito.mock(FilterService.class);
    Model model = Mockito.mock(Model.class);

    @Test
    public void main() throws Exception {
        MainController mainController = new MainController();
        mainController.setCategoryService(categoryService);
        mainController.setClothesService(clothesService);
        mainController.setFilterService(filterService);

        Clothes clothes = new Clothes();
        clothes.setName("труселя");
        List<Clothes> list = List.of(clothes);
        Page<Clothes> page = new PageImpl<>(list, PageRequest.of(1, 12),
                1);
        User user = new User();
        user.setLogin("qwerty");
        model.addAttribute("string", 123);
        Pageable pageable = PageRequest.of(0, 8);
        Filter filter = new Filter();
        filter.setActive(true);
        List<Filter> flist = new ArrayList<>();
        flist.add(filter);
        Category category = new Category();
        category.setNameRus("name");
        List<Category> catlist = new ArrayList<>();
        catlist.add(category);

        Mockito.when(clothesService.getClothesPopular(pageable)).thenReturn(page);
        Mockito.when(clothesService.getMaxPriceAllClothes()).thenReturn(1);
        Mockito.when(filterService.getActiveFilter()).thenReturn(flist);
        Assert.assertEquals("main", mainController.main(model, pageable, user));
    }

    @Test
    public void about() throws Exception{
        MainController mainController = new MainController();
        model.addAttribute("0", 0);
        mainController.setCategoryService(categoryService);
        mainController.setFilterService(filterService);

        Clothes clothes1 = new Clothes();
        clothes1.setName("труселя");
        clothes1.setVisit(1);
        User user = new User();
        user.setLogin("qwerty");
        Pageable pageable = PageRequest.of(0, 8);
        Category category = new Category();
        category.setNameRus("name");
        List<Category> catlist = new ArrayList<>();
        catlist.add(category);

        Mockito.when(categoryService.findMainCategory()).thenReturn(catlist);

        Assert.assertEquals("about", mainController.about(model, user));

    }

    @Test
    public void register() {
        MainController mainController = new MainController();
        mainController.setCategoryService(categoryService);
        Category category = new Category();
        category.setNameRus("name");
        List<Category> catlist = new ArrayList<>();
        catlist.add(category);
        Mockito.when(categoryService.findMainCategory()).thenReturn(catlist);
        Assert.assertEquals("registration",
                mainController.register(model));

    }
}