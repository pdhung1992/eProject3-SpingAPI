package com.springapi.payload.response;

public class AdminResponse {
    private int id;
    private String username;
    private String fullname;
    private String email;
    private String telephone;
    private int roleId;
    private String role;

    public AdminResponse(int id, String username, String fullname, String email, String telephone, int roleId, String role) {
        this.id = id;
        this.username = username;
        this.fullname = fullname;
        this.email = email;
        this.telephone = telephone;
        this.roleId = roleId;
        this.role = role;
    }

    public AdminResponse(String username) {
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
