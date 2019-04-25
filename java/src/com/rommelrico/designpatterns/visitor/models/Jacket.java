package com.rommelrico.designpatterns.visitor.models;

import com.rommelrico.designpatterns.visitor.interfaces.*;

public class Jacket implements Visitable {

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

    @Override
    public double accept(Visitor visitor) {
        return visitor.visitor(this);
    }

}
