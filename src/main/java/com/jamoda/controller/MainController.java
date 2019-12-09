package com.jamoda.controller;

import com.jamoda.model.Clothes;
import com.jamoda.model.Image;
import com.jamoda.repository.CategoryRepository;
import com.jamoda.repository.ClothesRepository;
import com.jamoda.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.*;

@Controller
//@RequestMapping("/")
public class MainController {
    @Autowired
    private ClothesRepository clothesRepository;
    @Autowired
    private ImageRepository imageRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("/")
    public String main(Model model, HttpSession session) {
        Iterable<Clothes> clothes = clothesRepository.findAll();
        model.addAttribute("clothes", clothes);
        getCommonInfo(model, session);
        return "main";
    }

    @GetMapping("/about")
    public String about(Model model, HttpSession session) {
        getCommonInfo(model, session);
        return "about";
    }

    public Model getCommonInfo(Model model, HttpSession session) {
        return getModel(model, session, categoryRepository);
    }

    static Model getModel(Model model, HttpSession session, CategoryRepository categoryRepository) {
        @SuppressWarnings("unchecked")
        List<String> attributeList = (List<String>) session.getAttribute("PRODUCTS");
        if (attributeList == null) {
            attributeList = new ArrayList<>();
        }
        model.addAttribute("cartSession", attributeList);
        model.addAttribute("categories", categoryRepository.findAllByType("main"));
        return model;
    }

    static HttpSession destroySession(HttpSession session) {
        session.invalidate();
        return session;
    }
}
