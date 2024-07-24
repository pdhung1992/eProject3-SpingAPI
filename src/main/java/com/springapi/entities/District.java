package com.springapi.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "districts")
@Getter
@Setter
public class District {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int district_id;

    @Column(name = "district_name")
    private String district_name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "city_id")
    private City city;

    public District(String district_name, City city) {
        this.district_name = district_name;
        this.city = city;
    }

    public District() {
        super();
    }


}
