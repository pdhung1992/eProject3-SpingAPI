package com.springapi.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name = "permissions")
@Getter
@Setter
public class Permission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int permission_id;

    @Column(name = "permission_name")
    private String permission_name;

    @Column(name = "sort_order")
    private int sort_order;

    @Column(name = "prefix")
    private String prefix;

    @Column(name = "fa_icon")
    private String fa_icon;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id")
    private Role role;


    public Permission(String permission_name, int sort_order, String prefix, String fa_icon, Role role) {
        this.permission_name = permission_name;
        this.sort_order = sort_order;
        this.prefix = prefix;
        this.fa_icon = fa_icon;
        this.role = role;
    }

    public Permission() {
        super();
    }

}
