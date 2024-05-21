package com.springapi.entities;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name = "permissions")
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

    public int getPermission_id() {
        return permission_id;
    }

    public void setPermission_id(int permission_id) {
        this.permission_id = permission_id;
    }

    public String getPermission_name() {
        return permission_name;
    }

    public void setPermission_name(String permission_name) {
        this.permission_name = permission_name;
    }

    public int getSort_order() {
        return sort_order;
    }

    public void setSort_order(int sort_order) {
        this.sort_order = sort_order;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getFa_icon() {
        return fa_icon;
    }

    public void setFa_icon(String fa_icon) {
        this.fa_icon = fa_icon;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

}
