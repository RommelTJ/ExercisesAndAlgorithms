package com.rommelrico.designpatterns.visitor;

import com.rommelrico.designpatterns.visitor.models.*;

public class Main {

    public static void main(String[] args) {
        Shirt shirt = new Shirt(18.99);
        TShirt tshirt = new TShirt(9.99);
        Jacket jacket = new Jacket(60);

        TaxVisitor taxVisitor = new TaxVisitor();
        tshirt.accept(taxVisitor);
        shirt.accept(taxVisitor);
        jacket.accept(taxVisitor);
    }

}
