package com.springapi.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "foods")
@Getter
@Setter
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
}
