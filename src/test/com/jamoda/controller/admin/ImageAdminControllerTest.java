package com.jamoda.controller.admin;

import com.jamoda.service.ClothesService;
import com.jamoda.service.ImageService;
import com.jamoda.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class ImageAdminControllerTest {

    @Test
    void pageAddFile() {
        ImageAdminController imageAdminController = new ImageAdminController();
        ClothesService clothesService = Mockito.mock(ClothesService.class);
        imageAdminController.setClothesService(clothesService);

        Model model = mock(Model.class);

        Assertions.assertNotNull(imageAdminController.pageAddFile(model));
        Assertions.assertEquals( "admin/addFile",
                imageAdminController.pageAddFile(model));
    }

    @Test
    void addFile() throws IOException {
        ImageAdminController imageAdminController = new ImageAdminController();
        ClothesService clothesService = Mockito.mock(ClothesService.class);
        imageAdminController.setClothesService(clothesService);
        ImageService imageService = Mockito.mock(ImageService.class);
        imageAdminController.setImageService(imageService);

        String article = "qwerty";
        MultipartFile file = new MockMultipartFile("yes.jpg", new FileInputStream(new File("C:/users/homahel/Desktop/yes.jpg")));

        Model model = mock(Model.class);

        Assertions.assertNotNull(imageAdminController.addFile(
                article, file, model));
        Assertions.assertEquals( "admin/addFile",
                imageAdminController.addFile(
                        article, file, model));
    }
}