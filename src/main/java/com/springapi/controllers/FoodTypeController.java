package com.springapi.controllers;

import com.springapi.payload.response.FoodTypeResponse;
import com.springapi.services.FoodTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/types")
public class FoodTypeController {
    @Autowired
    FoodTypeService foodTypeService;

    @GetMapping
    public ResponseEntity<List<FoodTypeResponse>> getAllFoodTypes() {
        return ResponseEntity.ok(foodTypeService.getAllFoodTypes());
    }
}
