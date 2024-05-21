package com.springapi.payload.request;


import java.io.File;

public class CreateAccountRequest {
    private String username;
    private String fullname;
    private String email;
    private String phone;
    private String password;
    private int roleId;

    private String resName;
    private String resAddress;
    private String resDeliveryHours;
    private double resPrepaidRate;
    private int resMinimumDelivery;
    private File resThumbnail;
    private File resBanner;
    private int resDistrictId;
    private int restCatId;

    public CreateAccountRequest() {
    }

    public CreateAccountRequest(String username, String fullname, String email, String phone, String password, int roleId, String resName, String resAddress, String resDeliveryHours, double resPrepaidRate, int resMinimumDelivery, File resThumbnail, File resBanner, int resDistrictId, int restCatId) {
        this.username = username;
        this.fullname = fullname;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.roleId = roleId;
        this.resName = resName;
        this.resAddress = resAddress;
        this.resDeliveryHours = resDeliveryHours;
        this.resPrepaidRate = resPrepaidRate;
        this.resMinimumDelivery = resMinimumDelivery;
        this.resThumbnail = resThumbnail;
        this.resBanner = resBanner;
        this.resDistrictId = resDistrictId;
        this.restCatId = restCatId;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getResName() {
        return resName;
    }

    public void setResName(String resName) {
        this.resName = resName;
    }

    public String getResAddress() {
        return resAddress;
    }

    public void setResAddress(String resAddress) {
        this.resAddress = resAddress;
    }

    public String getResDeliveryHours() {
        return resDeliveryHours;
    }

    public void setResDeliveryHours(String resDeliveryHours) {
        this.resDeliveryHours = resDeliveryHours;
    }

    public double getResPrepaidRate() {
        return resPrepaidRate;
    }

    public void setResPrepaidRate(double resPrepaidRate) {
        this.resPrepaidRate = resPrepaidRate;
    }

    public int getResMinimumDelivery() {
        return resMinimumDelivery;
    }

    public void setResMinimumDelivery(int resMinimumDelivery) {
        this.resMinimumDelivery = resMinimumDelivery;
    }

    public File getResThumbnail() {
        return resThumbnail;
    }

    public void setResThumbnail(File resThumbnail) {
        this.resThumbnail = resThumbnail;
    }

    public File getResBanner() {
        return resBanner;
    }

    public void setResBanner(File resBanner) {
        this.resBanner = resBanner;
    }

    public int getResDistrictId() {
        return resDistrictId;
    }

    public void setResDistrictId(int resDistrictId) {
        this.resDistrictId = resDistrictId;
    }

    public int getRestCatId() {
        return restCatId;
    }

    public void setRestCatId(int restCatId) {
        this.restCatId = restCatId;
    }
}
