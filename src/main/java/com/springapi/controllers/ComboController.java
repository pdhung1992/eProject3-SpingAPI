package com.springapi.controllers;

import com.springapi.entities.*;
import com.springapi.payload.request.ComboRequest;
import com.springapi.payload.response.ComboResponse;
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
@RequestMapping("/api/combos")
public class ComboController {
    @Autowired
    private ComboService comboService;
    @Autowired
    private ComboDetailService comboDetailService;
    @Autowired
    private FoodService foodService;
    @Autowired
    private RestaurantService restaurantService;
    @Autowired
    private ServeTypeService serveTypeService;
    @Autowired
    private TagService tagService;

    @GetMapping("/restaurant/{restaurantId}")
    public List<ComboResponse> getCombosByRestaurant(@PathVariable int restaurantId) {
        return comboService.getCombosByRestaurant(restaurantId);
    }

    @GetMapping("/restaurant/{restaurantId}/serve/{serveTypeId}")
    public List<ComboResponse> getCombosByRestaurantAndServeType(@PathVariable int restaurantId, @PathVariable int serveTypeId) {
        return comboService.getCombosByRestaurantAndServeType(restaurantId, serveTypeId);
    }

    @GetMapping("details/{comboId}")
    public ComboResponse getComboDetail(@PathVariable int comboId) {
        return comboService.getComboDetail(comboId);
    }

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('Restaurant Admin')")
    public ResponseEntity<?> createCombo(@ModelAttribute ComboRequest createComboRequest) {
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

            ServeType serveType = serveTypeService.getServeTypeById(createComboRequest.getServeId());
            if (serveType == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Serve type not found");
            }

            Tag tag = tagService.getTagById(createComboRequest.getComboTagId());
            if (tag == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tag not found");
            }

            String thumbnailName = null;
            MultipartFile thumbnail = createComboRequest.getThumbnail();
            if (thumbnail != null && !thumbnail.isEmpty()) {
                File uploadDir = new File("public/img");
                if (!uploadDir.exists()) {
                    uploadDir.mkdirs();
                }

                String thumbnailOriginalFilename = thumbnail.getOriginalFilename();
                String thumbnailFileExtension = thumbnailOriginalFilename.substring(thumbnailOriginalFilename.lastIndexOf("."));
                String thumbnailUniqFilename = System.currentTimeMillis() + thumbnailFileExtension;
                String thumbnailPath = uploadDir.getAbsolutePath() + "/" + thumbnailUniqFilename;
                Files.copy(thumbnail.getInputStream(), Paths.get(thumbnailPath), StandardCopyOption.REPLACE_EXISTING);
                thumbnailName = thumbnailUniqFilename;
            }

            Combo newCombo = new Combo(
                    createComboRequest.getName(),
                    createComboRequest.getDescription(),
                    createComboRequest.getDiscountRate(),
                    thumbnailName,
                    restaurant,
                    serveType,
                    tag
            );

            comboService.createCombo(newCombo);

            for (int foodId : createComboRequest.getFoods()) {
                Food food = foodService.getFoodById(foodId);
                ComboDetail comboDetail = new ComboDetail(newCombo, food);
                comboDetailService.createComboDetail(comboDetail);
            }

            return ResponseEntity.status(HttpStatus.CREATED).body(new ComboResponse(newCombo.getCombo_name()));

        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }



    @DeleteMapping("/delete/{comboId}")
    @PreAuthorize("hasAuthority('Restaurant Admin')")
    public ResponseEntity<?> deleteCombo(@PathVariable int comboId) {
        try {
            Combo deleteCombo = comboService.getComboById(comboId);
            if (deleteCombo.getThumbnail() != null) {
                File deleteFile = new File("public/img/" + deleteCombo.getThumbnail());
                deleteFile.delete();
            }

            ComboDetail[] deleteComboDetails = comboDetailService.getComboDetailsByCombo(comboId).toArray(new ComboDetail[0]);
            for (ComboDetail deleteComboDetail : deleteComboDetails) {
                comboDetailService.deleteComboDetail(deleteComboDetail.getCombo_detail_id());
            }

            comboService.deleteCombo(comboId);
            return ResponseEntity.ok().body("Combo deleted");

        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }
}
