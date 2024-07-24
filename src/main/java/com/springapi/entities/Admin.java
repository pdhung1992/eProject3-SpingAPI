package com.springapi.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "admin")
@Getter
@Setter
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int admin_id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "fullName")
    private String fullName;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id")
    private Role role;

    public Admin(String username, String password, String email, String phone, String fullName, Role role) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.fullName = fullName;
        this.role = role;
    }

    public Admin() {
        super();
    }

    public Collection<? extends GrantedAuthority> getPermissions() {
        return List.of();
    }
}
