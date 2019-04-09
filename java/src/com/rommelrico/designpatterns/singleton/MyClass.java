package com.rommelrico.designpatterns.singleton;

class MyClass {

    private static MyClass uniqueInstance;

    String name;

    private MyClass() { }

    static synchronized MyClass getInstance() {
        // Adding the synchronized keyword makes our singleton thread safe
        if (uniqueInstance == null) {
            uniqueInstance = new MyClass();
        }
        return uniqueInstance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
