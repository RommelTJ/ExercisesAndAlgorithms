package com.rommelrico.designpatterns.statedesign;

import com.rommelrico.designpatterns.statedesign.models.*;

public class Main {

    public static void main(String[] args) {
        SodaVendingMachine sodaVendingMachine = new SodaVendingMachine(3);
        sodaVendingMachine.insertMoney();
        sodaVendingMachine.selectSoda();
        sodaVendingMachine.dispense();

        sodaVendingMachine.insertMoney();
        sodaVendingMachine.selectSoda();
        sodaVendingMachine.dispense();
        sodaVendingMachine.insertMoney();
        sodaVendingMachine.selectSoda();
        sodaVendingMachine.dispense();

        sodaVendingMachine.insertMoney();
        sodaVendingMachine.selectSoda();
        sodaVendingMachine.dispense();
    }

}
