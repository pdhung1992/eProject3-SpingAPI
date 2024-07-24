package com.springapi.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "cities")
@Getter
@Setter
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int city_id;

    @Column(name = "city_name")
    private String city_name;

    @Column(name = "thumbnail")
    private String thumbnail;

    public City(String city_name, String thumbnail) {
        this.city_name = city_name;
        this.thumbnail = thumbnail;
    }

    public City() {
        super();
    }

}
