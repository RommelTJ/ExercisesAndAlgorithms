package com.rommelrico.designpatterns.statedesign.models;

import com.rommelrico.designpatterns.statedesign.interfaces.*;

public class HasMoneyState implements State {

    private SodaVendingMachine sodaVendingMachine;

    public HasMoneyState(SodaVendingMachine sodaVendingMachine) {
        this.sodaVendingMachine = sodaVendingMachine;
    }

    @Override
    public void insertMoney() {

    }

    @Override
    public void ejectMoney() {

    }

    @Override
    public void select() {

    }

    @Override
    public void dispense() {

    }

}
