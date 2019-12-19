package com.jamoda.controller.admin;

import com.jamoda.model.Image;
import com.jamoda.service.ClothesService;
import com.jamoda.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Controller
public class ImageAdminController {

    private ClothesService clothesService;
    private ImageService imageService;

    @GetMapping("/admin/add_file")
    public String pageAddFile(Model model) {
        model.addAttribute("products", clothesService.findAll());
        return "admin/addFile";
    }

    @PostMapping("/admin/add_file")
    public String addFile(@RequestParam("article") String article,
                          @RequestParam("file") MultipartFile file,
                          Model model) throws IOException {
        model.addAttribute("products", clothesService.findAll());

        if(file == null || file.getOriginalFilename().isEmpty()) {
            model.addAttribute("error", "Прикрепите файл!");
            return "admin/addFile";
        }
        //проверяем наличие папки, создаем если ее нет
        imageService.checkExistsDir();
        //сохраняем файл в папку
        String uuidFile = UUID.randomUUID().toString();
        String resultFilename = article + "_" + uuidFile + ".webp";
        imageService.addFile(file, resultFilename);
        //создаем объект для сохранения в БД
        Image image = new Image(resultFilename, article);
        imageService.saveImageToDb(image);
        model.addAttribute("message", "success");
        return "admin/addFile";
    }

    @Autowired
    public void setClothesService(ClothesService clothesService) {
        this.clothesService = clothesService;
    }
    @Autowired
    public void setImageService(ImageService imageService) {
        this.imageService = imageService;
    }
}
