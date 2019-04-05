package com.rommelrico.designpatterns.decorator.models;

import com.rommelrico.designpatterns.decorator.interfaces.*;

public class BasicIceCream implements IceCream {

    public BasicIceCream() {
        System.out.println("Creating a basic ice cream");
    }

    @Override
    public double cost() {
        return 0.50;
    }

}
