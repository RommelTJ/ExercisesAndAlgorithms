package com.rommelrico.designpatterns.iterator;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        DevStoreCatalog devStoreCatalog = new DevStoreCatalog();
        Product[] devCatalog = devStoreCatalog.getCatalog();

        GeekyStoreCatalog geekyStoreCatalog = new GeekyStoreCatalog();
        ArrayList geekyCatalog = geekyStoreCatalog.getCatalog();

        // Loop through dev catalog - Array
        for (int i = 0; i < devCatalog.length; i++) {
            Product product = devCatalog[i];
            System.out.print(product.getName() + " ");
            System.out.print(product.getDescription() + " ");
            System.out.println(product.getPrice() + " ");
        }

        System.out.println("---");

        // Loop through geeky catalog - ArrayList
        for (int i = 0; i < geekyCatalog.size(); i++) {
            Product product = (Product) geekyCatalog.get(i);
            System.out.print(product.getName() + " ");
            System.out.print(product.getDescription() + " ");
            System.out.println(product.getPrice() + " ");
        }
        
    }

}
