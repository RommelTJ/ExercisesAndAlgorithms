package com.rommelrico.designpatterns.factory.model;

import com.rommelrico.designpatterns.factory.interfaces.*;

public class MozambicanHamburgerStore extends HamburgerStore {

    @Override
    public Hamburger createHamburger(String type) {
        if (type.equals("cheese")) {
            return new MozambicanCheeseburger("Mozambican Cheeseburger");
        } else if (type.equals("Veggie")) {
            return new MozambicanVeggieburger("Mozambican Veggieburger");
        }
        return null;
    }

}
