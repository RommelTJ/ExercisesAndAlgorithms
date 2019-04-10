package com.rommelrico.designpatterns.singleton;

public class Singleton {

    // Volatile = ensures multiple thread checking
    private volatile static Singleton uniqueInstance;

    private Singleton() {}

    // Double-check locking
    public static Singleton getInstance() {
        if (uniqueInstance == null) {
            synchronized ((Singleton.class)) {
                if (uniqueInstance == null) {
                    uniqueInstance = new Singleton();
                }
            }
        }
        return uniqueInstance;
    }

}
