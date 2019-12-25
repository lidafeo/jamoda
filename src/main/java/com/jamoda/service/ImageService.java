package com.jamoda.service;

import com.jamoda.model.Image;
import com.jamoda.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

@Service
public class ImageService {

    ImageRepository imageRepository;

    @Value("${upload.path}")
    private String uploadPath;
//    private String uploadPath = "C:/Users/homahel/Desktop/jamoda/uploads";

    public boolean checkExistsDir() {
        File uploadDir = new File(uploadPath);
        if(!uploadDir.exists()) {
            uploadDir.mkdir();
            return false;
        }
        return true;
    }

    public void addFile(MultipartFile file, String filename) throws IOException {
        if(file == null || file.getOriginalFilename().isEmpty()) {
            return;
        }
        file.transferTo(new File(uploadPath + "/" + filename));
    }

    public List<Image> addImages(MultipartFile[] files, String article) throws IOException {
        if(files == null || files.length == 0) {
            return null;
        }
        //проверяем наличие папки, создаем если ее нет
        checkExistsDir();
        List<Image> images = new LinkedList<>();
        //сохраняем файлы в папку
        for(int i = 0; i < files.length; i++) {
            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = article + "_" + uuidFile + ".webp";
            addFile(files[i], resultFilename);
            //создаем объект для сохранения в БД
            images.add(new Image(resultFilename, article));
        }
        return images;
    }

    public void saveImageToDb(Image image) {
        imageRepository.save(image);
    }

    @Autowired
    public void setImageRepository(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }
}
