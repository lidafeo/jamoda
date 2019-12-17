package com.jamoda.service;

import com.jamoda.model.Category;
import com.jamoda.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> findMainCategory() {
        return categoryRepository.findAllByType("main");
    }
}
