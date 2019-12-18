package com.jamoda.controller;

import com.jamoda.model.*;
import com.jamoda.repository.*;
import com.jamoda.service.ClothesService;
import com.jamoda.service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Entity;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.*;

@Controller
//@RequestMapping("/")
public class MainController {

    @Autowired
    private MainService mainService;
    @Autowired
    private ClothesService clothesService;

    @GetMapping("/")
    public String main(Model model, HttpSession session) {
        model.addAttribute("clothes", clothesService.getClothesPopular());
        mainService.getModel(model, session);
        return "main";
    }

    @GetMapping("/about")
    public String about(Model model, HttpSession session) {
        mainService.getModel(model, session);
        return "about";
    }
}
