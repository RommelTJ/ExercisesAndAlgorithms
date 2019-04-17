package com.rommelrico.designpatterns.iterator;

import java.util.*;

public class GeekyStoreCatalog {

    private ArrayList<Product> catalog;

    public GeekyStoreCatalog() {
        this.catalog = new ArrayList<>();

        // Add a few products to catalog.
    }

    public void addItem(String name, String description, double price) {
        Product product = new Product(name, description, price);
        catalog.add(product);
    }

    public ArrayList<Product> getCatalog() {
        return catalog;
    }

}
