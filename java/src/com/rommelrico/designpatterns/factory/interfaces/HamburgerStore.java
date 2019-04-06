package com.rommelrico.designpatterns.factory.interfaces;

import com.rommelrico.designpatterns.factory.model.*;

public abstract class HamburgerStore {

    SimpleHamburgerFactory factory;

    public HamburgerStore(SimpleHamburgerFactory factory) {
        this.factory = factory;
    }

    public Hamburger orderHamburger(String type) {
        Hamburger hamburger;

        hamburger = factory.createHamburger(type);

        hamburger.prepare();
        hamburger.cook();
        hamburger.box();

        return hamburger;
    }

}
