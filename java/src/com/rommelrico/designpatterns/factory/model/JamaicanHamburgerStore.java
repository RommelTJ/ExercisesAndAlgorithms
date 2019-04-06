package com.rommelrico.designpatterns.factory.model;

import com.rommelrico.designpatterns.factory.interfaces.*;

public class JamaicanHamburgerStore extends HamburgerStore {

    @Override
    public Hamburger createHamburger(String type) {
        if (type.equals("cheese")) {
            return new JamaicanCheeseburger();
        } else if (type.equals("Veggie")) {
            return new JamaicanVeggieburger();
        }
        return null;
    }

}
