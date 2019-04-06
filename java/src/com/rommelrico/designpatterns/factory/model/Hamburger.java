package com.rommelrico.designpatterns.factory.model;

public abstract class Hamburger {

    String name;
    String sauce;
    String buns;

    public void prepare() {
        System.out.println("Preparing: " + name);
        System.out.println("Adding sauce: " + sauce);
        System.out.println("Adding buns: " + buns);
    }

    public void cook() {
        System.out.println("Cooking: " + name);
    }

    public void box() {
        System.out.println("Boxing: " + name);
    }

    public String getName() {
        return name;
    }

}
