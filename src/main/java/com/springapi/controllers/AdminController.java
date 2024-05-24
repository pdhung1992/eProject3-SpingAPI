package com.springapi.controllers;

import com.springapi.entities.*;
import com.springapi.payload.request.AccountRequest;
import com.springapi.payload.response.AdminResponse;
import com.springapi.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("api/accounts")
@PreAuthorize("hasAuthority('Root Admin')")
public class AdminController {
    @Autowired
    private AdminService adminService;
    @Autowired
    private RestaurantService restaurantService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private DistrictService districtService;
    @Autowired
    private PasswordEncoder encoder;

    @GetMapping
    public ResponseEntity<List<AdminResponse>> getAllAccounts(){
        return ResponseEntity.status(HttpStatus.OK).body(adminService.getAllAccounts());
    }

    @GetMapping("details/{id}")
    public ResponseEntity<AdminResponse> getAccountDetails(@PathVariable int id){
        return ResponseEntity.status(HttpStatus.OK).body(adminService.getAdminById(id));
    }

    @PostMapping("/create")
    public ResponseEntity<?> createAccount(@ModelAttribute AccountRequest createAccountRequest){
        try {
            //check if username already exists

            if (adminService.checkIfUsernameExists(createAccountRequest.getUsername())){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username already exists");
            }

            Role role = roleService.getRoleById(createAccountRequest.getRoleId());
            if (role == null){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Role not found");
            }

            String encodedPassword = encoder.encode(createAccountRequest.getPassword());

            Admin newAdmin = new Admin(
                    createAccountRequest.getUsername(),
                    encodedPassword,
                    createAccountRequest.getEmail(),
                    createAccountRequest.getTelephone(),
                    createAccountRequest.getFullname(),
                    role
            );

            adminService.createAdmin(newAdmin);

            if (createAccountRequest.getRoleId() == 2){
                MultipartFile thumbnail = createAccountRequest.getResThumbnail();
                MultipartFile banner = createAccountRequest.getResBanner();

                String thumbnailName = null;
                String bannerName = null;

                if (thumbnail != null && !thumbnail.isEmpty()){
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

                if (banner != null && !banner.isEmpty()){
                    File uploadDir = new File("public/img");
                    if (!uploadDir.exists()){
                        uploadDir.mkdirs();
                    }

                    String bannerOriginalFilename = banner.getOriginalFilename();
                    String bannerFileExtension = bannerOriginalFilename.substring(bannerOriginalFilename.lastIndexOf("."));
                    String bannerUniqFilename = System.currentTimeMillis() + bannerFileExtension;
                    String bannerPath = uploadDir.getAbsolutePath() + "/" + bannerUniqFilename;
                    Files.copy(banner.getInputStream(), Paths.get(bannerPath), StandardCopyOption.REPLACE_EXISTING);
                    bannerName = bannerUniqFilename;
                }

                Date joinDate = new Date();

                Category category = categoryService.findCategoryById(createAccountRequest.getResCatId());
                if (category == null){
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Category not found");
                }

                District district = districtService.findDistrictById(createAccountRequest.getResDistrictId());
                if (district == null){
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("District not found");
                }

                Restaurant newRestaurant = new Restaurant(
                        createAccountRequest.getResName(),
                        createAccountRequest.getResAddress(),
                        joinDate,
                        thumbnailName,
                        createAccountRequest.getResDescription(),
                        createAccountRequest.getResDeliveryHours(),
                        createAccountRequest.getResMinimumDelivery(),
                        createAccountRequest.getPrepaidRate(),
                        bannerName,
                        category,
                        district,
                        newAdmin
                );

                restaurantService.createRestaurant(newRestaurant);
            }

            return ResponseEntity.status(HttpStatus.CREATED).body(new AdminResponse(newAdmin.getUsername()));

        }catch (Exception e){
            System.out.println("err here");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<?> updateAccount(@PathVariable int id, @RequestBody AccountRequest updateAccountRequest) {
        try {
            Admin updateAdmin = adminService.findAdminById(id);
            if (updateAdmin == null){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Account not found");
            }

            updateAdmin.setFullName(updateAccountRequest.getFullname());
            updateAdmin.setEmail(updateAccountRequest.getEmail());
            updateAdmin.setPhone(updateAccountRequest.getTelephone());

            adminService.updateAdmin(updateAdmin);
            return ResponseEntity.status(HttpStatus.OK).body(new AdminResponse(updateAdmin.getUsername()));


        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteAccount(@PathVariable int id){
        try {
            Admin admin = adminService.findAdminById(id);
            if (admin == null){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Account not found");
            }

            Restaurant restaurant = adminService.findRestaurantByAdminId(id);
            if (restaurant != null){
                restaurantService.deleteRestaurant(restaurant.getRestaurant_id());
                if (restaurant.getThumbnail() != null){
                    File thumbnail = new File("public/img/" + restaurant.getThumbnail());
                    thumbnail.delete();
                };
                if (restaurant.getBanner() != null){
                    File banner = new File("public/img/" + restaurant.getBanner());
                    banner.delete();
                };
            }
            adminService.deleteAdmin(id);
            return ResponseEntity.status(HttpStatus.OK).body("Account deleted");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
