package com.springapi.controllers;

import com.springapi.payload.response.AdminResponse;
import com.springapi.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/accounts")
@PreAuthorize("hasAuthority('Root Admin')")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @GetMapping
    public ResponseEntity<List<AdminResponse>> getAllAccounts(){
        return ResponseEntity.status(HttpStatus.OK).body(adminService.getAllAccounts());
    }
}
