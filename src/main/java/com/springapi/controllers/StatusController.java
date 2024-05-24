package com.springapi.controllers;

import com.springapi.payload.response.StatusResponse;
import com.springapi.services.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/status")
public class StatusController {
    @Autowired
    StatusService statusService;

    @GetMapping
    public ResponseEntity<List<StatusResponse>> getAllStatus() {
        return ResponseEntity.ok(statusService.getAllStatus());
    }
}
