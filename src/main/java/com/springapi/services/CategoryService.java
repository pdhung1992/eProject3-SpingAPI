package com.springapi.services;

import com.springapi.entities.Category;
import com.springapi.payload.response.CategoryResponse;
import com.springapi.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    public List<CategoryResponse> getAllCategories() {
        return categoryRepository.findAll().stream()
                .map(category -> new CategoryResponse(category.getCategory_id(), category.getCategory_name(), category.getDescription()))
                .collect(java.util.stream.Collectors.toList());
    }

    public Category findCategoryById(int id) {
        return categoryRepository.findById(id).orElse(null);
    }

    public CategoryResponse getCategoryById(int id) {
        return categoryRepository.findById(id)
                .map(category -> new CategoryResponse(category.getCategory_id(), category.getCategory_name(), category.getDescription()))
                .orElse(null);
    }

    public void createCategory(Category category) {
        categoryRepository.save(category);
    }

    public void updateCategory(Category category) {
        categoryRepository.save(category);
    }

    public void deleteCategoryById(int id) {
        categoryRepository.deleteById(id);
    }
}
