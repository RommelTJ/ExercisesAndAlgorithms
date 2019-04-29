package com.rommelrico.designpatterns.chainofresponsibility.models;

import com.rommelrico.designpatterns.chainofresponsibility.interfaces.*;

public class Manager extends PurchasePower {

    @Override
    protected double getAllowable() {
        return BASE * 5;
    }

    @Override
    protected String getRole() {
        return "Manager";
    }

}
