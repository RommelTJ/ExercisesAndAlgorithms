package com.rommelrico.designpatterns.visitor.models;

public class Jacket {
    private double price;

    public Jacket(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
