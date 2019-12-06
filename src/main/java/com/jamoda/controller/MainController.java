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

import java.io.File;
import java.util.Map;
import java.util.UUID;

@Controller
//@RequestMapping("/")
public class MainController {
    @Autowired
    private ClothesRepository clothesRepository;
    @Autowired
    private ImageRepository imageRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    /*
    @GetMapping("/greeting")
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name,
                           Map<String, Object> model) {
        model.put("name", name);
        return "greeting";
    }
    */

    @GetMapping("/")
    public String main(Model model) {
        Iterable<Clothes> clothes = clothesRepository.findAll();
        model.addAttribute("clothes", clothes);
        model.addAttribute("categories", categoryRepository.findAllByType("main"));
        return "main";
    }

    @GetMapping("/about")
    public String about(Model model) {
        model.addAttribute("categories", categoryRepository.findAllByType("main"));
        return "about";
    }
}
