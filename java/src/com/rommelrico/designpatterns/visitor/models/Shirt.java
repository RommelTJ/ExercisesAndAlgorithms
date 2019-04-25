package com.rommelrico.designpatterns.visitor.models;

public class Shirt {
    private double price;

    public Shirt(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
