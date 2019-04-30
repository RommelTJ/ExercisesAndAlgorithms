package com.rommelrico.designpatterns.flyweight.models;

import com.rommelrico.designpatterns.flyweight.interfaces.*;

public class Circle implements Shape {

    private String color;
    private int x;
    private int y;
    private int radius;

    @Override
    public void draw() {
        System.out.println("Drawing a circle...");
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }
}
