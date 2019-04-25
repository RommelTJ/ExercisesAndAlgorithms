package com.rommelrico.designpatterns.visitor.models;

import com.rommelrico.designpatterns.visitor.interfaces.*;

public class TShirt implements Visitable {

    private double price;

    public TShirt(double price) {
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
