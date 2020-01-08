package com.jamoda.service;

import com.jamoda.model.Clothes;
import com.jamoda.repository.ClothesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchService {
    private ClothesRepository clothesRepository;

    public Page<Clothes> findClothesByQ(String q, Pageable pageable) {
        String[] arr = q.trim().split(" ");
        return clothesRepository.findByStringsContains(arr, pageable);
    }

    @Autowired
    public void setClothesRepository(ClothesRepository clothesRepository) {
        this.clothesRepository = clothesRepository;
    }
}
