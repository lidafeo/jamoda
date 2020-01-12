package com.jamoda.service;

import com.jamoda.model.Image;
import com.jamoda.repository.ClothesRepository;
import com.jamoda.repository.ImageRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.TestPropertySource;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.beans.factory.annotation.Value;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

//@TestPropertySource(properties = {
//        "upload.path=C:/Users/homahel/Desktop/jamoda/uploads"})
class ImageServiceTest {

    @Value("${upload.path}")
    private String uploadPath = "C:/Users/homahel/Desktop/jamoda/uploads";

//    @Test
//    void checkExistsDir() {
//        ImageService imgServMock = new ImageService();
//        Assertions.assertTrue(imgServMock.checkExistsDir());
//
//        Mockito.when(new File(uploadPath)).
//                thenReturn(new File(uploadPath));
//        imgServMock.checkExistsDir();
//    }

//    @Test
//    void addFile() throws IOException {
//
//        MultipartFile file = new MockMultipartFile("test.xlsx", new FileInputStream(new File("C:/users/frost/Desktop/test.xlsx")));

        //storageService.uploadFile(file);
//        ImageService imgServMock = new ImageService();
//        ImageRepository imgRepMock = mock(ImageRepository.class);
//        imgServMock.setImageRepository(imgRepMock);
//
////        String fn = "C:/Users/homahel/Desktop/yes.jpg";
//        final MultipartFile mockFile = mock(MultipartFile.class);
//
//        MultipartFile[] mockFiles = {mockFile};
//        String s = "qwerty";
//
//        List<Image> images = new LinkedList<>();
//        Image image = new Image();
//        image.setArticle("qwerty");
//        image.setName("name");
//        images.add(0, image);
//
//        List<Image> images2  = imgServMock.addImages(mockFiles, s);
//        Assertions.assertEquals(imgServMock.addImages(mockFiles, s),
//               images);

//    }

    @Test
    void saveImageToDb() {
        ImageService imgServMock = new ImageService();
        ImageRepository imgRepMock = mock(ImageRepository.class);
        imgServMock.setImageRepository(imgRepMock);

        Image image = new Image();
        image.setArticle("qwerty");
        imgServMock.saveImageToDb(image);

        Assertions.assertNotNull(imgRepMock.findAllByArticle("qwerty"));
    }
}