package com.jamoda.controller;

import com.jamoda.model.*;
import com.jamoda.repository.*;
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
    @Autowired
    private FilterRepository filterRepository;
    @Autowired
    private AttributeValueRepository attributeValueRepository;

    @GetMapping("/")
    public  String main(Model model, HttpSession session) {
        Iterable<Clothes> clothes = clothesRepository.findAllByOrderByVisitDesc();
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
        return getModel(model, session, categoryRepository, filterRepository, attributeValueRepository);
    }

    static Model getModel(Model model,
                          HttpSession session,
                          CategoryRepository categoryRepository,
                          FilterRepository filterRepository,
                          AttributeValueRepository attributeValueRepository) {
        @SuppressWarnings("unchecked")
        List<String> attributeList = (List<String>) session.getAttribute("PRODUCTS");
        if (attributeList == null) {
            attributeList = new ArrayList<>();
        }
        List<Filter> filters = filterRepository.findAllByActive(true);
        for(int i = 0; i < filters.size(); i++) {
            if(!filters.get(i).isSearchAll()) {
                continue;
            }
            Attribute attribute = filters.get(i).getAttribute();
            List<String> values = attributeValueRepository.findDistinctValueByAttribute(attribute);
            filters.get(i).setValues(values);
        }
        model.addAttribute("cartSession", attributeList);
        model.addAttribute("filters", filters);
        model.addAttribute("categories", categoryRepository.findAllByType("main"));
        return model;
    }

    static HttpSession destroySession(HttpSession session) {
        session.invalidate();
        return session;
    }
}
