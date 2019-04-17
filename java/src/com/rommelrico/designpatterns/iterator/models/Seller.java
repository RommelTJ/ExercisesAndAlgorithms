package com.rommelrico.designpatterns.iterator.models;

public class Seller {

    GeekyStoreCatalog geekyStoreCatalog;
    DevStoreCatalog devStoreCatalog;

    public Seller(GeekyStoreCatalog geekyStoreCatalog, DevStoreCatalog devStoreCatalog) {
        this.geekyStoreCatalog = geekyStoreCatalog;
        this.devStoreCatalog = devStoreCatalog;
    }

    public void printGeekyCatalog() {
        GeekyStoreIterator iterator = geekyStoreCatalog.createIterator();
        System.out.println("Printing geeky store catalog: ");
        while (iterator.hasNext()) {
            Product product = (Product) iterator.next();
            System.out.println("Product: { name: " + product.getName()
                    + ", description: " + product.getDescription()
                    + ", price: " + product.getPrice() + " }" );
        }
    }

    public void printDevCatalog() {
        DevStoreIterator iterator = devStoreCatalog.createIterator();
        System.out.println("Printing dev store catalog: ");
        while (iterator.hasNext()) {
            Product product = (Product) iterator.next();
            System.out.println("Product: { name: " + product.getName()
                    + ", description: " + product.getDescription()
                    + ", price: " + product.getPrice() + " }" );
        }
    }

}
