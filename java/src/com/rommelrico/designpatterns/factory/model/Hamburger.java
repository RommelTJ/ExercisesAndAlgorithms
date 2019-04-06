package com.rommelrico.designpatterns.factory.model;

public abstract class Hamburger {

    public String name;
    public String sauce;
    public String buns;

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
