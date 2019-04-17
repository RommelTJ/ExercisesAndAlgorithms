package com.rommelrico.designpatterns.iterator;

import com.rommelrico.designpatterns.iterator.models.*;

import java.util.*;

public class GeekyStoreCatalog {

    private ArrayList<Product> catalog;

    public GeekyStoreCatalog() {
        this.catalog = new ArrayList<>();

        // Add a few products to catalog.
        addItem("Superman Comic", "The best in town", 12.99);
        addItem("Batman Comic", "Okay, but still good", 11.99);
        addItem("Star Wars Comic", "Can't live without it", 39.99);
        addItem("Jedi T-Shirt", "Gotta Have It", 29.99);
    }

    public void addItem(String name, String description, double price) {
        Product product = new Product(name, description, price);
        catalog.add(product);
    }

    public ArrayList<Product> getCatalog() {
        return catalog;
    }

    public GeekyStoreIterator createIterator() {
        return new GeekyStoreIterator(catalog);
    }

}
