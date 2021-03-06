package com.rommelrico.designpatterns.iterator.models;

import com.rommelrico.designpatterns.iterator.interfaces.*;

import java.util.*;

public class GeekyStoreCatalog implements Catalog {

    private ArrayList<Product> catalog;

    public GeekyStoreCatalog() {
        this.catalog = new ArrayList<>();

        // Add a few products to catalog.
        addItem("Superman Comic", "The best in town", 12.99);
        addItem("Batman Comic", "Okay, but still good", 11.99);
        addItem("Star Wars Comic", "Can't live without it", 39.99);
        addItem("Jedi T-Shirt", "Gotta Have It", 29.99);
    }

    private void addItem(String name, String description, double price) {
        Product product = new Product(name, description, price);
        catalog.add(product);
    }

    public GeekyStoreIterator createIterator() {
        return new GeekyStoreIterator(catalog);
    }

}
