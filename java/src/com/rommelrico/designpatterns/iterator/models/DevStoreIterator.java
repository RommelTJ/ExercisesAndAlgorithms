package com.rommelrico.designpatterns.iterator.models;

import com.rommelrico.designpatterns.iterator.*;
import com.rommelrico.designpatterns.iterator.interfaces.*;

public class DevStoreIterator implements Iterator {

    private static final int MAX_ITEMS = 4;
    private int numberOfProducts = 0;
    private Product[] catalog;

    public DevStoreIterator(Product[] catalog) {
        this.catalog = catalog;
    }

    @Override
    public boolean hasNext() {
        return numberOfProducts < MAX_ITEMS && catalog[numberOfProducts] != null;
    }

    @Override
    public Object next() {
        Product product = catalog[numberOfProducts];
        numberOfProducts = numberOfProducts + 1;
        return product;
    }

}
