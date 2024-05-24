package com.springapi.payload.request;

import org.springframework.web.multipart.MultipartFile;

public class RestaurantRequest {
    private String name;
    private String address;
    private String description;
    private double prePaidRate;
    private int catId;
    private int districtId;
    private String deliveryHours;
    private int minimumDelivery;
    private MultipartFile thumbnail;
    private MultipartFile banner;

    public RestaurantRequest() {
    }

    public RestaurantRequest(String name, String address, String description, double prePaidRate, int catId, int districtId, String deliveryHours, int minimumDelivery, MultipartFile thumbnail, MultipartFile banner) {
        this.name = name;
        this.address = address;
        this.description = description;
        this.prePaidRate = prePaidRate;
        this.catId = catId;
        this.districtId = districtId;
        this.deliveryHours = deliveryHours;
        this.minimumDelivery = minimumDelivery;
        this.thumbnail = thumbnail;
        this.banner = banner;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrePaidRate() {
        return prePaidRate;
    }

    public void setPrePaidRate(double prePaidRate) {
        this.prePaidRate = prePaidRate;
    }

    public int getCatId() {
        return catId;
    }

    public void setCatId(int catId) {
        this.catId = catId;
    }

    public int getDistrictId() {
        return districtId;
    }

    public void setDistrictId(int districtId) {
        this.districtId = districtId;
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

    public MultipartFile getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(MultipartFile thumbnail) {
        this.thumbnail = thumbnail;
    }

    public MultipartFile getBanner() {
        return banner;
    }

    public void setBanner(MultipartFile banner) {
        this.banner = banner;
    }
}
