package com.rommelrico.designpatterns.facade;

public class Memory {

    public void load(long pos, byte[] data) {
        System.out.println("Added item to memory..." + pos);
    }

}
