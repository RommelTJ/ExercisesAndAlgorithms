package com.rommelrico.designpatterns.decorator.models;

import com.rommelrico.designpatterns.decorator.interfaces.*;

public class ChocolateIceCream extends IceCreamDecorator {

    public ChocolateIceCream(IceCream iceCream) {
        super(iceCream);
    }

    @Override
    public double cost() {
        return 1.0 + super.cost();
    }

}
