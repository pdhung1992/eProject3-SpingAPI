package com.springapi.payload.response;

import java.util.Date;

public class OrderResponse {
    private int id;
    private String invoiceNumber;
    private Date orderDate;
    private String eventName;
    private String deliveryAddress;
    private Date deliveryDate;
    private double totalAmount;
    private int statusId;
    private String status;

    public OrderResponse(int id, String invoiceNumber, Date orderDate, String eventName, String deliveryAddress, Date deliveryDate, double totalAmount, int statusId, String status) {
        this.id = id;
        this.invoiceNumber = invoiceNumber;
        this.orderDate = orderDate;
        this.eventName = eventName;
        this.deliveryAddress = deliveryAddress;
        this.deliveryDate = deliveryDate;
        this.totalAmount = totalAmount;
        this.statusId = statusId;
        this.status = status;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
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

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
