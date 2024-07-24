package com.springapi.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "orders")
@Getter
@Setter
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int order_id;

    @Column(name = "order_date")
    private Date order_date;

    @Column(name = "invoice_no")
    private String invoice_no;

    @Column(name = "event_name")
    private String event_name;

    @Column(name = "delivery_date")
    private Date delivery_date;

    @Column(name = "delivery_address")
    private String delivery_address;

    @Column(name = "delivery_person")
    private String delivery_person;

    @Column(name = "delivery_phone")
    private String delivery_phone;

    @Column(name = "additional_note")
    private String additional_note;

    @Column(name = "unit_price")
    private Double unit_price;

    @Column(name = "combo_discount")
    private Double combo_discount;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "total_amount")
    private Double total_amount;

    @Column(name = "prepaid_amount")
    private Double prepaid_amount;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "status_id")
    private Status status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    public Order(Date order_date, String invoice_no, String event_name, Date delivery_date, String delivery_address, String delivery_person, String delivery_phone, String additional_note, Double unit_price, Double combo_discount, int quantity, Double total_amount, Double prepaid_amount, Status status, User user, Restaurant restaurant) {
        this.order_date = order_date;
        this.invoice_no = invoice_no;
        this.event_name = event_name;
        this.delivery_date = delivery_date;
        this.delivery_address = delivery_address;
        this.delivery_person = delivery_person;
        this.delivery_phone = delivery_phone;
        this.additional_note = additional_note;
        this.unit_price = unit_price;
        this.combo_discount = combo_discount;
        this.quantity = quantity;
        this.total_amount = total_amount;
        this.prepaid_amount = prepaid_amount;
        this.status = status;
        this.user = user;
        this.restaurant = restaurant;
    }

    public Order(){super();}


}
