package com.rommelrico.designpatterns.iterator;

import com.rommelrico.designpatterns.iterator.interfaces.*;
import com.rommelrico.designpatterns.iterator.models.*;

public class Main {

    public static void main(String[] args) {
        Catalog devStoreCatalog = new DevStoreCatalog();
        Catalog geekyStoreCatalog = new GeekyStoreCatalog();

        Seller seller = new Seller(geekyStoreCatalog, devStoreCatalog);
        seller.printGeekyCatalog();
        System.out.println("---");
        seller.printDevCatalog();
        
    }

}
