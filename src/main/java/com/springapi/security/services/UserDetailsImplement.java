package com.springapi.security.services;

import com.springapi.entities.Role;
import com.springapi.entities.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserDetailsImplement implements UserDetails {
    private int user_id;
    private String email;
    @JsonIgnore
    private String password;
;   private String phone;
    private String fullname;
    private Collection<? extends GrantedAuthority> authorities;

    public UserDetailsImplement(int user_id, String email, String password, String phone, String fullname, List<GrantedAuthority> authorities) {
        this.user_id = user_id;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.fullname = fullname;
        this.authorities = authorities;
    }

    public static UserDetailsImplement build(User user) {

        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("User"));

        return new UserDetailsImplement(
                user.getUser_id(),
                user.getEmail(),
                user.getPassword(),
                user.getPhone(),
                user.getFullname(),
                authorities
        );
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
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
