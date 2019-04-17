package com.rommelrico.designpatterns.iterator;

public class DevStoreCatalog {

    private static final int MAX_ITEMS = 4;
    private int numberOfProducts = 0;
    Product[] catalog;

    public DevStoreCatalog() {
        this.catalog = new Product[MAX_ITEMS];

        addItem("C++ is not dead yet", "T-shirt", 12.99);
        addItem("Java Rocks", "Silky mouse-pad", 19.99);
        addItem("Java Design Patterns", "Book", 139.99);
        addItem("Web Development Cookbook", "Book", 80.99);
    }

    public void addItem(String name, String description, double price) {
        Product product = new Product(name, description, price);

        if (numberOfProducts >= MAX_ITEMS) {
            System.out.println("Catalog is full - can't add more products");
        } else {
            catalog[numberOfProducts] = product;
            numberOfProducts = numberOfProducts++;
        }
    }

    public Product[] getCatalog() {
        return this.catalog;
    }

}