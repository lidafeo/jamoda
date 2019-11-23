package com.jamoda.controller;

import com.jamoda.model.Category;
import com.jamoda.model.Clothes;
import com.jamoda.model.Role;
import com.jamoda.model.User;
import com.jamoda.repository.CategoryRepository;
import com.jamoda.repository.ClothesRepository;
import com.jamoda.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;

@Controller
//@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ClothesRepository clothesRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @Value("${upload.path}")
    private String uploadPath;

    //admin
    @GetMapping("/admin")
    public String admin(Model model) {
        return "admin";
    }

    //admin/add_user
    @GetMapping("/admin/add_user")
    public String pageAddUser(Model model) {
        return "addUser";
    }

    @PostMapping("/admin/add_user")
    public String addUser(User user, Model model) {
        User userFromDb = userRepository.findByLogin(user.getLogin());
        if(userFromDb != null) {
            model.addAttribute("message", "User exists!");
            return "addUser";
        }
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.ADMIN));
        userRepository.save(user);
        return "redirect:/admin";
    }

    //admin/add_file
    @GetMapping("/admin/add_file")
    public String pageAddFile(Model model) {
        return "addFile";
    }

    @PostMapping("/admin/add_file")
    public String addFile(@RequestParam("article") String article, @RequestParam("file") MultipartFile file, Model model) throws IOException {
        if(file != null && !file.getOriginalFilename().isEmpty()) {
            File uploadDir = new File(uploadPath);
            if(!uploadDir.exists()) {
                uploadDir.mkdir();
            }
            //String uuidFile = UUID.randomUUID().toString();
            String resultFilename = article + "_" + file.getOriginalFilename();
            Clothes clothes = clothesRepository.findByArticle(article);
            clothes.setFilename(resultFilename);
            file.transferTo(new File(uploadPath + "/" + resultFilename));
            clothesRepository.save(clothes);
        }
        return "redirect:/admin";
    }

    //admin/add_clothes
    @GetMapping("/admin/add_clothes")
    public String pageAddClothes(Model model) {
        Map<String, Object> attribute = new HashMap<>();
        attribute.put("gender", Clothes.getGenderForSelect());
        attribute.put("season", Clothes.getSeasonForSelect());
        attribute.put("brand", clothesRepository.findDistinctBrand());

        //attribute.put("category", categoryRepository.findAll());
        List<String> category = new LinkedList<String>();
        category.add("Платье");
        attribute.put("category", category);

        model.addAllAttributes(attribute);
        return "addClothes";
    }

    @PostMapping("/admin/add_clothes")
    public String addClothes(Clothes clothes, Model model) {
        Clothes clothesFromDb = clothesRepository.findByArticle(clothes.getArticle());
        if(clothesFromDb != null) {
            model.addAttribute("message", "Clothes exists!");
            return "addClothes.ftl";
        }
        clothes.setDate_added(new Date());
        clothesRepository.save(clothes);
        return "redirect:/admin";
    }
}
