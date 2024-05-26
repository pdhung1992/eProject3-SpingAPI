package com.springapi.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "foods")
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int food_id;

    @Column(name = "food_name")
    private String food_name;

    @Column(name = "food_price")
    private Double food_price;

    @Column(name = "thumbnail")
    private String thumbnail;

    @Column(name = "description")
    private String description;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "food_type_id")
    private FoodType foodType;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "serve_type_id")
    private ServeType serveType;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "food_tag_id")
    private Tag tag;

    public Food(String food_name, Double food_price, String thumbnail, String description, Restaurant restaurant, FoodType foodType, ServeType serveType, Tag tag) {
        this.food_name = food_name;
        this.food_price = food_price;
        this.thumbnail = thumbnail;
        this.description = description;
        this.restaurant = restaurant;
        this.foodType = foodType;
        this.serveType = serveType;
        this.tag = tag;
    }

    public Food(){}

    public int getFood_id() {
        return food_id;
    }

    public void setFood_id(int food_id) {
        this.food_id = food_id;
    }

    public String getFood_name() {
        return food_name;
    }

    public void setFood_name(String food_name) {
        this.food_name = food_name;
    }

    public Double getFood_price() {
        return food_price;
    }

    public void setFood_price(Double food_price) {
        this.food_price = food_price;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public FoodType getFoodType() {
        return foodType;
    }

    public void setFoodType(FoodType foodType) {
        this.foodType = foodType;
    }

    public ServeType getServeType() {
        return serveType;
    }

    public void setServeType(ServeType serveType) {
        this.serveType = serveType;
    }

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }
}
