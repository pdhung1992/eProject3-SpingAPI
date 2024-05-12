package com.springapi.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "combos")
public class Combo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int combo_id;

    @Column(name = "combo_name")
    private String combo_name;

    @Column(name = "description")
    private String description;

    @Column(name = "discount_rate")
    private Double discount_rate;

    @Column(name = "thumbnail")
    private String thumbnail;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "serve_type_id")
    private ServeType serveType;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "combo_tag_id")
    private Tag tag;

    public Combo(String combo_name, String description, Double discount_rate, String thumbnail, Restaurant restaurant, ServeType serveType, Tag tag) {
        this.combo_name = combo_name;
        this.description = description;
        this.discount_rate = discount_rate;
        this.thumbnail = thumbnail;
        this.restaurant = restaurant;
        this.serveType = serveType;
        this.tag = tag;
    }

    public Combo() {
        super();
    }

    public int getCombo_id() {
        return combo_id;
    }

    public void setCombo_id(int combo_id) {
        this.combo_id = combo_id;
    }

    public String getCombo_name() {
        return combo_name;
    }

    public void setCombo_name(String combo_name) {
        this.combo_name = combo_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getDiscount_rate() {
        return discount_rate;
    }

    public void setDiscount_rate(Double discount_rate) {
        this.discount_rate = discount_rate;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
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
