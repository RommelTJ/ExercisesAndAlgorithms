package com.rommelrico.designpatterns.flyweight.models;

import com.rommelrico.designpatterns.flyweight.interfaces.*;

import java.util.*;

public class ShapeFactory {

    private static final HashMap circleMap = new HashMap();

    public static Shape getCircle(String color) {
        Circle circle = (Circle) circleMap.get(color);
        if (circle == null) {
            circle = new Circle(color);
            circleMap.put(color, circle);
            System.out.println("Making a circle of color: " + color);
        }
        return circle;
    }

}
