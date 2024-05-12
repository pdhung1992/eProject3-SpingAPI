package com.springapi.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "foodtypes")
public class FoodType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int food_type_id;

    @Column(name = "food_type_name")
    private String food_type_name;

    @Column(name = "description")
    private String description;

    @Column(name = "sort_order")
    private int sort_order;

    public FoodType(String food_type_name, String description, int sort_order) {
        this.food_type_name = food_type_name;
        this.description = description;
        this.sort_order = sort_order;
    }

    public FoodType() {
        super();
    }

    public int getFood_type_id() {
        return food_type_id;
    }

    public void setFood_type_id(int food_type_id) {
        this.food_type_id = food_type_id;
    }

    public String getFood_type_name() {
        return food_type_name;
    }

    public void setFood_type_name(String food_type_name) {
        this.food_type_name = food_type_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getSort_order() {
        return sort_order;
    }

    public void setSort_order(int sort_order) {
        this.sort_order = sort_order;
    }
}
