package com.rommelrico.designpatterns.decorator.models;

import com.rommelrico.designpatterns.decorator.interfaces.*;

public class ChocolateIceCream extends IceCreamDecorator {

    public ChocolateIceCream(IceCream iceCream) {
        super(iceCream);
    }

    @Override
    public double cost() {
        System.out.println("Adding Chocolate Ice Cream!");
        return 1.0 + super.cost();
    }

}
