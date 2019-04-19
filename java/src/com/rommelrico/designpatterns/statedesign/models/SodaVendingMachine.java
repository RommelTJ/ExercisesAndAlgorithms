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
}
