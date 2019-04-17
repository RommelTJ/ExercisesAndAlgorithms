package com.rommelrico.designpatterns.iterator.models;

import com.rommelrico.designpatterns.iterator.interfaces.*;

import java.util.Iterator;

public class Seller {

    private Catalog geekyStoreCatalog;
    private Catalog devStoreCatalog;

    public Seller(Catalog geekyStoreCatalog, Catalog devStoreCatalog) {
        this.geekyStoreCatalog = geekyStoreCatalog;
        this.devStoreCatalog = devStoreCatalog;
    }

    public void printGeekyCatalog() {
        Iterator iterator = geekyStoreCatalog.createIterator();
        System.out.println("Printing geeky store catalog: ");
        while (iterator.hasNext()) {
            Product product = (Product) iterator.next();
            System.out.println("Product: { name: " + product.getName()
                    + ", description: " + product.getDescription()
                    + ", price: " + product.getPrice() + " }" );
        }
    }

    public void printDevCatalog() {
        Iterator iterator = devStoreCatalog.createIterator();
        System.out.println("Printing dev store catalog: ");
        while (iterator.hasNext()) {
            Product product = (Product) iterator.next();
            System.out.println("Product: { name: " + product.getName()
                    + ", description: " + product.getDescription()
                    + ", price: " + product.getPrice() + " }" );
        }
    }

}
