package com.springapi.controllers;

import com.springapi.entities.*;
import com.springapi.payload.request.FoodRequest;
import com.springapi.payload.response.FoodResponse;
import com.springapi.security.services.AdminDetailsImplement;
import com.springapi.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@RestController
@RequestMapping("/api/foods")
public class FoodController {
    @Autowired
    private FoodService foodService;
    @Autowired
    private RestaurantService restaurantService;
    @Autowired
    private FoodTypeService foodTypeService;
    @Autowired
    private ServeTypeService serveTypeService;
    @Autowired
    private TagService tagService;

    @GetMapping("/restaurant/{restaurantId}")
    public ResponseEntity<?> getFoodsByRestaurant(@PathVariable int restaurantId){
        try {
            List<FoodResponse> foods = foodService.getFoodsByRestaurant(restaurantId);
            return ResponseEntity.status(HttpStatus.OK).body(foods);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e);
        }
    }

    @GetMapping("/restaurant/{restaurantId}/type/{typeId}/serve/{serveId}")
    public ResponseEntity<?> getFoodsByRestaurantAndTypeAndServe(@PathVariable int restaurantId, @PathVariable int typeId, @PathVariable int serveId){
        try {
            List<FoodResponse> foods = foodService.getFoodsByRestaurantAndTypeAndServe(restaurantId, typeId, serveId);
            return ResponseEntity.status(HttpStatus.OK).body(foods);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e);
        }
    }

    @GetMapping("/restaurant/{restaurantId}/type/{typeId}")
    public ResponseEntity<?> getFoodsByRestaurantAndType(@PathVariable int restaurantId, @PathVariable int typeId){
        try {
            List<FoodResponse> foods = foodService.getFoodsByRestaurantAndType(restaurantId, typeId);
            return ResponseEntity.status(HttpStatus.OK).body(foods);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e);
        }
    }

    @GetMapping("details/{foodId}")
    public ResponseEntity<?> getFoodDetail(@PathVariable int foodId){
        try {
            FoodResponse food = foodService.getFoodDetails(foodId);
            return ResponseEntity.status(HttpStatus.OK).body(food);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e);
        }
    }

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('Restaurant Admin')")
    public ResponseEntity<?> createFood(@ModelAttribute FoodRequest createFoodRequest){
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
            }
            AdminDetailsImplement adminDetails = (AdminDetailsImplement) authentication.getPrincipal();

            Restaurant restaurant = restaurantService.getRestaurantByAdmin(adminDetails.getAdmin_id());
            if (restaurant == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Restaurant not found");
            }

            FoodType foodType = foodTypeService.getFoodTypeById(createFoodRequest.getTypeId());
            if (foodType == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Food type not found");
            }

            ServeType serveType = serveTypeService.getServeTypeById(createFoodRequest.getServeId());
            if (serveType == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Serve type not found");
            }

            Tag tag = tagService.getTagById(createFoodRequest.getFoodTagId());
            if (tag == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tag not found");
            }

            String thumbnailName = null;

            MultipartFile thumbnail = createFoodRequest.getThumbnail();

            if (thumbnail != null && !thumbnail.isEmpty()) {
                File uploadDir = new File("public/img");
                if (!uploadDir.exists()){
                    uploadDir.mkdirs();
                }

                String thumbnailOriginalFilename = thumbnail.getOriginalFilename();
                String thumbnailFileExtension = thumbnailOriginalFilename.substring(thumbnailOriginalFilename.lastIndexOf("."));
                String thumbnailUniqFilename = System.currentTimeMillis() + thumbnailFileExtension;
                String thumbnailPath = uploadDir.getAbsolutePath() + "/" + thumbnailUniqFilename;
                Files.copy(thumbnail.getInputStream(), Paths.get(thumbnailPath), StandardCopyOption.REPLACE_EXISTING);
                thumbnailName = thumbnailUniqFilename;
            }
            else {
                System.out.println("No thumbnail");
            }

            Food newFood = new Food(
                    createFoodRequest.getName(),
                    createFoodRequest.getPrice(),
                    thumbnailName,
                    createFoodRequest.getDescription(),
                    restaurant,
                    foodType,
                    serveType,
                    tag
            );

            foodService.createFood(newFood);
            return ResponseEntity.status(HttpStatus.CREATED).body(new FoodResponse(
                    newFood.getFood_name()
            ));

        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e);
        }
    }

    @PutMapping("/update/{foodId}")
    @PreAuthorize("hasAuthority('Restaurant Admin')")
    public ResponseEntity<?> updateFood(@PathVariable int foodId, @ModelAttribute FoodRequest updateFoodRequest){
        try {
            Food food = foodService.getFoodById(foodId);
            if (food == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Food not found");
            }

            FoodType foodType = foodTypeService.getFoodTypeById(updateFoodRequest.getTypeId());
            if (foodType == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Food type not found");
            }

            ServeType serveType = serveTypeService.getServeTypeById(updateFoodRequest.getServeId());
            if (serveType == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Serve type not found");
            }

            Tag tag = tagService.getTagById(updateFoodRequest.getFoodTagId());
            if (tag == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tag not found");
            }

            String thumbnailName = null;

            MultipartFile thumbnail = updateFoodRequest.getThumbnail();
            if (thumbnail != null && !thumbnail.isEmpty()) {
                File uploadDir = new File("public/img");
                if (!uploadDir.exists()){
                    uploadDir.mkdirs();
                }

                String thumbnailOriginalFilename = thumbnail.getOriginalFilename();
                String thumbnailFileExtension = thumbnailOriginalFilename.substring(thumbnailOriginalFilename.lastIndexOf("."));
                String thumbnailUniqFilename = System.currentTimeMillis() + thumbnailFileExtension;
                String thumbnailPath = uploadDir.getAbsolutePath() + "/" + thumbnailUniqFilename;
                Files.copy(thumbnail.getInputStream(), Paths.get(thumbnailPath), StandardCopyOption.REPLACE_EXISTING);
                thumbnailName = thumbnailUniqFilename;
            }
            else {
                System.out.println("No thumbnail");
            }

            food.setFood_name(updateFoodRequest.getName());
            food.setFood_price(updateFoodRequest.getPrice());
            food.setThumbnail(thumbnailName);
            food.setDescription(updateFoodRequest.getDescription());
            food.setFoodType(foodType);
            food.setServeType(serveType);
            food.setTag(tag);

            foodService.updateFood(food);
            return ResponseEntity.status(HttpStatus.OK).body(new FoodResponse(
                    food.getFood_name()
            ));

        } catch (ClassCastException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("User is not authorized to perform this action");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{foodId}")
    @PreAuthorize("hasAuthority('Restaurant Admin')")
    public ResponseEntity<?> deleteFood(@PathVariable int foodId){
        try {
            Food deletedFood = foodService.getFoodById(foodId);
            if (deletedFood != null) {
                if (deletedFood.getThumbnail() != null) {
                    File thumbnailFile = new File("public/img/" + deletedFood.getThumbnail());
                    if (thumbnailFile.exists()) {
                        thumbnailFile.delete();
                    }
                }
            }
            foodService.deleteFood(foodId);
            return ResponseEntity.status(HttpStatus.OK).body("Food deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e);
        }
    }
}
