package com.rommelrico.designpatterns.chainofresponsibility.models;

import com.rommelrico.designpatterns.chainofresponsibility.interfaces.*;

public class CEOPurchasePower extends PurchasePower {

    @Override
    protected double getAllowable() {
        return BASE * 10;
    }

    @Override
    protected String getRole() {
        return "CEO";
    }

}
