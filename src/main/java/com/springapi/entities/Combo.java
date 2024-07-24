package com.springapi.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "combos")
@Getter
@Setter
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

}
