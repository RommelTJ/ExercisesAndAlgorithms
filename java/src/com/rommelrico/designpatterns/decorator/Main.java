package com.rommelrico.designpatterns.decorator;

import com.rommelrico.designpatterns.decorator.interfaces.*;
import com.rommelrico.designpatterns.decorator.models.*;

public class Main {

    public static void main(String[] args) {
        IceCream basicIceCream = new BasicIceCream();
        System.out.println("Basic Ice Cream Cost: $" + basicIceCream.cost());

        // Add Chocolate to the order
        IceCream withChocolateIceCream = new ChocolateIceCream(basicIceCream);
        System.out.println("Basic with Chocolate Ice Cream Cost: $" + withChocolateIceCream.cost());

        // Add Mint to the order
        IceCream withMintAndChocolateIceCream = new MintIceCream(withChocolateIceCream);
        System.out.println("Basic with Mint and Chocolate Ice Cream Cost: $" + withMintAndChocolateIceCream.cost());

        // Add Pecan to the order
        IceCream withPecanMintChocolateIceCream = new PecanIceCream(withMintAndChocolateIceCream);
        System.out.println("Basic, Chocolate, Mint, Pecan Ice Cream Cost: $" + withPecanMintChocolateIceCream.cost());
    }

}
