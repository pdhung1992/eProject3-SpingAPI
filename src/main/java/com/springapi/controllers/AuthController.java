package com.springapi.controllers;

import com.springapi.entities.Admin;
import com.springapi.entities.Role;
import com.springapi.entities.User;
import com.springapi.payload.request.*;
import com.springapi.payload.response.AdminJwtResponse;
import com.springapi.payload.response.PermissionResponse;
import com.springapi.payload.response.UserJwtResponse;
import com.springapi.payload.response.MessageResponse;
import com.springapi.repositories.AdminRepository;
import com.springapi.repositories.RoleRepository;
import com.springapi.repositories.UserRepository;
import com.springapi.security.jwt.JwtUtils;
import com.springapi.security.services.AdminDetailsImplement;
import com.springapi.security.services.UserDetailsImplement;
import com.springapi.services.AdminService;
import com.springapi.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private AdminService adminService;
    @Autowired
    RoleRepository roleRepository;

    //User

    //Register
    @PostMapping("/user/register")
    public ResponseEntity<?> userRegister(@Valid @RequestBody RegisterRequest registerRequest){
        try {
            //Check if email is already in use
            if(userRepository.existsByEmail(registerRequest.getEmail())){
                return ResponseEntity.badRequest().body(new MessageResponse("Email is already in use!"));
            }

            //Create new user's account
            User newUser = new User(
                    registerRequest.getFullname(),
                    registerRequest.getEmail(),
                    registerRequest.getPhone(),
                    encoder.encode(registerRequest.getPassword())
            );

            userRepository.save(newUser);

            return ResponseEntity.status(HttpStatus.CREATED).body(new MessageResponse("User registered successfully!"));
        } catch (Exception e){
            return ResponseEntity.badRequest().body(new MessageResponse("Error: " + e.getMessage()));
        }
    }

    //Login
    @PostMapping("/user/login")
    public ResponseEntity<?> userLogin(@Valid @RequestBody UserLogInRequest loginRequest){
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);

            String jwt = jwtUtils.generateUserJwtToken(authentication);
            UserDetailsImplement userDetails = (UserDetailsImplement) authentication.getPrincipal();

            return ResponseEntity.ok(new UserJwtResponse(
                    jwt,
                    userDetails.getUser_id(),
                    userDetails.getEmail(),
                    userDetails.getFullname()
            ));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Incorrect username or password.");
        }
    }

    //Change password
    @PostMapping("/user/change-password")
    public ResponseEntity<?> changePassword(@RequestBody ChangePasswordRequest changePasswordRequest, @RequestHeader("Authorization") String tokenHeader){
        try {
            String token = tokenHeader.substring(7);

            String email = jwtUtils.getUserNameFromJwtToken(token);

            if (!userRepository.existsByEmail(email)){
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Change password failed.");
            }

            User user = (User) userService.getUserByEmail(email).get();

            if (!encoder.matches(changePasswordRequest.getCurrentPassword(), user.getPassword())){
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Old password incorrect.");
            }

            user.setPassword(encoder.encode(changePasswordRequest.getNewPassword()));
            userRepository.save(user);

            return ResponseEntity.ok(new MessageResponse("Change password successfully!"));

        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Change password failed." + tokenHeader);
        }
    }

    //Admin

    //Create Account
    @PostMapping("/admin/create-account")
    @PreAuthorize("hasAuthority('Root Admin')")
    public ResponseEntity<?> createAccount(@Valid @RequestBody AccountRequest createAccountRequest){
        try {
            //Check if username is already in use
            if(adminRepository.existsByUsername(createAccountRequest.getUsername())){
                return ResponseEntity.badRequest().body(new MessageResponse("Username is already taken!"));
            }

            Role role = roleRepository.getReferenceById(createAccountRequest.getRoleId());
            if(role == null){
                return ResponseEntity.badRequest().body(new MessageResponse("Role not found!"));
            }

            String encodedPassword = encoder.encode(createAccountRequest.getPassword());

            //Create new admin's account
            Admin newAdmin = new Admin(
                    createAccountRequest.getUsername(),
                    encodedPassword,
                    createAccountRequest.getEmail(),
                    createAccountRequest.getTelephone(),
                    createAccountRequest.getFullname(),
                    role
            );

            adminRepository.save(newAdmin);

            return ResponseEntity.status(HttpStatus.CREATED).body(new MessageResponse("Admin created successfully!"));
        } catch (Exception e){
            return ResponseEntity.badRequest().body(new MessageResponse("Error: " + e.getMessage()));
        }
    }


    //Login
    @PostMapping("/admin/login")
    public ResponseEntity<?> adminLogin(@Valid @RequestBody AdminLogInRequest loginRequest){
        try {

            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);

            String jwt = jwtUtils.generateAdminJwtToken(authentication);
            AdminDetailsImplement adminDetails = (AdminDetailsImplement) authentication.getPrincipal();

            String role = adminDetails.getRole().getRole();

            List<PermissionResponse> permissions = adminService.getPermissionsByRoleId(adminDetails.getRole().getRole_id());

            return ResponseEntity.ok(new AdminJwtResponse(
                    jwt,
                    adminDetails.getAdmin_id(),
                    adminDetails.getUsername(),
                    adminDetails.getEmail(),
                    adminDetails.getFullname(),
                    role,
                    permissions
            ));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Incorrect username or password.");
        }
    }

    //Change password
    @PostMapping("/admin/change-password")
    public ResponseEntity<?> changeAdminPassword(@RequestBody ChangePasswordRequest changePasswordRequest, @RequestHeader("Authorization") String tokenHeader){
        try {
            String token = tokenHeader.substring(7);

            String username = jwtUtils.getUserNameFromJwtToken(token);

            if (!adminRepository.existsByUsername(username)){
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Change password failed.");
            }

            Admin admin = (Admin) adminService.getAdminByUsername(username).get();

            if (!encoder.matches(changePasswordRequest.getCurrentPassword(), admin.getPassword())){
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Old password incorrect.");
            }

            admin.setPassword(encoder.encode(changePasswordRequest.getNewPassword()));
            adminRepository.save(admin);

            return ResponseEntity.status(HttpStatus.OK).body(new AdminJwtResponse(
                    admin.getUsername()
            )) ;

        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Change password failed." + tokenHeader);
        }
    }
}
