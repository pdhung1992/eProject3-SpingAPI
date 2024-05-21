package com.springapi.services;

import com.springapi.payload.response.RoleResponse;
import com.springapi.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleService  {
    @Autowired
    private RoleRepository roleRepository;

    public List<RoleResponse> getAllRole(){
        return roleRepository.findAll().stream()
                .map(role -> new RoleResponse(role.getRole_id(), role.getRole()))
                .collect(Collectors.toList());
    }

}
