package com.jamoda.controller;

import com.jamoda.model.*;
import com.jamoda.repository.*;
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
    @Autowired
    private ImageRepository imageRepository;
    @Autowired
    private AttributeGroupRepository attributeGroupRepository;
    @Autowired
    private AttributeRepository attributeRepository;
    @Autowired
    private AttributeValueRepository attributeValueRepository;

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
            model.addAttribute("error", "Такой пользователь уже существует!");
            return "addUser";
        }
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.ADMIN));
        userRepository.save(user);
        model.addAttribute("message", "success");
        return "addUser";
        //return "redirect:/admin";
    }

    //admin/add_file
    @GetMapping("/admin/add_file")
    public String pageAddFile(Model model) {
        return "addFile";
    }

    @PostMapping("/admin/add_file")
    public String addFile(@RequestParam("article") String article, @RequestParam("file") MultipartFile file, Model model) throws IOException {
        if(file == null || file.getOriginalFilename().isEmpty()) {
            model.addAttribute("error", "Прикрепите файл!");
            return "addFile";
        }
        File uploadDir = new File(uploadPath);
        if(!uploadDir.exists()) {
            uploadDir.mkdir();
        }
        String uuidFile = UUID.randomUUID().toString();
        String resultFilename = article + "_" + uuidFile + ".webp";
        Image image = new Image(resultFilename, article);
        file.transferTo(new File(uploadPath + "/" + resultFilename));
        imageRepository.save(image);
        model.addAttribute("message", "success");
        return "addFile";
    }

    //admin/add_clothes
    @GetMapping("/admin/add_clothes")
    public String pageAddClothes(Model model) {
        //attribute.put("category", categoryRepository.findAll());

        List<Category> categories = categoryRepository.findAll();
        model.addAttribute("category", categories);
        return "addClothes";
    }

    @PostMapping("/admin/add_clothes")
    public String addClothes(Clothes clothes, Model model) {
        Clothes clothesFromDb = clothesRepository.findByArticle(clothes.getArticle());
        if(clothesFromDb != null) {
            List<Category> categories = categoryRepository.findAll();
            model.addAttribute("category", categories);
            model.addAttribute("error", "Такой товар уже существует!");
            return "addClothes";
        }
        clothes.setDate_added(new Date());
        clothesRepository.save(clothes);
        List<Category> categories = categoryRepository.findAll();
        model.addAttribute("category", categories);
        model.addAttribute("message", "success");
        return "addClothes";
    }

    //admin/add_category
    @GetMapping("/admin/add_category")
    public String pageAddCategory(Model model) {
        model.addAttribute("categories", categoryRepository.findAll());
        return "addCategory";
    }

    @PostMapping("/admin/add_category")
    public String addCategory(Category category, Model model) {
        Category categoryFromDb = categoryRepository.findByNameEnOrNameRusEquals(category.getNameEn(), category.getNameRus());
        if(categoryFromDb != null) {
            model.addAttribute("categories", categoryRepository.findAll());
            model.addAttribute("error", "Такая категория уже существует!");
            return "addCategory";
        }
        categoryRepository.save(category);
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("message", "success");
        return "addCategory";
    }

    //admin/add_attribute
    @GetMapping("/admin/add_attribute")
    public String pageAddAttribute(Model model) {
        model.addAttribute("groups", attributeGroupRepository.findAll());
        return "addAttribute";
    }

    @PostMapping("/admin/add_attribute")
    public String addAttribute(Attribute attribute, @RequestParam(name="groupId") long groupId, Model model) {
        Attribute attributeFromDb = attributeRepository.findByName(attribute.getName());
        AttributeGroup attributeGroup = attributeGroupRepository.findById(groupId);
        //сохраняем атрибут
        if(attributeFromDb != null) {
            model.addAttribute("error", "Такой атрибут уже существует!");
            model.addAttribute("groups", attributeGroupRepository.findAll());
            return "addAttribute";
        }
        if(attributeGroup != null) {
            attribute.setGroup(attributeGroup);
        }
        attributeRepository.save(attribute);
        model.addAttribute("message", "success");
        model.addAttribute("groups", attributeGroupRepository.findAll());
        return "addAttribute";
    }

    //admin/add_group
    @GetMapping("/admin/add_group")
    public String pageAddGroup(Model model) {
        return "addGroup";
    }

    @PostMapping("/admin/add_group")
    public String addGroup(AttributeGroup attributeGroup, Model model) {
        AttributeGroup attributeGroupFromDb = attributeGroupRepository.findByName(attributeGroup.getName());
        if(attributeGroupFromDb != null) {
            model.addAttribute("error", "Такая группа атрибутов уже существует!");
            return "addGroup";
        }
        attributeGroupRepository.save(attributeGroup);
        model.addAttribute("message", "success");
        return "addGroup";
    }

    //admin/add_attribute_value
    @GetMapping("/admin/add_attribute_value")
    public String pageAddAttributeValue(Model model) {
        model.addAttribute("clothes", clothesRepository.findAll());
        model.addAttribute("attributes", attributeRepository.findAll());
        return "addAttributeValue";
    }

    @PostMapping("/admin/add_attribute_value")
    public String addAttributeValue(AttributeValue attributeValue,
                                    @RequestParam(name="product_article") String article,
                                    @RequestParam(name="attribute_id") long attribute_id,
                                    Model model) {
        AttributeValue attributeValueFromDb = attributeValueRepository.findById(attributeValue.getId());
        if(attributeValueFromDb != null) {
            model.addAttribute("error", "Такой атрибут уже добавлен!");
            model.addAttribute("clothes", clothesRepository.findAll());
            model.addAttribute("attributes", attributeRepository.findAll());
            return "addAttributeValue";
        }
        Clothes clothes = clothesRepository.findByArticle(article);
        if(clothes == null) {
            model.addAttribute("error", "Такого товара не существует!");
            model.addAttribute("clothes", clothesRepository.findAll());
            model.addAttribute("attributes", attributeRepository.findAll());
            return "addAttributeValue";
        }
        attributeValue.setClothes(clothes);

        Attribute attribute = attributeRepository.findById(attribute_id);
        if(attribute == null) {
            model.addAttribute("error", "Такого атрибута не существует!");
            model.addAttribute("clothes", clothesRepository.findAll());
            model.addAttribute("attributes", attributeRepository.findAll());
            return "addAttributeValue";
        }
        attributeValue.setAttribute(attribute);
        attributeValueRepository.save(attributeValue);
        model.addAttribute("message", "success");
        model.addAttribute("clothes", clothesRepository.findAll());
        model.addAttribute("attributes", attributeRepository.findAll());
        return "addAttributeValue";
    }
}
