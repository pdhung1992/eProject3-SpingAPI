package com.springapi.services;

import com.springapi.entities.Permission;
import com.springapi.entities.Role;
import com.springapi.payload.response.PermissionResponse;
import com.springapi.repositories.PermissionRepository;
import com.springapi.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PermissionService {
    @Autowired
    private PermissionRepository permissionRepository;


    public List<PermissionResponse> getAllPermission(){
        List<Permission> permissions = permissionRepository.findAll();

        return permissions.stream()
                .map(permission -> {
                    Role role = permissionRepository.findRoleByPermissionId(permission.getPermission_id());
                    String roleName = (role != null) ? role.getRole() : "";
                    return new PermissionResponse(permission.getPermission_id(), permission.getPermission_name(), permission.getPrefix(), permission.getFa_icon(), roleName);
                })
                .collect(Collectors.toList());
    }
}
