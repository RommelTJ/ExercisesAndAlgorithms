package com.rommelrico.designpatterns.facade;

public class CPU {

    public void freeze() {
        System.out.println("Computer freezing...");
    }

    public void jump(long pos) {
        System.out.println("Jumping to position: " + pos);
    }

    public void execute() {
        System.out.println("Computer executing commands...");
    }

}
