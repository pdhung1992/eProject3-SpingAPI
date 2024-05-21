package com.springapi.payload.response;

public class PermissionResponse {
    private int id;
    private String name;
    private String prefix;
    private String faIcon;
    private String role;

    public PermissionResponse(int id, String name, String prefix, String faIcon) {
        this.id = id;
        this.name = name;
        this.prefix = prefix;
        this.faIcon = faIcon;
    }

    public PermissionResponse(int id, String name, String prefix, String faIcon, String role) {
        this.id = id;
        this.name = name;
        this.prefix = prefix;
        this.faIcon = faIcon;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getFaIcon() {
        return faIcon;
    }

    public void setFaIcon(String faIcon) {
        this.faIcon = faIcon;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
