package com.springapi.services;

import com.springapi.entities.Admin;
import com.springapi.entities.Permission;
import com.springapi.entities.Restaurant;
import com.springapi.entities.Role;
import com.springapi.payload.response.AdminResponse;
import com.springapi.payload.response.PermissionResponse;
import com.springapi.repositories.AdminRepository;
import com.springapi.repositories.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AdminService {
    @Autowired
    private PermissionRepository permissionRepository;

    @Autowired
    private AdminRepository adminRepository;

    public List<PermissionResponse> getPermissionsByRoleId(int roleId) {
        List<Permission> permissions = permissionRepository.findPermissionsByRoleId(roleId);
        return permissions.stream()
                .map(permission -> new PermissionResponse(permission.getPermission_id(), permission.getPermission_name(), permission.getPrefix(), permission.getFa_icon()))
                .collect(Collectors.toList());
    }

    public boolean checkIfUsernameExists(String username) {
        return adminRepository.existsByUsername(username);
    }

    public List<AdminResponse> getAllAccounts(){

        List<Admin> admins = adminRepository.findAll();

        return admins.stream()
                .map(admin -> {
                    Role role = adminRepository.findRoleByAdminId(admin.getAdmin_id());
                    String roleName = (role != null) ? role.getRole() : "";
                    return new AdminResponse(admin.getAdmin_id(), admin.getUsername(), admin.getFullName(), admin.getEmail(), admin.getPhone(), role.getRole_id(), roleName);
                })
                .collect(Collectors.toList());
    }

    public AdminResponse getAdminById(int adminId) {
        Admin admin = adminRepository.findById(adminId).get();
        Role role = adminRepository.findRoleByAdminId(adminId);
        String roleName = (role != null) ? role.getRole() : "";
        return new AdminResponse(admin.getAdmin_id(), admin.getUsername(), admin.getFullName(), admin.getEmail(), admin.getPhone(), role.getRole_id(), roleName);
    }

    public Admin findAdminById(int adminId) {
        return adminRepository.findById(adminId).get();
    }

    public Restaurant findRestaurantByAdminId(int adminId) {
        return adminRepository.findRestaurantByAdminId(adminId);
    }

    public Optional<Object> getAdminByUsername(String username) {
        return adminRepository.findAdminByUsername(username);
    }

    public void createAdmin(Admin admin) {
        adminRepository.save(admin);
    }

    public void updateAdmin(Admin admin) {
        adminRepository.save(admin);
    }

    public void deleteAdmin(int adminId) {
        adminRepository.deleteById(adminId);
    }
}
