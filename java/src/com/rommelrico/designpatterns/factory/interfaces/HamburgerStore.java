package com.rommelrico.designpatterns.factory.interfaces;

import com.rommelrico.designpatterns.factory.model.*;

public abstract class HamburgerStore {

    public Hamburger orderHamburger(String type) {
        Hamburger hamburger;

        hamburger = createHamburger(type);

        hamburger.prepare();
        hamburger.cook();
        hamburger.box();

        return hamburger;
    }

    // This is abstract so that other Factories can create their own versions.
    abstract public Hamburger createHamburger(String type);

}
