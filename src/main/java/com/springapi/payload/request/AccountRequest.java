package com.springapi.payload.request;


import org.springframework.web.multipart.MultipartFile;

public class AccountRequest {
    private String username;
    private String fullname;
    private String email;
    private String telephone;
    private String password;
    private int roleId;

    private String resName;
    private String resAddress;
    private String resDescription;
    private String resDeliveryHours;
    private double prepaidRate;
    private int resMinimumDelivery;
    private MultipartFile resThumbnail;
    private MultipartFile resBanner;
    private int resDistrictId;
    private int resCatId;

    public AccountRequest() {
    }

    public AccountRequest(String fullname, String email, String telephone) {
        this.fullname = fullname;
        this.email = email;
        this.telephone = telephone;
    }

    public AccountRequest(String username, String fullname, String email, String telephone, String password, int roleId, String resName, String resAddress, String resDescription, String resDeliveryHours, double prepaidRate, int resMinimumDelivery, MultipartFile resThumbnail, MultipartFile resBanner, int resDistrictId, int resCatId) {
        this.username = username;
        this.fullname = fullname;
        this.email = email;
        this.telephone = telephone;
        this.password = password;
        this.roleId = roleId;
        this.resName = resName;
        this.resAddress = resAddress;
        this.resDescription = resDescription;
        this.resDeliveryHours = resDeliveryHours;
        this.prepaidRate = prepaidRate;
        this.resMinimumDelivery = resMinimumDelivery;
        this.resThumbnail = resThumbnail;
        this.resBanner = resBanner;
        this.resDistrictId = resDistrictId;
        this.resCatId = resCatId;
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

    public String getResDescription() {
        return resDescription;
    }

    public void setResDescription(String resDescription) {
        this.resDescription = resDescription;
    }

    public String getResDeliveryHours() {
        return resDeliveryHours;
    }

    public void setResDeliveryHours(String resDeliveryHours) {
        this.resDeliveryHours = resDeliveryHours;
    }

    public double getPrepaidRate() {
        return prepaidRate;
    }

    public void setPrepaidRate(double prepaidRate) {
        this.prepaidRate = prepaidRate;
    }

    public int getResMinimumDelivery() {
        return resMinimumDelivery;
    }

    public void setResMinimumDelivery(int resMinimumDelivery) {
        this.resMinimumDelivery = resMinimumDelivery;
    }

    public MultipartFile getResThumbnail() {
        return resThumbnail;
    }

    public void setResThumbnail(MultipartFile resThumbnail) {
        this.resThumbnail = resThumbnail;
    }

    public MultipartFile getResBanner() {
        return resBanner;
    }

    public void setResBanner(MultipartFile resBanner) {
        this.resBanner = resBanner;
    }

    public int getResDistrictId() {
        return resDistrictId;
    }

    public void setResDistrictId(int resDistrictId) {
        this.resDistrictId = resDistrictId;
    }

    public int getResCatId() {
        return resCatId;
    }

    public void setResCatId(int resCatId) {
        this.resCatId = resCatId;
    }
}
