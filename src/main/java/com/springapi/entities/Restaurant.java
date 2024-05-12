package com.springapi.entities;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "restaurants")
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
    private int prepaid_rate;

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

    public Restaurant(String restaurant_name, String restaurant_address, Date join_date, String thumbnail, String description, String delivery_hours, int minimum_order, int prepaid_rate, String banner, Category category, District district, Admin admin) {
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

    public int getRestaurant_id() {
        return restaurant_id;
    }

    public void setRestaurant_id(int restaurant_id) {
        this.restaurant_id = restaurant_id;
    }

    public String getRestaurant_name() {
        return restaurant_name;
    }

    public void setRestaurant_name(String restaurant_name) {
        this.restaurant_name = restaurant_name;
    }

    public String getRestaurant_address() {
        return restaurant_address;
    }

    public void setRestaurant_address(String restaurant_address) {
        this.restaurant_address = restaurant_address;
    }

    public Date getJoin_date() {
        return join_date;
    }

    public void setJoin_date(Date join_date) {
        this.join_date = join_date;
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

    public String getDelivery_hours() {
        return delivery_hours;
    }

    public void setDelivery_hours(String delivery_hours) {
        this.delivery_hours = delivery_hours;
    }

    public int getMinimum_order() {
        return minimum_order;
    }

    public void setMinimum_order(int minimum_order) {
        this.minimum_order = minimum_order;
    }

    public int getPrepaid_rate() {
        return prepaid_rate;
    }

    public void setPrepaid_rate(int prepaid_rate) {
        this.prepaid_rate = prepaid_rate;
    }

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }
}
