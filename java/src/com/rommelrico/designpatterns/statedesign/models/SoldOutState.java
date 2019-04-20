package com.rommelrico.designpatterns.statedesign.models;

import com.rommelrico.designpatterns.statedesign.interfaces.*;

public class SoldOutState implements State {

    private SodaVendingMachine sodaVendingMachine;

    SoldOutState(SodaVendingMachine sodaVendingMachine) {
        this.sodaVendingMachine = sodaVendingMachine;
    }

    @Override
    public void insertMoney() {
        System.out.println("Machine sold out!");
    }

    @Override
    public void ejectMoney() {
        System.out.println("Insert money first");
    }

    @Override
    public void select() {
        System.out.println("Machine sold out!");
    }

    @Override
    public void dispense() {
        System.out.println("Machine sold out!");
    }

    @Override
    public String toString() {
        return "Sold Out";
    }

}
