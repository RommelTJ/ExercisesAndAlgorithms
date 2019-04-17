package com.rommelrico.designpatterns.statedesign;

import com.rommelrico.designpatterns.statedesign.models.*;

public class Main {

    public static void main(String[] args) {
        SodaMachine sodaMachine = new SodaMachine(3);

        sodaMachine.insertMoney();
        sodaMachine.selectSoda();

        sodaMachine.insertMoney();
        sodaMachine.ejectMoney();

        sodaMachine.insertMoney();
        sodaMachine.selectSoda();

        sodaMachine.insertMoney();
        sodaMachine.ejectMoney();

        sodaMachine.selectSoda();
        sodaMachine.insertMoney();
        sodaMachine.selectSoda();
        sodaMachine.insertMoney();
    }

}
