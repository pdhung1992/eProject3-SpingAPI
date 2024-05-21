package com.springapi.security.services;

import com.springapi.entities.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class UserDetailsImplement implements UserDetails {
    private int user_id;
    private String email;
    @JsonIgnore
    private String password;
;   private String phone;
    private String fullname;

    public UserDetailsImplement(int user_id, String email, String password, String phone, String fullname) {
        this.user_id = user_id;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.fullname = fullname;
    }

    public static UserDetailsImplement build(User user) {
        return new UserDetailsImplement(
            user.getUser_id(),
            user.getEmail(),
            user.getPassword(),
            user.getPhone(),
            user.getFullname()
        );
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
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

    public int getUser_id() {
        return user_id;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getFullname() {
        return fullname;
    }
}
