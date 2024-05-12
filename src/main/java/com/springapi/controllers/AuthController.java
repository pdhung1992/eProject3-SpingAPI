package com.springapi.controllers;

import com.springapi.entities.User;
import com.springapi.payload.request.RegisterRequest;
import com.springapi.payload.response.MessageResponse;
import com.springapi.repositories.UserRepository;
import com.springapi.security.jwt.JwtUtils;
import com.springapi.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;


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
}
