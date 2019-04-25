package com.rommelrico.designpatterns.visitor.models;

import com.rommelrico.designpatterns.visitor.interfaces.*;

import java.text.*;

public class TaxVisitor implements Visitor {

    private DecimalFormat decimalFormat = new DecimalFormat("#.##");

    @Override
    public double visitor(Shirt shirt) {
        System.out.println("Shirt final price with tax: ");
        return Double.parseDouble(decimalFormat.format(shirt.getPrice() * 1.10));
    }

    @Override
    public double visitor(TShirt tshirt) {
        System.out.println("TShirt final price with tax: ");
        return Double.parseDouble(decimalFormat.format(tshirt.getPrice() * 1.10));
    }

    @Override
    public double visitor(Jacket jacket) {
        System.out.println("Jacket final price with tax: ");
        return Double.parseDouble(decimalFormat.format(jacket.getPrice() * 1.10));
    }

}
