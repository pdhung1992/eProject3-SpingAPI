package com.springapi.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "restaurants")
@Getter
@Setter
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int restaurant_id;

    @Column(name = "restaurant_name")
    private String restaurant_name;

    @Column(name = "restaurant_address")
    private String restaurant_address;

    @Column(name = "join_date")
    private Date join_date;

    @Column(name = "thumbnail")
    private String thumbnail;

    @Column(name = "description")
    private String description;

    @Column(name = "delivery_hours")
    private String delivery_hours;

    @Column(name = "minimum_order")
    private int minimum_order;

    @Column(name = "prepaid_rate")
    private double prepaid_rate;

    @Column(name = "banner")
    private String banner;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "district_id")
    private District district;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "admin_id")
    private Admin admin;

    public Restaurant(String restaurant_name, String restaurant_address, Date join_date, String thumbnail, String description, String delivery_hours, int minimum_order, double prepaid_rate, String banner, Category category, District district, Admin admin) {
        this.restaurant_name = restaurant_name;
        this.restaurant_address = restaurant_address;
        this.join_date = join_date;
        this.thumbnail = thumbnail;
        this.description = description;
        this.delivery_hours = delivery_hours;
        this.minimum_order = minimum_order;
        this.prepaid_rate = prepaid_rate;
        this.banner = banner;
        this.category = category;
        this.district = district;
        this.admin = admin;
    }

    public Restaurant() {
        super();
    }
}
