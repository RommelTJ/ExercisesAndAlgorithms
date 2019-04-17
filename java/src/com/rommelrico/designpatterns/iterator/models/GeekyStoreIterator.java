package com.rommelrico.designpatterns.iterator.models;

import java.util.*;

public class GeekyStoreIterator implements Iterator {

    private ArrayList<Product> catalog;
    private int position = 0;

    GeekyStoreIterator(ArrayList<Product> catalog) {
        this.catalog = catalog;
    }

    @Override
    public boolean hasNext() {
        return position < catalog.size() && catalog.get(position) != null;
    }

    @Override
    public Object next() {
        Product product = catalog.get(position);
        position = position + 1;
        return product;
    }

    @Override
    public void remove() {
        catalog.remove(position);
        position = position - 1;
    }
}
