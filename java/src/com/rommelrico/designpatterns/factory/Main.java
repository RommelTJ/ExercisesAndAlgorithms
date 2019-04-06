package com.rommelrico.designpatterns.factory;

import com.rommelrico.designpatterns.factory.interfaces.*;
import com.rommelrico.designpatterns.factory.model.*;

public class Main {

    public static void main(String[] args) {
        HamburgerStore mozambicanStore = new MozambicanHamburgerStore();
        HamburgerStore jamaicanStore = new JamaicanHamburgerStore();

        Hamburger burger1 = mozambicanStore.orderHamburger("cheese");
        System.out.println("Rommel ordered: " + burger1.getName());

        Hamburger burger2 = jamaicanStore.orderHamburger("veggie");
        System.out.println("Rommel ordered: " + burger2.getName());
    }
}
