package com.rommelrico.designpatterns.iterator.models;

import com.rommelrico.designpatterns.iterator.*;

public class Seller {

    GeekyStoreCatalog geekyStoreCatalog;

    public Seller(GeekyStoreCatalog geekyStoreCatalog) {
        this.geekyStoreCatalog = geekyStoreCatalog;
    }

    public void printCatalog() {
        GeekyStoreIterator iterator = geekyStoreCatalog.createIterator();
        System.out.println("Printing catalog: ");
        while (iterator.hasNext()) {
            Product product = (Product) iterator.next();
            System.out.println("Product: { name: " + product.getName()
                    + ", description: " + product.getDescription()
                    + ", price: " + product.getPrice() + " }" );
        }
    }

}
