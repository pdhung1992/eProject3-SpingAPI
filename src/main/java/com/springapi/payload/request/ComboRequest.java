package com.springapi.payload.request;

import org.springframework.web.multipart.MultipartFile;

public class ComboRequest {
private String name;
    private String description;
    private double discountRate;
    private int serveId;
    private int comboTagId;
    private int[] foods;
    private MultipartFile thumbnail;

    public ComboRequest(String name, String description, double discountRate, int serveId, int comboTagId, int[] foods, MultipartFile thumbnail) {
        this.name = name;
        this.description = description;
        this.discountRate = discountRate;
        this.serveId = serveId;
        this.comboTagId = comboTagId;
        this.foods = foods;
        this.thumbnail = thumbnail;
    }

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

    public double getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(double discountRate) {
        this.discountRate = discountRate;
    }

    public int getServeId() {
        return serveId;
    }

    public void setServeId(int serveId) {
        this.serveId = serveId;
    }

    public int getComboTagId() {
        return comboTagId;
    }

    public void setComboTagId(int comboTagId) {
        this.comboTagId = comboTagId;
    }

    public int[] getFoods() {
        return foods;
    }

    public void setFoods(int[] foods) {
        this.foods = foods;
    }

    public MultipartFile getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(MultipartFile thumbnail) {
        this.thumbnail = thumbnail;
    }
}
