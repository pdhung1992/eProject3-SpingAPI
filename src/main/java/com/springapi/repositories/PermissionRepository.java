package com.springapi.repositories;

import com.springapi.entities.Permission;
import com.springapi.entities.Role;
import com.springapi.payload.response.PermissionResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Integer> {
    @Query("SELECT p FROM Permission p WHERE p.role.role_id = :roleId")
    List<Permission> findPermissionsByRoleId(@Param("roleId") int roleId);

    @Query("SELECT r FROM Role r JOIN r.permissions p WHERE p.permission_id = :permissionId")
    Role findRoleByPermissionId(@Param("permissionId") int permissionId);

}
