package com.springapi.security.services;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.springapi.entities.Admin;
import com.springapi.entities.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class AdminDetailsImplement implements UserDetails {
    private int admin_id;
    private String username;
    @JsonIgnore
    private String password;
    private String phone;
    private String fullname;
    private String email;
    private Role role;


    public AdminDetailsImplement(int admin_id, String username, String password, String phone, String fullname, String email, Role role, Collection<? extends GrantedAuthority> permissions, List<GrantedAuthority> authorities) {
        this.admin_id = admin_id;
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.fullname = fullname;
        this.email = email;
        this.role = role;
    }

    public static AdminDetailsImplement build(Admin admin) {
        Role adminRole = admin.getRole();

        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(adminRole.getRole()));

        for (GrantedAuthority permission : admin.getPermissions()) {
            authorities.add(new SimpleGrantedAuthority(permission.getAuthority()));
        }

        return new AdminDetailsImplement(
            admin.getAdmin_id(),
            admin.getUsername(),
            admin.getPassword(),
            admin.getPhone(),
            admin.getFullName(),
            admin.getEmail(),
            adminRole,
            admin.getPermissions(),
            authorities
        );
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(role.getRole()));
        return authorities;
    }


    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public int getAdmin_id() {
        return admin_id;
    }

    public String getPhone() {
        return phone;
    }

    public String getFullname() {
        return fullname;
    }

    public String getEmail() {
        return email;
    }

    public Role getRole() {
        return role;
    }

}


