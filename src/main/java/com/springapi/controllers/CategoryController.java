package com.springapi.controllers;

import com.springapi.entities.Category;
import com.springapi.payload.request.CategoryRequest;
import com.springapi.payload.response.CategoryResponse;
import com.springapi.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<CategoryResponse>> getAllCategories() {
        return ResponseEntity.ok(categoryService.getAllCategories());
    }

    @GetMapping("/details/{id}")
    public ResponseEntity<?> getCategoryById(@PathVariable int id) {
        Category category = categoryService.findCategoryById(id);

        if (category != null) {
            return ResponseEntity.status(HttpStatus.OK).body(new CategoryResponse(category.getCategory_id(), category.getCategory_name(), category.getDescription()));
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Category not found");
    }

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('Root Admin')")
    public ResponseEntity<?> createCategory(@RequestBody CategoryRequest categoryRequest) {
        try {
            Category newCategory = new Category(
                    categoryRequest.getName(),
                    categoryRequest.getDescription()
            );

            categoryService.createCategory(newCategory);
            return ResponseEntity.status(HttpStatus.CREATED).body(new CategoryResponse(newCategory.getCategory_id(), newCategory.getCategory_name(), newCategory.getDescription()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error creating category");
        }
    }

    @PostMapping("/update/{id}")
    @PreAuthorize("hasAuthority('Root Admin')")
    public ResponseEntity<?> updateCategory(@PathVariable int id, @RequestBody CategoryRequest categoryRequest) {
        try {
            Category category = categoryService.findCategoryById(id);
            if (category == null) {
                return ResponseEntity.badRequest().body("Category not found");
            }

            category.setCategory_name(categoryRequest.getName());
            category.setDescription(categoryRequest.getDescription());

            categoryService.updateCategory(category);

            return ResponseEntity.ok(new CategoryResponse(category.getCategory_id(), category.getCategory_name(), category.getDescription()));

        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error updating category");
        }
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('Root Admin')")
    public ResponseEntity<?> deleteCategoryById(@PathVariable int id) {
        try {
            categoryService.deleteCategoryById(id);
            return ResponseEntity.ok("Category deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error deleting category");
        }
    }
}
