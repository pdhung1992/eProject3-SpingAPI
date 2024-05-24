package com.springapi.payload.response;

import java.util.Date;

public class RestaurantResponse {
    private int id;
    private String name;
    private String address;
    private Date jointDate;
    private String description;
    private String deliveryHours;
    private int minimumDelivery;
    private double prePaidRate;
    private String thumbnail;
    private String banner;
    private int districtId;
    private String district;
    private int cityId;
    private String city;
    private int catId;
    private String category;
    private int adminId;

    public RestaurantResponse(int id, String name, String address, Date jointDate, String description, String deliveryHours, int minimumDelivery, double prePaidRate, String thumbnail, String banner, int districtId, String district, int cityId, String city, int catId, String category) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.jointDate = jointDate;
        this.description = description;
        this.deliveryHours = deliveryHours;
        this.minimumDelivery = minimumDelivery;
        this.prePaidRate = prePaidRate;
        this.thumbnail = thumbnail;
        this.banner = banner;
        this.districtId = districtId;
        this.district = district;
        this.cityId = cityId;
        this.city = city;
        this.catId = catId;
        this.category = category;
    }

    public RestaurantResponse(int id, String name, String deliveryHours, String thumbnail, String district, String category) {
        this.id = id;
        this.name = name;
        this.deliveryHours = deliveryHours;
        this.thumbnail = thumbnail;
        this.district = district;
        this.category = category;
    }

    public RestaurantResponse(String name){
        this.name = name;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getJointDate() {
        return jointDate;
    }

    public void setJointDate(Date jointDate) {
        this.jointDate = jointDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDeliveryHours() {
        return deliveryHours;
    }

    public void setDeliveryHours(String deliveryHours) {
        this.deliveryHours = deliveryHours;
    }

    public int getMinimumDelivery() {
        return minimumDelivery;
    }

    public void setMinimumDelivery(int minimumDelivery) {
        this.minimumDelivery = minimumDelivery;
    }

    public double getPrePaidRate() {
        return prePaidRate;
    }

    public void setPrePaidRate(double prePaidRate) {
        this.prePaidRate = prePaidRate;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }

    public int getDistrictId() {
        return districtId;
    }

    public void setDistrictId(int districtId) {
        this.districtId = districtId;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getCatId() {
        return catId;
    }

    public void setCatId(int catId) {
        this.catId = catId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }
}
