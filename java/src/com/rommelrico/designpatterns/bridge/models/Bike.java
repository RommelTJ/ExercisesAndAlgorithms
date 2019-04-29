package com.rommelrico.designpatterns.bridge.models;

import com.rommelrico.designpatterns.bridge.interfaces.*;

public class Bike extends Vehicle {

    public Bike(Workshop workshop, Workshop workshop2) {
        super(workshop, workshop2);
    }

    @Override
    public void manufacture() {
        System.out.println("Manufacturing bike...");
        getWorkshop().make();
        getWorkshop2().make();
    }

}
