package com.springapi.controllers;

import com.springapi.entities.Admin;
import com.springapi.entities.Category;
import com.springapi.entities.District;
import com.springapi.entities.Restaurant;
import com.springapi.payload.request.RestaurantRequest;
import com.springapi.payload.response.PermissionResponse;
import com.springapi.payload.response.RestaurantResponse;
import com.springapi.security.services.AdminDetailsImplement;
import com.springapi.services.CategoryService;
import com.springapi.services.DistrictService;
import com.springapi.services.RestaurantService;
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
@RequestMapping("/api/restaurant")
public class RestaurantController {
    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private DistrictService districtService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/admin")
    @PreAuthorize("hasAuthority('Restaurant Admin')")
    public ResponseEntity<?> getRestaurantByAdmin(){
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Error: Authentication not found");
            }

            AdminDetailsImplement adminDetails = (AdminDetailsImplement) authentication.getPrincipal();

            Restaurant restaurant = restaurantService.getRestaurantByAdmin(adminDetails.getAdmin_id());
            if (restaurant == null) {
                return ResponseEntity.badRequest().body("Error: Restaurant not found");
            }

            return ResponseEntity.status(HttpStatus.OK).body(new RestaurantResponse(
                    restaurant.getRestaurant_id(),
                    restaurant.getRestaurant_name(),
                    restaurant.getRestaurant_address(),
                    restaurant.getJoin_date(),
                    restaurant.getDescription(),
                    restaurant.getDelivery_hours(),
                    restaurant.getMinimum_order(),
                    restaurant.getPrepaid_rate(),
                    restaurant.getThumbnail(),
                    restaurant.getBanner(),
                    restaurant.getDistrict().getDistrict_id(),
                    restaurant.getDistrict().getDistrict_name(),
                    restaurant.getDistrict().getCity().getCity_id(),
                    restaurant.getDistrict().getCity().getCity_name(),
                    restaurant.getCategory().getCategory_id(),
                    restaurant.getCategory().getCategory_name()
            ));
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Error: " + e);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getRestaurantDetails(@PathVariable int id){
        try {
            Restaurant restaurant = restaurantService.getRestaurantById(id);
            if (restaurant == null) {
                return ResponseEntity.badRequest().body("Error: Restaurant not found");
            }

            return ResponseEntity.status(HttpStatus.OK).body(new RestaurantResponse(
                    restaurant.getRestaurant_id(),
                    restaurant.getRestaurant_name(),
                    restaurant.getRestaurant_address(),
                    restaurant.getJoin_date(),
                    restaurant.getDescription(),
                    restaurant.getDelivery_hours(),
                    restaurant.getMinimum_order(),
                    restaurant.getPrepaid_rate(),
                    restaurant.getThumbnail(),
                    restaurant.getBanner(),
                    restaurant.getDistrict().getDistrict_id(),
                    restaurant.getDistrict().getDistrict_name(),
                    restaurant.getDistrict().getCity().getCity_id(),
                    restaurant.getDistrict().getCity().getCity_name(),
                    restaurant.getCategory().getCategory_id(),
                    restaurant.getCategory().getCategory_name()
            ));
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Error: " + e);
        }
    }

    @GetMapping("city/{cityId}")
    public ResponseEntity<?> getRestaurantByCity(@PathVariable int cityId){
        try {
            List<RestaurantResponse> restaurants = restaurantService.getRestaurantByCity(cityId);
            return ResponseEntity.status(HttpStatus.OK).body(restaurants);
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Error: " + e);
        }
    }

    @GetMapping("district/{districtId}")
    public ResponseEntity<?> getRestaurantByDistrict(@PathVariable int districtId){
        try {
            List<RestaurantResponse> restaurants = restaurantService.getRestaurantsByDistrict(districtId);
            return ResponseEntity.status(HttpStatus.OK).body(restaurants);
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Error: " + e);
        }
    }

    @GetMapping("category/{categoryId}")
    public ResponseEntity<?> getRestaurantByCategory(@PathVariable int categoryId){
        try {
            List<RestaurantResponse> restaurants = restaurantService.getRestaurantsByCategory(categoryId);
            return ResponseEntity.status(HttpStatus.OK).body(restaurants);
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Error: " + e);
        }
    }

    @PutMapping("/update")
    @PreAuthorize("hasAuthority('Restaurant Admin')")
    public ResponseEntity<?> updateRestaurant(@ModelAttribute RestaurantRequest updateRestaurantRequest){
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Error: Authentication not found");
            }

            AdminDetailsImplement adminDetails = (AdminDetailsImplement) authentication.getPrincipal();

            Restaurant updateRestaurant = restaurantService.getRestaurantByAdmin(adminDetails.getAdmin_id());
            if (updateRestaurant == null) {
                return ResponseEntity.badRequest().body("Error: Restaurant not found");
            }

            District district = districtService.findDistrictById(updateRestaurantRequest.getDistrictId());
            if (district == null) {
                return ResponseEntity.badRequest().body("Error: District not found");
            }

            Category category = categoryService.findCategoryById(updateRestaurantRequest.getCatId());
            if (category == null) {
                return ResponseEntity.badRequest().body("Error: Category not found");
            }

            updateRestaurant.setRestaurant_name(updateRestaurantRequest.getName());
            updateRestaurant.setRestaurant_address(updateRestaurantRequest.getAddress());
            updateRestaurant.setDescription(updateRestaurantRequest.getDescription());
            updateRestaurant.setDelivery_hours(updateRestaurantRequest.getDeliveryHours());
            updateRestaurant.setMinimum_order(updateRestaurantRequest.getMinimumDelivery());
            updateRestaurant.setPrepaid_rate(updateRestaurantRequest.getPrePaidRate());
            updateRestaurant.setDistrict(district);
            updateRestaurant.setCategory(category);

            if (updateRestaurantRequest.getThumbnail() != null && !updateRestaurantRequest.getThumbnail().isEmpty()){
                MultipartFile thumbnail = updateRestaurantRequest.getThumbnail();
                File uploadDir = new File("public/img");
                if (!uploadDir.exists()){
                    uploadDir.mkdirs();
                }

                if (updateRestaurant.getThumbnail() != null){
                    File oldThumbnail = new File(uploadDir, updateRestaurant.getThumbnail());
                    oldThumbnail.delete();
                }

                String thumbnailOriginalFilename = thumbnail.getOriginalFilename();
                String thumbnailFileExtension = thumbnailOriginalFilename.substring(thumbnailOriginalFilename.lastIndexOf("."));
                String thumbnailUniqFilename = System.currentTimeMillis() + thumbnailFileExtension;
                String thumbnailPath = uploadDir.getAbsolutePath() + "/" + thumbnailUniqFilename;
                Files.copy(thumbnail.getInputStream(), Paths.get(thumbnailPath), StandardCopyOption.REPLACE_EXISTING);
                updateRestaurant.setThumbnail(thumbnailUniqFilename);
            }

            if (updateRestaurantRequest.getBanner() != null && !updateRestaurantRequest.getBanner().isEmpty()){
                MultipartFile banner = updateRestaurantRequest.getBanner();
                File uploadDir = new File("public/img");
                if (!uploadDir.exists()){
                    uploadDir.mkdirs();
                }

                if (updateRestaurant.getBanner() != null){
                    File oldBanner = new File(uploadDir, updateRestaurant.getBanner());
                    oldBanner.delete();
                }

                String bannerOriginalFilename = banner.getOriginalFilename();
                String bannerFileExtension = bannerOriginalFilename.substring(bannerOriginalFilename.lastIndexOf("."));
                String bannerUniqFilename = System.currentTimeMillis() + bannerFileExtension;
                String bannerPath = uploadDir.getAbsolutePath() + "/" + bannerUniqFilename;
                Files.copy(banner.getInputStream(), Paths.get(bannerPath), StandardCopyOption.REPLACE_EXISTING);
                updateRestaurant.setBanner(bannerUniqFilename);
            }

            restaurantService.updateRestaurant(updateRestaurant);

            return ResponseEntity.status(HttpStatus.OK).body(new RestaurantResponse(
                    updateRestaurant.getRestaurant_name()
            ));
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Error: " + e);
        }
    }
}
