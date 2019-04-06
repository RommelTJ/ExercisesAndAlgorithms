package com.rommelrico.designpatterns.factory.model;

public class Hamburger {

    private String name;

    public Hamburger(String name) {
        this.name = name;
    }

    public void prepare() {
        System.out.println("Preparing hamburger: " + name);
    }

    public void cook() {
        System.out.println("Cooking hamburger: " + name);
    }

    public void box() {
        System.out.println("Boxing hamburger: " + name);
    }

}
