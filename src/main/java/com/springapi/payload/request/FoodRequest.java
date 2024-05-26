package com.springapi.payload.request;

import org.springframework.web.multipart.MultipartFile;

public class FoodRequest {
    private int foodId;
    private String name;
    private String description;
    private MultipartFile thumbnail;
    private double price;
    private int serveId;
    private int typeId;
    private int foodTagId;

    public FoodRequest(String name, String description, MultipartFile thumbnail, double price, int serveId, int typeId, int foodTagId) {
        this.name = name;
        this.description = description;
        this.thumbnail = thumbnail;
        this.price = price;
        this.serveId = serveId;
        this.typeId = typeId;
        this.foodTagId = foodTagId;
    }

    public FoodRequest(int foodId, double price) {
        this.foodId = foodId;
        this.price = price;
    }

    public FoodRequest(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public MultipartFile getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(MultipartFile thumbnail) {
        this.thumbnail = thumbnail;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getServeId() {
        return serveId;
    }

    public void setServeId(int serveId) {
        this.serveId = serveId;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public int getFoodTagId() {
        return foodTagId;
    }

    public void setFoodTagId(int foodTagId) {
        this.foodTagId = foodTagId;
    }

    public int getFoodId() {
        return foodId;
    }

    public void setFoodId(int foodId) {
        this.foodId = foodId;
    }
}
