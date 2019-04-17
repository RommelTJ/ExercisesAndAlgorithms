package com.rommelrico.designpatterns.iterator.models;

import com.rommelrico.designpatterns.iterator.*;
import com.rommelrico.designpatterns.iterator.interfaces.Iterator;

import java.util.*;

public class GeekyStoreIterator implements Iterator {

    ArrayList<Product> catalog;
    int position = 0;

    public GeekyStoreIterator(ArrayList<Product> catalog) {
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

}
