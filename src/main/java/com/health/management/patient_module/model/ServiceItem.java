package com.health.management.patient_module.model;


import jakarta.persistence.Embeddable;

@Embeddable
public class ServiceItem {

    private String serviceName;
    private int quantity;
    private double price;

    // Getters and Setters
    public String getServiceName() { return serviceName; }
    public void setServiceName(String serviceName) { this.serviceName = serviceName; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
}

