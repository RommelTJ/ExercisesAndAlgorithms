package com.rommelrico.designpatterns.bridge.interfaces;

public abstract class Vehicle {

    private Workshop workshop;
    private Workshop workshop2;

    public Vehicle(Workshop workshop, Workshop workshop2) {
        this.workshop = workshop;
        this.workshop2 = workshop2;
    }

    abstract public void manufacture();

    public Workshop getWorkshop() {
        return workshop;
    }

    public Workshop getWorkshop2() {
        return workshop2;
    }

}
