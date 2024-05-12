package com.springapi.entities;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "orders")
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

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public Date getOrder_date() {
        return order_date;
    }

    public void setOrder_date(Date order_date) {
        this.order_date = order_date;
    }

    public String getInvoice_no() {
        return invoice_no;
    }

    public void setInvoice_no(String invoice_no) {
        this.invoice_no = invoice_no;
    }

    public String getEvent_name() {
        return event_name;
    }

    public void setEvent_name(String event_name) {
        this.event_name = event_name;
    }

    public Date getDelivery_date() {
        return delivery_date;
    }

    public void setDelivery_date(Date delivery_date) {
        this.delivery_date = delivery_date;
    }

    public String getDelivery_address() {
        return delivery_address;
    }

    public void setDelivery_address(String delivery_address) {
        this.delivery_address = delivery_address;
    }

    public String getDelivery_person() {
        return delivery_person;
    }

    public void setDelivery_person(String delivery_person) {
        this.delivery_person = delivery_person;
    }

    public String getDelivery_phone() {
        return delivery_phone;
    }

    public void setDelivery_phone(String delivery_phone) {
        this.delivery_phone = delivery_phone;
    }

    public String getAdditional_note() {
        return additional_note;
    }

    public void setAdditional_note(String additional_note) {
        this.additional_note = additional_note;
    }

    public Double getUnit_price() {
        return unit_price;
    }

    public void setUnit_price(Double unit_price) {
        this.unit_price = unit_price;
    }

    public Double getCombo_discount() {
        return combo_discount;
    }

    public void setCombo_discount(Double combo_discount) {
        this.combo_discount = combo_discount;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Double getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(Double total_amount) {
        this.total_amount = total_amount;
    }

    public Double getPrepaid_amount() {
        return prepaid_amount;
    }

    public void setPrepaid_amount(Double prepaid_amount) {
        this.prepaid_amount = prepaid_amount;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }
}
