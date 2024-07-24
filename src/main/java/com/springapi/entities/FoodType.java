package com.springapi.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "foodtypes")
@Getter
@Setter
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

}
