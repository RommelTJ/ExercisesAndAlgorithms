package com.rommelrico.designpatterns.statedesign.models;

import com.rommelrico.designpatterns.statedesign.interfaces.*;

public class NoMoneyState implements State {

    private SodaVendingMachine sodaVendingMachine;

    NoMoneyState(SodaVendingMachine sodaVendingMachine) {
        this.sodaVendingMachine = sodaVendingMachine;
    }

    @Override
    public void insertMoney() {
        System.out.println("You inserted a dollar");
        sodaVendingMachine.setState(sodaVendingMachine.getHasMoneyState());
    }

    @Override
    public void ejectMoney() {
        System.out.println("You haven't inserted a dollar");
    }

    @Override
    public void select() {
        System.out.println("You selected a soda, but there's no money");
    }

    @Override
    public void dispense() {
        System.out.println("You need to add money first");
    }

    @Override
    public String toString() {
        return "No Money";
    }
}
