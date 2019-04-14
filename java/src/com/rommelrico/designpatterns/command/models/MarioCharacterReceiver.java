package com.rommelrico.designpatterns.command.models;

public class MarioCharacterReceiver {

    private String name;

    public void moveUp() {
        System.out.println(getName() + " jumping up!");
    }

    public void moveDown() {
        System.out.println(getName() + " ducking!");
    }

    public void moveLeft() {
        System.out.println(getName() + " moving left!");
    }

    public void moveRight() {
        System.out.println(getName() + " move right!");
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
