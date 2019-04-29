package com.rommelrico.designpatterns.chainofresponsibility.models;

import com.rommelrico.designpatterns.chainofresponsibility.interfaces.*;

public class Director extends PurchasePower {

    @Override
    protected double getAllowable() {
        return BASE * 7;
    }

    @Override
    protected String getRole() {
        return "Director";
    }

}
