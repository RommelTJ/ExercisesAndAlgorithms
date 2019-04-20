package com.rommelrico.designpatterns.statedesign.models;

import com.rommelrico.designpatterns.statedesign.interfaces.*;

public class SoldState implements State {

    private SodaVendingMachine sodaVendingMachine;

    public SoldState(SodaVendingMachine sodaVendingMachine) {
        this.sodaVendingMachine = sodaVendingMachine;
    }

    @Override
    public void insertMoney() {
        System.out.println("Please wait...");
    }

    @Override
    public void ejectMoney() {
        System.out.println("Soda already sold.");
    }

    @Override
    public void select() {
        System.out.println("Soda already selected.");
    }

    @Override
    public void dispense() {
        System.out.println("Already sold");
        if (sodaVendingMachine.getCount() > 0) {
            sodaVendingMachine.setState(sodaVendingMachine.getNoMoneyState());
        } else {
            System.out.println("Sorry, out of sodas.");
            sodaVendingMachine.setState(sodaVendingMachine.getSoldOutState());
        }
    }

    @Override
    public String toString() {
        return "Sold";
    }

}
