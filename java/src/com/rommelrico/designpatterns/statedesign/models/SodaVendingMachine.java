package com.rommelrico.designpatterns.statedesign.models;

import com.rommelrico.designpatterns.statedesign.interfaces.*;

public class SodaVendingMachine {

    private State soldOutState;
    private State noMoneyState;
    private State hasMoneyState;
    private State soldState;

    private State state = soldOutState;
    private int count = 0;

    public SodaVendingMachine(int count) {
        soldOutState = new SoldOutState(this);
        noMoneyState = new NoMoneyState(this);
        hasMoneyState = new HasMoneyState(this);
        soldState = new SoldState(this);

        this.count = count;

        if (count > 0) {
            state = noMoneyState;
        }
    }

    // Actions

    public void insertMoney() {
        state.insertMoney();
    }

    public void ejectMoney() {
        state.ejectMoney();
    }

    public void selectSoda() {
        state.select();
    }

    private void dispense() {
        state.dispense();
    }

    public int getCount() {
        return count;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    State getSoldOutState() {
        return soldOutState;
    }

    State getNoMoneyState() {
        return noMoneyState;
    }

    State getHasMoneyState() {
        return hasMoneyState;
    }

    State getSoldState() {
        return soldState;
    }

}
