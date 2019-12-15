package com.jamoda.controller;

import com.jamoda.model.Clothes;
import com.jamoda.repository.AttributeValueRepository;
import com.jamoda.repository.CategoryRepository;
import com.jamoda.repository.ClothesRepository;
import com.jamoda.repository.FilterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

import static com.jamoda.controller.CartController.getCart;
import static com.jamoda.controller.MainController.getModel;

@Controller
public class OrderController {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private AttributeValueRepository attributeValueRepository;
    @Autowired
    private FilterRepository filterRepository;
    @Autowired
    private ClothesRepository clothesRepository;

    @GetMapping("/order")
    public String order(Model model, HttpSession session) {
        getCommonInfo(model, session);
        model.addAttribute("categories", categoryRepository.findAllByType("main"));
        model.addAttribute("cart", getCart(session, clothesRepository));
        return "order";
    }

    public Model getCommonInfo(Model model, HttpSession session) {
        return getModel(model, session, categoryRepository, filterRepository, attributeValueRepository);
    }
}
