package com.rommelrico.designpatterns.adapter.models;

public class Socket {

    public Volt getVolt() {
        return new Volt(120);
    }

}
