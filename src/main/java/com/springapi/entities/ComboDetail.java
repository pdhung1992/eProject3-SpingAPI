package com.springapi.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "combo_details")
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

    public int getCombo_detail_id() {
        return combo_detail_id;
    }

    public void setCombo_detail_id(int combo_detail_id) {
        this.combo_detail_id = combo_detail_id;
    }

    public Combo getCombo() {
        return combo;
    }

    public void setCombo(Combo combo) {
        this.combo = combo;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }
}
