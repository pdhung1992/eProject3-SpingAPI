package com.springapi.services;

import com.springapi.entities.User;
import com.springapi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public List<User> getAllUser(){
        return userRepository.findAll();
    }

    public Optional<Object> getUserByEmail(String email){
        return userRepository.findByEmail(email);
    }

}
