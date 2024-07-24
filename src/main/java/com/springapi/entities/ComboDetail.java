package com.springapi.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "combo_details")
@Getter
@Setter
public class ComboDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int combo_detail_id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "combo_id")
    private Combo combo;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "food_id")
    private Food food;

    public ComboDetail(Combo combo, Food food) {
        this.combo = combo;
        this.food = food;
    }

    public ComboDetail() {
        super();
    }

}
