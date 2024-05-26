package com.springapi.payload.response;

public class ComboResponse {
    private int id;
    private String name;
    private String description;
    private double discountRate;
    private int serveId;
    private String serve;
    private String thumbnail;
    private int comboTagId;
    private String tag;
    private FoodResponse[] foods;
    private double fullPrice;
    private double actualPrice;

    public ComboResponse(int id, String name, String description, double discountRate, String serve, String thumbnail, String tag, FoodResponse[] foods, double fullPrice, double actualPrice) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.discountRate = discountRate;
        this.serve = serve;
        this.thumbnail = thumbnail;
        this.tag = tag;
        this.foods = foods;
        this.fullPrice = fullPrice;
        this.actualPrice = actualPrice;
    }

    public ComboResponse(int id, String name, String description, double discountRate, int serveId, String serve, String thumbnail, int comboTagId, String Tag) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.discountRate = discountRate;
        this.serveId = serveId;
        this.serve = serve;
        this.thumbnail = thumbnail;
        this.comboTagId = comboTagId;
        this.tag = Tag;
    }

    public ComboResponse(int id, String name, String description, double discountRate,String serve, String thumbnail, String Tag) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.discountRate = discountRate;
        this.serve = serve;
        this.thumbnail = thumbnail;
        this.tag = Tag;
    }

    public ComboResponse(String name){
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

    public String getServe() {
        return serve;
    }

    public void setServe(String serve) {
        this.serve = serve;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public int getComboTagId() {
        return comboTagId;
    }

    public void setComboTagId(int comboTagId) {
        this.comboTagId = comboTagId;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        tag = tag;
    }

    public FoodResponse[] getFoods() {
        return foods;
    }

    public void setFoods(FoodResponse[] foods) {
        this.foods = foods;
    }

    public double getFullPrice() {
        return fullPrice;
    }

    public void setFullPrice(double fullPrice) {
        this.fullPrice = fullPrice;
    }

    public double getActualPrice() {
        return actualPrice;
    }

    public void setActualPrice(double actualPrice) {
        this.actualPrice = actualPrice;
    }
}
