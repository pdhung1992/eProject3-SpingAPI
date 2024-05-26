package com.springapi.payload.response;

public class FoodResponse {
    private int id;
    private String name;
    private int resId;
    private int typeId;
    private String type;
    private String description;
    private double price;
    private int serveId;
    private String serve;
    private String thumbnail;
    private int foodTagId;
    private String tag;

    public FoodResponse(int id, String name, int typeId, String type, String description, double price, int serveId, String serve, String thumbnail, int foodTagId, String tag) {
        this.id = id;
        this.name = name;
        this.typeId = typeId;
        this.type = type;
        this.description = description;
        this.price = price;
        this.serveId = serveId;
        this.serve = serve;
        this.thumbnail = thumbnail;
        this.foodTagId = foodTagId;
        this.tag = tag;
    }

    public FoodResponse(int id, String name, int typeId, String type, String description, double price, String serve, String thumbnail, String tag) {
        this.id = id;
        this.name = name;
        this.typeId = typeId;
        this.type = type;
        this.description = description;
        this.price = price;
        this.serve = serve;
        this.thumbnail = thumbnail;
        this.tag = tag;
    }

    public FoodResponse(int id, String name, int typeId, String type, String description, double price, String serve, String thumbnail, int foodTagId, String tag) {
        this.id = id;
        this.name = name;
        this.typeId = typeId;
        this.type = type;
        this.description = description;
        this.price = price;
        this.serve = serve;
        this.thumbnail = thumbnail;
        this.foodTagId = foodTagId;
        this.tag = tag;
    }

    public FoodResponse(String name){
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

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public int getFoodTagId() {
        return foodTagId;
    }

    public void setFoodTagId(int foodTagId) {
        this.foodTagId = foodTagId;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
