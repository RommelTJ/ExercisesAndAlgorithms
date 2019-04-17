package com.rommelrico.designpatterns.iterator;

import com.rommelrico.designpatterns.iterator.models.*;

public class Main {

    public static void main(String[] args) {
        DevStoreCatalog devStoreCatalog = new DevStoreCatalog();
        GeekyStoreCatalog geekyStoreCatalog = new GeekyStoreCatalog();

        Seller seller = new Seller(geekyStoreCatalog, devStoreCatalog);
        seller.printGeekyCatalog();
        System.out.println("---");
        seller.printDevCatalog();
        
    }

}
