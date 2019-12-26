package com.jamoda.controller.admin;

import com.jamoda.model.Clothes;
import com.jamoda.model.Image;
import com.jamoda.service.ClothesService;
import com.jamoda.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.LinkedList;
import java.util.List;

@Controller
public class DbAdminController {
    private ClothesService clothesService;
    private ImageService imageService;

    @GetMapping("/admin/fill_db")
    public String filleDb(Model model) {
        List<Clothes> clothes = clothesService.findAll();
        for(int i = 0; i < 500; i++) {
            for(Clothes clo : clothes) {
                String article =  clo.getArticle() + i;
                List<Image> images = new LinkedList<>();
                for(Image image : clo.getImages()) {
                    Image newIm = new Image(image.getName(), article);
                    imageService.saveImageToDb(newIm);
                    images.add(image);
                }
                clothesService.saveClothes(new Clothes(clo, article, images));
            }
        }
        return "redirect:/admin";
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
