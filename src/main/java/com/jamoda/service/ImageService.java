package com.jamoda.service;

import com.jamoda.model.Image;
import com.jamoda.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class ImageService {

    @Autowired
    ImageRepository imageRepository;

    @Value("${upload.path}")
    private String uploadPath;

    public void checkExistsDir() {
        File uploadDir = new File(uploadPath);
        if(!uploadDir.exists()) {
            uploadDir.mkdir();
        }
    }

    public void addFile(MultipartFile file, String filename) throws IOException {
        if(file == null || file.getOriginalFilename().isEmpty()) {
            return;
        }
        file.transferTo(new File(uploadPath + "/" + filename));
    }

    public void saveImageToDb(Image image) {
        imageRepository.save(image);
    }
}
