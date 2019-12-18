package com.jamoda.service;

import com.jamoda.model.Category;
import com.jamoda.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> findMainCategory() {
        return categoryRepository.findAllByType("main");
    }

    public Category findByNameEn(String nameEn) {
        return categoryRepository.findByNameEn(nameEn);
    }

    public List<Category> getChildrenCategory(Category category) {
        if(category == null)
            return null;
        List<Category> allCategories = new LinkedList<>();
        allCategories.add(category);
        List<Category> categories = categoryRepository.findAllByParent(category);
        while (!categories.isEmpty()) {
            allCategories.addAll(categories);
            categories = categoryRepository.findAllByParentIn(categories);
        }
        return allCategories;
    }
}
