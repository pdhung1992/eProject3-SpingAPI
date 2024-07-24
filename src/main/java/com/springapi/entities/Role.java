package com.springapi.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "roles")
@Getter
@Setter
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int role_id;

    @Column(name = "role")
    private String role;

    @ManyToMany(mappedBy = "role")
    private List<Permission> permissions;

    public Role(String role) {
        this.role = role;
    }

    public Role() {
        super();
    }
}
