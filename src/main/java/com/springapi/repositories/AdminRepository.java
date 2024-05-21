package com.springapi.repositories;

import com.springapi.entities.Admin;
import com.springapi.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer> {
    Boolean existsByUsername(String username);

    Optional<Object> findByUsername(String username);

    @Query("SELECT a.role FROM Admin a WHERE a.admin_id = :adminId")
    Role findRoleByAdminId(@Param("adminId") int adminId);

}
