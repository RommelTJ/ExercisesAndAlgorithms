package com.rommelrico.designpatterns.bridge.models;

import com.rommelrico.designpatterns.bridge.interfaces.*;

public class Car extends Vehicle {

    public Car(Workshop workshop, Workshop workshop2) {
        super(workshop, workshop2);
    }

    @Override
    public void manufacture() {
        System.out.println("Manufacturing car...");
        getWorkshop().make();
        getWorkshop2().make();
    }

}
