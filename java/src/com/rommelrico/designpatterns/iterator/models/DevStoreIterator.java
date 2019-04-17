package com.rommelrico.designpatterns.iterator.models;

import com.rommelrico.designpatterns.iterator.interfaces.*;

import java.util.Iterator;

public class DevStoreIterator implements Iterator {

    private static final int MAX_ITEMS = 4;
    private int numberOfProducts = 0;
    private Product[] catalog;

    DevStoreIterator(Product[] catalog) {
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

    @Override
    public void remove() {
        if (numberOfProducts <= 0) throw new IllegalStateException("You can't remove the last item");

        if (catalog[numberOfProducts - 1] == null) {
            for (int i = numberOfProducts; i < (catalog.length - 1); i++) {
                catalog[i] = catalog[i + 1];
            }
            catalog[catalog.length - 1] = null;
        }
    }
}
