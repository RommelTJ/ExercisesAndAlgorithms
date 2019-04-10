package com.rommelrico.designpatterns.singleton;

public class Singleton {

    private static Singleton uniqueInstance = new Singleton();
    String name;

    private Singleton() {}

    public static Singleton getInstance() {
        return uniqueInstance;
    }

}
