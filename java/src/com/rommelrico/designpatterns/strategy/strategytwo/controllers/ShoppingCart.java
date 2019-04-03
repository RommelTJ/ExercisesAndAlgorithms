package com.rommelrico.designpatterns.strategy.strategytwo.controllers;

import com.rommelrico.designpatterns.strategy.strategytwo.model.*;

import java.util.*;

public class ShoppingCart {

    List<Product> productList;

    public ShoppingCart() {
        this.productList = new ArrayList<>();
    }

    public void addProduct(Product product) {
        productList.add(product);
    }

    public void remove(Product product) {
        productList.remove(product);
    }

    public int calculateTotal() {
        int sum = 0;
        for (Product product: productList) {
            sum += product.getPrice();
        }
        return sum;
    }

    public void pay() {
        int amount = calculateTotal();
        // TODO: Implement payment algorithm.
    }

}
