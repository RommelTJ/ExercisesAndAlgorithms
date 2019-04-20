package com.rommelrico.designpatterns.proxy.models;

import com.rommelrico.designpatterns.proxy.interfaces.*;

public class RealBank implements Bank {

    @Override
    public void withdrawMoney(String clientName) throws Exception {
        System.out.println("Withdrawing from the ATM.");
    }

}
