package com.rommelrico.designpatterns.prototype.models;

import com.rommelrico.designpatterns.prototype.interfaces.*;

public class Dolphin implements Prototype {

    private String name;
    private int age;

    public Dolphin(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public Prototype clone() {
        return new Dolphin(name, age);
    }

    @Override
    public String toString() {
        return "Dolphin{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

}
