package com.rommelrico.designpatterns.singleton;

class MyClass {

    String name;

    private MyClass() { }

    static MyClass getInstance() {
        return new MyClass();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
