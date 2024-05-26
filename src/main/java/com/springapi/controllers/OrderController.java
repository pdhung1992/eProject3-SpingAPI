package com.springapi.controllers;

import com.springapi.entities.Order;
import com.springapi.entities.Restaurant;
import com.springapi.payload.request.OrderRequest;
import com.springapi.security.services.AdminDetailsImplement;
import com.springapi.security.services.UserDetailsImplement;
import com.springapi.services.OrderService;
import com.springapi.services.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    RestaurantService restaurantService;

    @GetMapping("/restaurant")
    @PreAuthorize("hasAuthority('Restaurant Admin')")
    public ResponseEntity<?> getRestaurantOrders() {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Error: Unauthorized");
            }

            AdminDetailsImplement adminDetails = (AdminDetailsImplement) authentication.getPrincipal();

            Restaurant restaurant = restaurantService.getRestaurantByAdmin(adminDetails.getAdmin_id());
            if (restaurant == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: Restaurant not found");
            }

            return ResponseEntity.ok(orderService.getRestaurantOrders(restaurant.getRestaurant_id()));

        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body("Error: " + e.getMessage());
        }
    }

    @GetMapping("/user")
    @PreAuthorize("hasAuthority('User')")
    public ResponseEntity<?> getUserOrders() {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Error: Unauthorized");
            }

            UserDetailsImplement userDetails = (UserDetailsImplement) authentication.getPrincipal();

            return ResponseEntity.ok(orderService.getUserOrders(userDetails.getUser_id()));

        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body("Error: " + e.getMessage());
        }
    }
}
