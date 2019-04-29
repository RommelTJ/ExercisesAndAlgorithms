package com.rommelrico.designpatterns.bridge.models;

import com.rommelrico.designpatterns.bridge.interfaces.*;

public class Assemble implements Workshop {

    @Override
    public void make() {
        System.out.print("...also ");
        System.out.println("Assembled");
    }
}
