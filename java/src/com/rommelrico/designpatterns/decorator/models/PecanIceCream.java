package com.rommelrico.designpatterns.decorator.models;

import com.rommelrico.designpatterns.decorator.interfaces.*;

public class PecanIceCream extends IceCreamDecorator {

    public PecanIceCream(IceCream iceCream) {
        super(iceCream);
    }

    @Override
    public double cost() {
        System.out.println("Adding Pecan Ice Cream!");
        return 1.5 + super.cost();
    }

}
