package com.springapi.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "categories")
@Getter
@Setter
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int category_id;

    @Column(name = "category_name")
    private String category_name;

    @Column(name = "description")
    private String description;

    public Category(String category_name, String description) {
        this.category_name = category_name;
        this.description = description;
    }

    public Category() {
        super();
    }
}
