package com.springapi.controllers;

import com.springapi.payload.response.ServeTypeResponse;
import com.springapi.services.ServeTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/servetypes")
public class ServeTypeController {
    @Autowired
    ServeTypeService serveTypeService;

    @GetMapping
    public ResponseEntity<List<ServeTypeResponse>> getAllServeTypes() {
        return ResponseEntity.ok(serveTypeService.getAllServeTypes());
    }
}
