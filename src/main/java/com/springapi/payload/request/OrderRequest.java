package com.springapi.payload.request;

public class OrderRequest {
    private int userId;
    private int restaurantId;
    private String eventName;
    private String deliveryAddress;
    private String deliveryDate;
    private String deliveryPerson;
    private String deliveryPhone;
    private String additionalRequirement;
    private double uintPrice;
    private int quantity;
    private double comboDiscount;
    private double totalAmount;
    private double prePaid;
    private int statusId;
    private FoodRequest[] foods;


    public OrderRequest(int userId, int restaurantId, String eventName, String deliveryAddress, String deliveryDate, String deliveryPerson, String deliveryPhone, String additionalRequirement, double uintPrice, int quantity, double comboDiscount, double totalAmount, double prePaid, int statusId, FoodRequest[] foods) {
        this.userId = userId;
        this.restaurantId = restaurantId;
        this.eventName = eventName;
        this.deliveryAddress = deliveryAddress;
        this.deliveryDate = deliveryDate;
        this.deliveryPerson = deliveryPerson;
        this.deliveryPhone = deliveryPhone;
        this.additionalRequirement = additionalRequirement;
        this.uintPrice = uintPrice;
        this.quantity = quantity;
        this.comboDiscount = comboDiscount;
        this.totalAmount = totalAmount;
        this.prePaid = prePaid;
        this.statusId = statusId;
        this.foods = foods;
    }

    public OrderRequest(){}

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getDeliveryPerson() {
        return deliveryPerson;
    }

    public void setDeliveryPerson(String deliveryPerson) {
        this.deliveryPerson = deliveryPerson;
    }

    public String getDeliveryPhone() {
        return deliveryPhone;
    }

    public void setDeliveryPhone(String deliveryPhone) {
        this.deliveryPhone = deliveryPhone;
    }

    public String getAdditionalRequirement() {
        return additionalRequirement;
    }

    public void setAdditionalRequirement(String additionalRequirement) {
        this.additionalRequirement = additionalRequirement;
    }

    public double getUintPrice() {
        return uintPrice;
    }

    public void setUintPrice(double uintPrice) {
        this.uintPrice = uintPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getComboDiscount() {
        return comboDiscount;
    }

    public void setComboDiscount(double comboDiscount) {
        this.comboDiscount = comboDiscount;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public double getPrePaid() {
        return prePaid;
    }

    public void setPrePaid(double prePaid) {
        this.prePaid = prePaid;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public FoodRequest[] getFoods() {
        return foods;
    }

    public void setFoods(FoodRequest[] foods) {
        this.foods = foods;
    }
}
