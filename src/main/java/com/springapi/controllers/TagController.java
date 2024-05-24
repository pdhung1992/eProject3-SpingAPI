package com.springapi.controllers;

import com.springapi.payload.response.FoodTagResponse;
import com.springapi.services.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/ftags")
public class TagController {
    @Autowired
    TagService tagService;

    @GetMapping
    public ResponseEntity<List<FoodTagResponse>> getAllTags() {
        return ResponseEntity.ok(tagService.getAllTags());
    }
}
