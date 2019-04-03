package com.rommelrico.designpatterns.strategy.strategytwo;

import com.rommelrico.designpatterns.strategy.strategytwo.controllers.*;
import com.rommelrico.designpatterns.strategy.strategytwo.model.*;

public class Main {

    public static void main(String[] args) {
        ShoppingCart cart = new ShoppingCart();
        Product pants = new Product("234", 25);
        Product shirt = new Product("987", 15);
        cart.addProduct(pants);
        cart.addProduct(shirt);

        // Payment decisions
        Payment paypal = new PayPalAlgorithm("sdgdsg@sdgdsg.com", "124");
        Payment creditCard = new CreditCardAlgorithm("name", "1232");
        cart.pay(paypal); // 40 paid with PayPal.
        cart.pay(creditCard); // 40 paid with Credit Card.
    }

}
